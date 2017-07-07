package com.ygj.utils;

import java.io.InputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;

//增 删 改  查 工具类
public class JDBCUtils {

    // 加载驱动
    private static String driver;

    private static String url;

    private static String user;

    private static String password;
    static {
        try {
            InputStream is = JDBCUtils.class.getResourceAsStream("/JDBC.properties");
            Properties prop = new Properties();
            prop.load(is);

            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            //"com.mysql.jdbc.Driver"
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("加载驱动失败");
            e.printStackTrace();
        }

    }

    // 创建链接
    public static Connection getConn() {
        Connection conn = null;
        try {
            
            conn = DriverManager
                    .getConnection(url,user, password);
        } catch (Exception e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }

        return conn;
    }

    // 得到列表
    public static List getList(Class clazz, String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List list = new ArrayList();
        Object obj = null;
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            // System.out.println(metaData);
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                obj = clazz.newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
                }
                list.add(obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);

        }
        return list;
    }

    // 添加 删除 修改
    public static int executeSql(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 0;
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);

            i = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);

        }

        return i;
    }

    // 添加 删除 修改 添加事物
    public static int TranceTxecuteSql(String sqls[]) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 0;
        try {
            conn = getConn();
            // 开启事物
            conn.setAutoCommit(false);
            for (String sql : sqls) {
                ps = conn.prepareStatement(sql);
                i = ps.executeUpdate();
            }

            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);

        }

        return i;
    }

    // 通过id查找对象
    public static Object getObjectById(Class clazz, String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Object obj = null;
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            // System.out.println(metaData);
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                obj = clazz.newInstance();

                for (int i = 1; i <= columnCount; i++) {
                    BeanUtils.copyProperty(obj, metaData.getColumnName(i), rs.getObject(i));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);

        }

        return obj;

    }

    // 查询count
    public static int getCount(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = getConn();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, ps, conn);

        }
        return count;
    }

    // 关闭链接
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
