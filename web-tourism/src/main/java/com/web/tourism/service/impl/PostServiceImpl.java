package com.web.tourism.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.web.tourism.entity.Post;
import com.web.tourism.entity.User;
import com.web.tourism.util.WebTourismUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.payload.PostDto;
import com.web.tourism.repository.PostRepository;
import com.web.tourism.repository.UserRepository;
import com.web.tourism.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebTourismUtil webTourismUtil;

    @Override
    public String addPost(Post post) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try {
            Post addPost = postRepository.save(post);
            message_en = "Post Added Successfully.";
        }catch (Exception e){
            message_en = "Exception While Adding Post!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String updatePost(Post post, Long postId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Post updatePost = postRepository.findById(postId)
                    .orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
            updatePost.setPostTitle(post.getPostTitle());
            updatePost.setPostContent(post.getPostContent());
            updatePost.setPostImage(post.getPostImage());
            updatePost.setModofiedDate(new Date());
            postRepository.save(updatePost);
            message_en = "Post Updated Successfully.";
        }catch (Exception e){
            message_en = "Exception While Update Post!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String deletePost(Long postId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Post deletePost = postRepository.findById(postId)
                    .orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
            postRepository.delete(deletePost);
            message_en = "Post Deleted Successfully!";
        }catch (Exception e){
            message_en = "Exception While Delete Post!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getPost(Long postId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Post findPost = postRepository.findById(postId)
                    .orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
            Map<String, Post> map = new HashMap();
            map.put("post", findPost);
            jsonResponse.put("data",map);
            message_en = "Post fetched By Id Successfully!";
        }catch (Exception e){
            message_en = "Exception While Finding Post!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getAllPost() {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            List<Post> posts = postRepository.findAll();
            for (Post post : posts){
                Map<String, Post> map = new HashMap<>();
                map.put("post", post);
                jsonArray.put(map);
                jsonResponse.put("data", jsonArray);
                message_en = "Post fetched successfully!!!";
            }
        }catch (Exception e){
            message_en = "Exception While Fetching Posts!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

}
    /*@Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }*/



    /*@Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        *//*Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();*//*

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost =  postRepository.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream()
                .map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }*/

    /*@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postRepository.findByTitleContaining(keyword);
		return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}*/
