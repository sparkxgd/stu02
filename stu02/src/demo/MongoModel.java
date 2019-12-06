package demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * 管理数据的增删查改
 * @author Administrator
 *
 */
public class MongoModel {
	private MongoCollection<Document> collection;
	
	public MongoModel(String databasename,String tablename) {
		MongoDatabase mongoDatabase=MongoConPlugin.mongoClient.getDatabase(databasename);
		this.collection=mongoDatabase.getCollection(tablename);
	}
	/**
	 * 查询
	 */
	public List<Document> find() {
		 FindIterable<Document> findIterable=this.collection.find();
		 MongoCursor<Document> mongoCursor = findIterable.iterator();  
		 
		 List<Document> docs=new ArrayList<Document>();
		 
		 
         while(mongoCursor.hasNext()){  
        	 docs.add(mongoCursor.next());
         } 
         
		return docs;
	}
	/**
	 *条件查询查询
	 */
	public List<Document> find(Document query) {
		
		 FindIterable<Document> findIterable=this.collection.find(query);
		 
		 MongoCursor<Document> mongoCursor = findIterable.iterator();  
		 
		 List<Document> docs=new ArrayList<Document>();
		 
		 
         while(mongoCursor.hasNext()){  
        	 docs.add(mongoCursor.next());
         } 
         
		return docs;
	}
	
}
