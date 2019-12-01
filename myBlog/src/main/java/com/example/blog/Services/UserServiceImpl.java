package com.example.blog.Services;

import com.example.blog.Models.User;
import com.example.blog.Repositories.ProjectVariables;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<User> fetchAllUsers() {

        List<User> users = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM blog.users;");
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAdmin(rs.getString(4));
                users.add(user);
            }

        } catch (SQLException e) {
            log.info("ARRRGH " + this.getClass());
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id){
        for (User current : fetchAllUsers()) {
            if(id == current.getId()){
                return  current;
            }
        }
        return null;
    }

    @Override
    public User authenticate (String username, String password) {
        try {
            Connection con = DriverManager.getConnection(ProjectVariables.getUrl(), ProjectVariables.getUsername(), ProjectVariables.getPassword());
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM blog.users WHERE username = ? AND passwords = ?");


            pstmt.setString(1, username);
            pstmt.setString(2, password);
            log.info("Getting username and password in: " + this.getClass());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setAdmin(rs.getString(4));

                log.info("Login succeeded in: " + this.getClass());

                return user;

            } else {
                log.info("Login failed in: " + this.getClass());
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
