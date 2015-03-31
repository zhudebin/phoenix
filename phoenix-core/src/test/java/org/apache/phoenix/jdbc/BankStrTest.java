package org.apache.phoenix.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhudebin on 15-3-31.
 */
public class BankStrTest {

    private Connection con;

    @Before
    public void before() throws Exception {
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        con = DriverManager.getConnection("jdbc:phoenix:e221,e222,e223:2181");
    }

    @Test
    public void saveNull() throws Exception {
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.executeUpdate("upsert into extract_hdfs_05cc6(id, name) values(3,null)");
        con.commit();
    }

    @After
    public void after() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
