package demo;


//import org.apache.cassandra.transport.Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.WriteResult;
import com.mongodb.client.*;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class ModeratorsDataAccess 
{
	MongoClient mongoClient=null;
	MongoCollection<Document> coll=null;
	MongoDatabase db=null;

	public Object  InsertModerator(ModaretorData moderator)
	{
		
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
				
				coll = db.getCollection("cmpeTestAssig2");		
				
		}
		catch (Exception e)
		{
				
		}
		
		
		System.out.println("hii");
		coll.insertOne(
						new Document ("id",moderator.getId())
									 .append("name",moderator.getName())
									 .append("email",moderator.getEmail())
									 .append("password",moderator.getPassword())
									 .append("created_at",moderator.getCreated_at())
									 );
		
		Bson filter= new Document("id",moderator.getId());
		Bson proj =new Document("_id",0); 
		
		List<Document> mod = coll.find(filter).projection(proj).limit(1).into(new ArrayList<Document>());
		//MongoCursor cur= (MongoCursor) mod;
		Object obj=mod.get(0);
		
		return obj;
	}
	
	public Object GetModerator(int id)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
			 coll = db.getCollection("cmpeTestAssig2");		
				
		}
		catch (Exception e)
		{
			
		}
		
		Bson filter= new Document("id",id);
		Bson proj =new Document("_id",0); 
		
		
		
		List<Document> mod = coll.find(filter).projection(proj).limit(1).into(new ArrayList<Document>());
		Object obj=mod.get(0);
		System.out.println(mod	);
		return obj;
	}
	
	public Object updateModerator(int id,String name,String email,String password)
	{
		try
		{
			 mongoClient = new MongoClient(new MongoClientURI("mongodb://root:test@ds059888.mongolab.com:59888/cmpe273db"));
			 db = mongoClient.getDatabase ("cmpe273db").withReadPreference(ReadPreference.primary());
			 coll = db.getCollection("cmpeTestAssig2");		
				
		}
		catch (Exception e)
		{
			
		}
		if(name!=null)
		{
			//Bson fillter = new Document("id",id); 
			//Bson update =new Document ("$set",new Document("name",name));
			coll.updateOne(  new Document("id",id),new Document ("$set",new Document("name",name)));
		}
		if(email!=null)
		{
			//Bson fillter = new Document("id",id); 
			//Bson update =new Document ("$set",new Document("name",name));
			coll.updateOne(  new Document("id",id),new Document ("$set",new Document("email",email)));
		}
		if(password!=null)
		{
			//Bson fillter = new Document("id",id); 
			//Bson update =new Document ("$set",new Document("name",name));
			coll.updateOne(  new Document("id",id),new Document ("$set",new Document("password",password)));
		}
		Bson filter= new Document("id",id);
		Bson proj =new Document("_id",0); 		
		
		List<Document> mod = coll.find(filter).projection(proj).limit(1).into(new ArrayList<Document>());
		Object obj=mod.get(0);
		System.out.println(mod	);
		return obj;
	
	}
}
