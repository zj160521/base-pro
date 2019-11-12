package com.simes.storm.util.mongo;

import java.util.List;

import org.apache.storm.mongodb.common.MongoDBClient;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.UpdateOptions;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
public class SystemMongoClient extends MongoDBClient {
	private MongoClient client;
    private MongoCollection<Document> collection;

    public SystemMongoClient(String url, String collectionName) {
    	super(url, collectionName);
        MongoClientURI uri = new MongoClientURI(url);
        this.client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        this.collection = db.getCollection(collectionName);
    }

	public MongoCollection<Document> getCollection() {
		return collection;
	}
	 /**
     * Inserts one or more documents.
     * This method is equivalent to a call to the bulkWrite method.
     * The documents will be inserted in the order provided, 
     * stopping on the first failed insertion. 
     * 
     * @param documents
     */
	 @Override
    public void insert(List<Document> documents, boolean ordered) {
        InsertManyOptions options = new InsertManyOptions();
        if (!ordered) {
            options.ordered(false);
        }
        collection.insertMany(documents, options);
    }

    /**
     * Update all documents in the collection according to the specified query filter.
     * When upsert set to true, the new document will be inserted if there are no matches to the query filter.
     * 
     * @param filter
     * @param update
     * @param upsert
     */
    @Override
    public void update(Bson filter, Bson update, boolean upsert) {
        UpdateOptions options = new UpdateOptions();
        if (upsert) {
            options.upsert(true);
        }
        collection.updateMany(filter, update, options);
    }

    /**
     * Closes all resources associated with this instance.
     */
    @Override
    public void close(){
        client.close();
    }
}
