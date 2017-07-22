package com.ygj.concept.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConceptDao {

    private static String url;

    private static String dbName;

    private static MdbExecutor exe;

    private static MongoClient mongoClient;

    static {

        try {

            InputStream is = ConceptDao.class.getResourceAsStream("/mongo.properties");

            Properties properties = new Properties();

            properties.load(is);

            url = properties.getProperty("url");

            dbName = properties.getProperty("dbName");

            exe = MongoDBFactory.newInstance(url);

            mongoClient = new MongoClient(url);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // 模板新闻保存
    public void addPage(String tableName, Map<String, Object> map) {

        DBObject obj = new BasicDBObject(map);

        exe.insert(dbName, tableName, obj);

    }

    
    // 查询基础方法
    public List<DBObject> find(String tableName, Map<String, Object> map, List<String> list,
            String sortFieldName, int sort, int begin, int size) {

        DB db = mongoClient.getDB(dbName);

        DBCollection collection = db.getCollection(tableName);

        DBObject query = null;

        if (map == null) {
            query = new BasicDBObject();
        } else {
            query = new BasicDBObject(map);
        }
        DBCursor find = null;
        if (sortFieldName != null) {
            DBObject sortField = new BasicDBObject();
            sortField.put(sortFieldName, sort);
            if (list == null) {
                find = collection.find(query).sort(sortField).skip(begin).limit(size);
            } else {
                BasicDBObject keys = new BasicDBObject();
                for (String li : list) {
                    keys.put(li, 1);
                }
                find = collection.find(query, keys).sort(sortField).skip(begin).limit(size);
            }
        } else {
            if (list == null) {
                find = collection.find(query).skip(begin).limit(size);
            } else {
                BasicDBObject keys = new BasicDBObject();

                for (String li : list) {
                    keys.put(li, 1);
                }
                find = collection.find(query, keys).skip(begin).limit(size);
            }
        }
        List<DBObject> listAll = new ArrayList<DBObject>();

        while (find.hasNext()) {
            DBObject obj = find.next();
            listAll.add(obj);
        }
        return listAll;
        // BasicDBObject keys = new BasicDBObject();
        //
        // for (String li : list) {
        //
        // keys.put(li, 1);
        // }
        //
        // DBObject q = new BasicDBObject();
        //
        // Set<String> keySet = map.keySet();
        //
        // for (String key : keySet) {
        // q.put(key, map.get(key));
        // }
        //
        // DBObject o = new BasicDBObject(sortFieldName, 1);
        //
        // DBCursor find = collection.find(q, keys).sort(o);
        //
        // // DBCursor find = exe.find(dbName, tableName, 0, Integer.MAX_VALUE, sortFieldName, 1, q,
        // // keys);
        //
        // List<DBObject> listAll = new ArrayList<DBObject>();
        //
        // while (find.hasNext()) {
        //
        // DBObject obj = find.next();
        //
        // listAll.add(obj);
        // }

    }

    public void update(String tableName, Map<String, Object> map1, Map<String, Object> map2) {

        DBObject q = new BasicDBObject(map1);

        DBObject o = new BasicDBObject();

        Set<String> keys = map2.keySet();

        for (String key : keys) {

            o.put(key, map2.get(key));
        }
        String mql = "db." + tableName + ".update(" + q + ",{$set:" + o + "},true,false)";
        exe.excuteMQL(dbName, mql);

    }

    @Test
    public void Test() {

        ConceptDao cdi = new ConceptDao();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("title", "社区活动");

        map.put("desc", "早上好。。。。。。");

        List<String> list1 = new ArrayList<String>();

        list1.add("weight");
        list1.add("name");

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("entity", "1");
        map1.put("initalState", "1");

        List<DBObject> list = cdi.find("report", map1, list1, "weight", 1, 0, Integer.MAX_VALUE);

        for (DBObject li : list) {

            System.out.println(li);
        }

        // cdi.addPage("concept", "article", map);

    }

}
