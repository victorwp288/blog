package com.example.blog.Services;

import com.example.blog.Models.User;

import java.util.List;

public interface UserService {

    User authenticate(String nickName, String password);

    User findById(int id);

    List<User> fetchAllUsers();
}

