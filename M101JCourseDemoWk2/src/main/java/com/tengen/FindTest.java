package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by yonney on 2014/6/7.
 */
public class FindTest {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("course");
        DBCollection dbCollection = db.getCollection("findTest");
        dbCollection.drop();
        for (int i = 0; i < 10 ; i++) {
            dbCollection.insert(new BasicDBObject("x", new Random().nextInt(100)));
        }
        System.out.println("Find One:");
        DBObject one = dbCollection.findOne();
        System.out.println(one);

        System.out.println("\nFind All:");
        DBCursor cursor = dbCollection.find();
        try {
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }

        System.out.println("\nCount:");
        long count = dbCollection.count();
        System.out.println(count);
    }
}
