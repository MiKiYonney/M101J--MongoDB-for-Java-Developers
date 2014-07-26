import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Created by yonney on 2014/6/15.
 */
public class hw3_1 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("school");
        DBCollection collection = db.getCollection("students");

        removeLowest(collection);

        long count = collection.count();
        System.out.println(count);
        DBObject result = collection.findOne(new BasicDBObject("_id", 100));
        System.out.println(result);

    }

    private static void removeLowest(DBCollection collection) {
        DBCursor cursor = collection.find(new BasicDBObject()).sort(new BasicDBObject("student_id", 1));

        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                int removeIdx = -1;

                List<Map> scores = (List<Map>) cur.get("scores");
                int _id = (Integer)cur.get("_id");
                if(100==_id){
                    System.out.println( scores);
                }
                Double lower = 100.00;
                for (int i = 0;i < scores.size();i++){
                    Map score = scores.get(i);
                    if("homework".equals((String)score.get("type")) &&
                            (Double)score.get("score") < lower ){
                        lower = (Double)score.get("score");
                        removeIdx = i;
                    }
                }
                scores.remove(removeIdx);
                System.out.println(scores+"||"+lower);
                collection.update(new BasicDBObject("_id", cur.get("_id")),
                        new BasicDBObject("$set", new BasicDBObject("scores", scores)),true,false);
            }
        } finally {
            cursor.close();
        }
    }

}