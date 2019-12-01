package com.example.blog.Services;

import com.example.blog.Models.Post;
import com.example.blog.Repositories.ProjectVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public List<Post> fetchAllPost() {
        List<Post> post = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM blog.post");

            while (rs.next()) {
                Post up = new Post();

                up.setId(rs.getInt(1));
                up.setTitle(rs.getString(2));
                up.setBody(rs.getString(3));
                up.setDt(rs.getDate(4));
                up.setUserId(rs.getInt(5));

                post.add(up);

            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public int update(Post post) {
        try {
            Connection con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
            PreparedStatement pstmt = con.prepareStatement("UPDATE blog.post SET title = ?, body = ? WHERE id = ?;");


            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getBody());
            pstmt.setInt(3, post.getId());
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    @Override
    public Post findById(int id) {
        Post post = new Post();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
            pstmt = con.prepareStatement("SELECT * FROM blog.post Where id = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                post.setId(rs.getInt(1));
                post.setTitle(rs.getString(2));
                post.setBody(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        return post;
    }

    @Override
    public int create(Post post) {
        try {
            Connection con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO blog.post (title, body, user_id) VALUES (?,?,?) ");

            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getBody());
            pstmt.setInt(3, post.getUserId());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {

            try {
                Connection con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
                PreparedStatement pstmt = con.prepareStatement("DELETE FROM blog.post Where id = ?");
                pstmt.setInt(1, id);
                return pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
        }
    }
