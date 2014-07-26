package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by yonney on 2014/6/8.
 */
public class FindCriteriaTest {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("findCriteriaTest");
        collection.drop();
        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x",new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }

        DBObject query = new BasicDBObject("x",0)
                .append("y",new BasicDBObject("$gt",10).append("$lt",90));

        QueryBuilder queryBuilder = QueryBuilder.start("x").is(0)
                .and("y").greaterThan(10).lessThan(90);
        query = queryBuilder.get();

        System.out.println(query);

        System.out.println("Count:");
        long count = collection.count(query);
        System.out.println(count);


        System.out.println("\nFind All:");
        DBCursor cursor = collection.find(query);
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
