package unit.com.ygj.mongoclient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.ygj.mongoclient.MdbExecutor;
import com.ygj.mongoclient.MongoDBFactory;

public class MongoclientTest {

    @Test
    public void mongoclientTest() {

        MdbExecutor exe = MongoDBFactory.newInstance("192.168.108.129:30001");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\ygj\\zhong\\day03\\student.txt"));
            String rlFirst = br.readLine();
            String[] keys = rlFirst.split(",");
            String rl = null;
            List<DBObject> list = new ArrayList<DBObject>();
            DBObject obj = new BasicDBObject();
            while ((rl = br.readLine()) != null) {
                String[] values = rl.split(",");

                for (int i = 0; i < values.length; i++) {
                    obj.put(keys[i], values[i]);
                }
                list.add(obj);
            }
            // 添加有问题
            WriteResult res = exe.insert("ygj1701", "student", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // DBObject obj = new BasicDBObject("name", "王丹宇")
        // .append("age", 18).append("sex", "男").append("group", "groupOne");

        // WriteResult res = exe.insert("ygj1701", "student", obj);

        // System.out.println(res);

    }
}
