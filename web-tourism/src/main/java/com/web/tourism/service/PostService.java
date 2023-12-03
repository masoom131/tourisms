package com.web.tourism.service;

import com.web.tourism.entity.Post;
import com.web.tourism.entity.User;

public interface PostService {

    String addPost(Post post);

    String updatePost(Post post, Long postId);

    String deletePost(Long postId);

    String getPost(Long postId);

    String getAllPost();

    /*PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    List<PostDto> getPostsByUser(Long userId);*/

}
