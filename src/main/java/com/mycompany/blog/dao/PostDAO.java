package com.mycompany.blog.dao;

import com.mycompany.blog.connection.BlogConnection;
import com.mycompany.blog.entity.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements AbstractDAO<Post> {
    private final String SELECT_ALL_ARTICLES = "SELECT * FROM post";
    private final String SELECT_POST_BY_ID = "SELECT * FROM post WHERE id=";


    private Connection connection;

    public PostDAO() {
        connection = BlogConnection.getConnection();
    }

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_ARTICLES)) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getInt("id"));
                    post.setTitle(rs.getString("title"));
                    post.setDescription(rs.getString("description"));
                    post.setText(rs.getString("text"));
                    post.setAuthor(rs.getString("author"));
                    posts.add(post);
                }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Statement Error: " + e);
        } finally {
            BlogConnection.close(connection);
        }
        return posts;
    }

    @Override
    public Post getById(int id) {
        Post post = new Post();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SELECT_POST_BY_ID + id)) {
            while (rs.next()) {
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setText(rs.getString("text"));
                post.setAuthor(rs.getString("author"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Statement Error: " + e);
        } finally {
            BlogConnection.close(connection);
        }
        return post;
    }
}
