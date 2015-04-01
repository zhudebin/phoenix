package org.apache.phoenix.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * Created by zhudebin on 15-3-31.
 */
public class BankStringTest {

    private Connection con = null;

    @Before
    public void before() throws Exception {
//        Properties pro = new Properties();
//        pro.setProperty("phoenix.table.default.store.nulls","true");
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        con = DriverManager.getConnection("jdbc:phoenix:e221,e222,e223:2181");
    }

    @After
    public void after() throws SQLException {
        if(con != null) {
            con.close();
        }
    }

    @Test
    public void saveBankStr() throws SQLException {
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.executeUpdate("upsert into extract_hdfs_05cc6(id, name) values(4,'')");
        con.commit();
    }

    @Test
    public void queryBankStr() throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select id,name from extract_hdfs_05cc6 where id=4");
        while(rs.next()) {
            Integer id = rs.getInt(1);
            String name = rs.getString(2);
            System.out.println("id:" + id + "----name-|" + name + "|--");
        }
    }

    @Test
    public void saveNullStr() throws SQLException {
        con.setAutoCommit(false);
        con.createStatement().executeUpdate("upsert into extract_hdfs_05cc6(id, name) values(3,null)");
        con.commit();
    }

    @Test
    public void saveStr() throws SQLException {
        con.setAutoCommit(false);
        con.createStatement().executeUpdate("upsert into extract_hdfs_05cc6(id, name) values(2,'aa')");
        con.commit();
    }

    @Test
    public void createTable() throws SQLException {
        con.createStatement().executeUpdate("create table extract_hdfs_05cc6 (id BIGINT not null primary key, name varchar(50))");
    }
}
