package com.shop.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.shop.bean.Shop;

public class ShopDao {

    private static String driver;

    private static String url;

    private static String user;

    private static String password;

    private Connection conn = null;

    private PreparedStatement ps = null;

    private ResultSet rs = null;

    String sql;

    static {
        try {
            InputStream is = ShopDao.class.getResourceAsStream("/JDBC.properties");
            Properties properties = new Properties();
            properties.load(is);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("加载失败");
            e.printStackTrace();
        }

    }

    public List<Shop> shopList(Shop shop,int i) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            sql = "select * from t_shop limit "+i*3+",3";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Shop> list = new ArrayList<Shop>();
            while (rs.next()) {
                list.add(new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("people"), rs
                        .getString("intro"), rs.getString("time")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);

        }
        return null;
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void shopAddSuccess(Shop shop) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            sql = "insert into t_shop set name = '" + shop.getName() + "', people = '" + shop.getPeople()
                    + "', intro = '" + shop.getIntro() + "', time = '" + shop.getTime() + "'";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);

        }
    }

    public void shopDelete(String ids) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            sql = "delete from t_shop where id in ("+ids+")";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);

        }
    }

    public Shop shopUpdate(String ids) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            sql = "select * from t_shop where id in ("+ids+")";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               return new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("people"), rs
                        .getString("intro"), rs.getString("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);

        }
        return null;
    }

    public void shopUpdateSuccess(Shop shop) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            sql = "update t_shop set name = '" + shop.getName() + "', people = '" + shop.getPeople()
                    + "', intro = '" + shop.getIntro() + "', time = '" + shop.getTime() + "' where id = '"+shop.getId()+"'";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);

        }
    }

    public int shopCount(Shop shop) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            sql = "select * from t_shop";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);

        }
        return 0;
    }

}
