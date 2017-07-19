package mongoclient;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyObject {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "wangdanyu");
        map.put("age", 18);

        try {
            Object obj = mapToJavabean(map, Person.class);
            if (obj instanceof Person) {
                Person person = (Person) obj;
                System.out.println(person.getName());
                System.out.println(person.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object mapToJavabean(Map<?, ?> map, Class<?> c) throws Exception, IllegalAccessException {

        if (map == null || null == c) {
            
            return null;
        }
        Method[] ms = c.getMethods();
        Object obj = c.newInstance();
        for (Method mas : ms) {
            String methodName = mas.getName();
            if (methodName.startsWith("set")) {
                String key = methodName.substring(3).toLowerCase();
                mas.invoke(obj, map.get(key));
            }
        }
        return obj;
    }
}
