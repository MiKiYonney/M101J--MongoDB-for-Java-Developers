package com.tengent;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoDBSparkFreemarkStyle {
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkStyle.class, "/");
		
		Spark.get(new Route("/") {
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try {
					MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
					DB dataBase = client.getDB("test");// 连接到test数据库
					DBCollection col = dataBase.getCollection("col");//获取集合
			
					Template template = configuration.getTemplate("hello.ftl");
					DBObject document = col.findOne();
					template.process(document, writer);
					System.out.println(writer);
				} catch (Exception e) {
					halt(500);
					e.printStackTrace();
				} 
				return writer;
			}
		});
	}
}
