package demo;

import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataAccessLayer 
{
	public  MongoClient mongoClient=null;
	public  MongoCollection<Document> coll=null;
	public  MongoDatabase db=null;
	
	public DataAccessLayer()
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2");
				int count =(int)coll.count();
					ModaretorData mod =new ModaretorData();
					mod.setcounter(count);
			
					
					
					 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
					 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
						
						coll = db.getCollection("cmpeTestAssig2Poll");
						Integer countPoll =(int)coll.count();
							PollData poll =new PollData();
							poll.setCounter(countPoll);	
							//List<Document> pollObj = coll.find().into(new ArrayList<Document>());
							//Iterator<>
							//coll.updateMany( ("$set",new Document("flag",0)));

		}
		catch (Exception e)
		{
			
		}
	}

}
 