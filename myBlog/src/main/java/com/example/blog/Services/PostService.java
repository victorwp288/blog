package com.example.blog.Services;

import com.example.blog.Models.Post;

import java.util.List;

public interface PostService {

    List<Post> fetchAllPost();

    int update (Post post);

    Post findById (int id);

    int create (Post post);

    int delete (int id);
}
