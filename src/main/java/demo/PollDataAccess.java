package demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;



import com.mongodb.DBAddress;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class PollDataAccess 
{
	MongoClient mongoClient=null;
	MongoCollection<Document> coll=null;
	MongoDatabase db=null;
	
	
	
	public Object  InsertPoll(PollData pollData,int modID)
	{		
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		
		
		String [] mongoChoice =pollData.getChoice();
		
		List<String> choice = new ArrayList<>();
		List<Integer> results = new ArrayList<>();
		
		for(int i=0;i<mongoChoice.length;i++)
		{
			choice.add(mongoChoice[i]);
			results.add(0);
		}
		
		coll.insertOne(new Document ("id",pollData.getId())
									 .append("question",pollData.getQuestion())
									 .append("started_at",pollData.getStarted_at())
									 .append("expired_at",pollData.getExpired_at())
									 .append("modId",modID)
									 .append("flag",0)
									 .append("choice",choice)
									 .append("results",results)
									 
									 
									 );
		Bson filter= new Document("id",pollData.getId());
		Bson proj =new Document( "_id",0).append("modId",0).append("flag",0).append("results", 0); 

		
		List<Document> mod = coll.find(filter).projection(proj).limit(1).into(new ArrayList<Document>());
		
		Object obj=mod.get(0);
		
		return obj;
	}
	
	public Object  ViewWithOUTResults(String pollID)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		
		Bson filter= new Document("id",pollID);
		Bson proj =new Document( "_id",0).append("modId",0).append("flag",0).append("results", 0);	
		List<Document> mod = coll.find(filter).projection(proj).limit(1).into(new ArrayList<Document>());		
		Object obj=mod.get(0);
		
		return obj;
	}
	
	public Object  ViewWithResults(String pollID)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		
		Bson filter= new Document("id",pollID);
		Bson proj =new Document( "_id",0).append("modId",0).append("flag", 0);	
		List<Document> mod = coll.find(filter).projection(proj).limit(1).into(new ArrayList<Document>());		
		Object obj=mod.get(0);
		
		return obj;
	}
	
	public List<Document>  ListAllPolls(int  modID)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		
		Bson filter= new Document("modId",modID);
		Bson proj =new Document( "_id",0).append("modId",0);	
		List<Document> listOfPolls = coll.find(filter).projection(proj).into(new ArrayList<Document>());		
		
		return listOfPolls;
		
	}
	
	public void DeletePoll(String pollID)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		Bson filter= new Document("id",pollID);
		coll.deleteOne(filter);
	}
	
	public void VoteAPoll(String pollID,int choice)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2Poll");		
				
				
		}
		catch (Exception e)
		{
			
		}
		
		Bson filter= new Document("id",pollID);
		Bson proj =new Document( "_id",0).append("results", 1);
		List<Document> mod = coll.find(filter). projection(proj).limit(1).into(new ArrayList<Document>());		
		
		List<Integer> obj =(List<Integer>) mod.get(0).get("results");
		
		List<Integer> results = new ArrayList<>();
		int s=obj.size();
		for(int i=0;i<s;i++)
		{
			Integer temp=obj.get(i);
			
			if(i==choice)
			{
				temp++;
			}
			results.add(temp);
			
		}
		coll.updateOne(  new Document("id",pollID),new Document ("$set",new Document("results",results)));
		
	}
}
