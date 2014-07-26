import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by yonney on 2014/6/8.
 */
public class wk2App {
    public static void main( String[] args ) throws UnknownHostException {
        MongoClient client = new MongoClient();

        DB database = client.getDB("students");
        DBCollection collection = database.getCollection("grades");

        DBCursor c = collection.find(new BasicDBObject("type","homework"))
                .sort(new BasicDBObject("student_id", 1).append("score",1));

        int lastStudent = -1;
        while (c.hasNext()) {
            DBObject grade = c.next();
            int currentStudent = Integer.parseInt(grade.get("student_id").toString());
            if (currentStudent != lastStudent) {
                lastStudent = currentStudent;
                collection.remove(new BasicDBObject("_id", grade.get("_id")));
            }
        }
    }
}
