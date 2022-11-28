package com.intel.ebsqa.draco.utility;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBRepository<T> {

	public DBCollection _collection;

	public MongoDBRepository(String collectionName) {

		MongoClient objMongoClient = new MongoClient("10.109.80.17", 27017);
		@SuppressWarnings("deprecation")
		DB objDB = objMongoClient.getDB("PSGTestData");
		_collection = objDB.getCollection(collectionName);
	}

}
