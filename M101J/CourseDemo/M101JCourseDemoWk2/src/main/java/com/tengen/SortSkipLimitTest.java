package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by yonney on 2014/6/8.
 */
public class SortSkipLimitTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("SortSkipLimitTest");
        collection.drop();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            collection.insert(
                    new BasicDBObject("_id",i)
                    .append("start",
                            new BasicDBObject("x",random.nextInt(90)+10)
                            .append("y",random.nextInt(90)+10)
                    ).append("end", new BasicDBObject("x", random.nextInt(90) + 10)
                            .append("y", random.nextInt(90) + 10)));
        }

       /* QueryBuilder queryBuilder = QueryBuilder.start("start.x").greaterThan(50);
        DBObject query = queryBuilder.get();

        System.out.println(query);*/


        System.out.println("\nFind All:");
        DBCursor cursor = collection.find().sort(new BasicDBObject("_id",-1)).skip(2).limit(2);
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
