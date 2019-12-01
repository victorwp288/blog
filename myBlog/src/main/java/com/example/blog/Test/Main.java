package com.example.blog.Test;

import com.example.blog.Services.PostServiceImpl;
import com.example.blog.Services.UserServiceImpl;

public class Main {
    public static void main(String[] args){

        UserServiceImpl us = new UserServiceImpl();
        PostServiceImpl up = new PostServiceImpl();

        System.out.println(us.fetchAllUsers().toString());
        System.out.println(us.findById(1).toString());
        System.out.println(us.authenticate("lotte", "1234").toString());



        System.out.println(up.fetchAllPost().toString());
        System.out.println(up.findById(1).toString());




    }
}

