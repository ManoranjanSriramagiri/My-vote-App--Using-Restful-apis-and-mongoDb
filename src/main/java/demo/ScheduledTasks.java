package demo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;
//import org.neo4j.graphalgo.impl.util.IntegerAdder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class ScheduledTasks 
{

    
    MongoClient mongoClient=null;
	MongoCollection<Document> collPoll=null;
	MongoCollection<Document> coll=null;
	MongoDatabase db=null;
	
    
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {
    	 TimeZone tz = TimeZone.getTimeZone("PST");
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'dd:HH:mm.sss'Z'");
         df.setTimeZone(tz);
         String nowAsISO = df.format(new Date());
         System.out.println("The time is now " + nowAsISO);
         List<String> modEmailIdArray=ViewWithResults(nowAsISO);
         //Iterator<String> itModResult = modEmailIdArray.iterator();
         TestProducer tp =new TestProducer();
         tp.Producer(modEmailIdArray);
         
         
    }
    
    public List<String>  ViewWithResults(String nowAsISO)
	{
    	try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				collPoll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		
		Bson proj =new Document( "_id",0);	
		List<Document> poll = collPoll.find().projection(proj).into(new ArrayList<Document>());
		Iterator<Document> itPoll = poll.iterator();
		List<String> modEmailIdArray=new ArrayList<String>();
		
		while(itPoll.hasNext())
		{
			Document one =new Document();
			one =itPoll.next();
			int modId  = Integer.parseInt( one.get("modId").toString());
			
			String  pollResult  = one.get("results").toString();
			
			pollResult= pollResult.replace("[","");
			pollResult= pollResult.replace("]","");
			//System.out.println(pollResult);
			String [] pollResultArray =pollResult.split(",");
			
			String pollChoice  = one.get("choice").toString();
			pollChoice= pollChoice.replace("[","");
			pollChoice= pollChoice.replace("]","");
			//System.out.println(pollChoice);
			String [] pollChoiceArray =pollChoice.split(",");
			
			String  pollResultDisplay="[";
			String result="";
			for(int i=0;i<pollResultArray.length;i++)
			{
				//System.out.println(pollChoiceArray[i] );
				//System.out.println(pollResultArray[i]);
				pollResultDisplay=pollResultDisplay+" "+pollChoiceArray[i] +" = "+pollResultArray[i];
				if(i!=pollResultArray.length-1)
				{
					pollResultDisplay=pollResultDisplay+",";
				}
			}
			pollResultDisplay=pollResultDisplay+"]";
			//System.out.println(pollResultDisplay);
			String expired_at =one.get("expired_at").toString();
			String pollID =one.get("id").toString();
			int flag =Integer.parseInt( one.get("flag").toString());
			
			coll = db.getCollection("cmpeTestAssig2");
			if(nowAsISO.compareToIgnoreCase(expired_at)>0 && flag !=1)
			{
			     Document mod=null;
			     try
					{						
					       	
							Bson filter= new Document("id",modId);
							 mod = coll.find(filter).first();
							
					}
					catch (Exception e)
					{
							
					}
			     
				result=mod.get("email").toString();
				result=result+":"+"010030410"+":Poll Result "+pollResultDisplay;
				//System.out.println(result);
				modEmailIdArray.add(result);
				collPoll.updateOne(  new Document("id",pollID),new Document ("$set",new Document("flag",1)));
			}
		}
		
		
		return modEmailIdArray;
	}
}