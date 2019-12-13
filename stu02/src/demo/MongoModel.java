package demo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * �������ݵ���ɾ���
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
	 * ��ѯ
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
	 *������ѯ��ѯ
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
	/**
	 * ����
	 * @param data
	 */
	public void insertOne(Document data) {
		collection.insertOne(data);
	}
	/**
	 * ɾ��
	 */
	public void deleteOne(Document query) {
		collection.deleteOne(query);
	}
	/**
	 * ����
	 */
	public void updateOne(Document data) {
		collection.updateOne(Filters.eq("no", data.get("no")), new Document("$set",data));
	}
}
