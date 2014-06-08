package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by yonney on 2014/6/8.
 */
public class UpdateRemoveTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("UpdateRemoveTest");
        collection.drop();;

        List<String> names = Arrays.asList("alise","jan","smith","yonney");
        for (String name : names){
            collection.insert(new BasicDBObject("_id",name));
        }

        collection.update(new BasicDBObject("_id","alise"),new BasicDBObject("age",40));
        collection.update(new BasicDBObject("_id","frank"),
                new BasicDBObject("$set",new BasicDBObject("gender","F")),true,false);
        System.out.println("\nFind All:");
        DBCursor cursor = collection.find();
        try {
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }

    }
}
