package com.web.tourism.service;

import com.web.tourism.payload.PostDto;
import com.web.tourism.reqres.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Long userId);

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    PostDto getPost(Long postId);

    List<PostDto> getPosts();

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    List<PostDto> getPostsByUser(Long userId);

}
