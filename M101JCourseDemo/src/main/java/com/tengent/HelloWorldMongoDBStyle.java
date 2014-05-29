package com.tengent;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class HelloWorldMongoDBStyle {
	public static void main(String[] args) {
		try{
			MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
			DB dataBase = client.getDB("test");// 连接到test数据库
			DBCollection col = dataBase.getCollection("things");//获取集合
			DBObject document = col.findOne();
			System.out.println(document);
		}catch (Exception e) {
		}
	}
}
