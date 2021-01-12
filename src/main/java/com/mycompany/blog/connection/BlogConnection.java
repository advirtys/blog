package com.mycompany.blog.connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BlogConnection {

    public static Connection getConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/blog");
            return ds.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException("Context Error: " + e);
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error: " + e);
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Not close connection: " + e);
            }
        }
    }
}
