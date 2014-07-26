package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.rmi.activation.UnknownObjectException;

/**
 * Created by yonney on 2014/6/7.
 */
public class InsertTest {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("insertTest");

        DBObject doc = new BasicDBObject().append("x", 1);
        System.out.println(doc);
        collection.insert(doc);
        System.out.println(doc);
    }
}
