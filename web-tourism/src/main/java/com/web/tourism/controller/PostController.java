package com.web.tourism.controller;
import java.util.List;

import com.web.tourism.entity.Post;
import com.web.tourism.entity.User;
import com.web.tourism.reqres.ApiResponse;
import com.web.tourism.util.WebTourismUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.tourism.payload.PostDto;
import com.web.tourism.service.PostService;

@RestController
@RequestMapping("/api/auth/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private WebTourismUtil webTourismUtil;

    /*@Autowired
    private FileService fileService;*/

   /* @Value("${project.image}")
    private String path;*/

    @PostMapping("/add")
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        String addPost = postService.addPost(post);
        return webTourismUtil.responseStatus(addPost);
    }

    @PutMapping("/up/{postId}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable Long postId) {
        String updatePost = postService.updatePost(post, postId);
        return webTourismUtil.responseStatus(updatePost);
    }

    @DeleteMapping("/del/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        String deletePost = postService.deletePost(postId);
        return webTourismUtil.responseStatus(deletePost);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<String> getPost(@PathVariable Long postId) {
        String fetchPost = postService.getPost(postId);
        return webTourismUtil.responseStatus(fetchPost);
    }

    @GetMapping("/get/all")
    public ResponseEntity<String> getAllPost() {
        String fetchAllPost = postService.getAllPost();
        return webTourismUtil.responseStatus(fetchAllPost);
    }

    /*@GetMapping("/all/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value="pageNumber", defaultValue= TourismConstants.PAGE_NUMBER, required=false) Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue= TourismConstants.PAGE_SIZE, required=false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue= TourismConstants.SORT_BY, required=false) String sortBy,
            @RequestParam(value="sortDir", defaultValue= TourismConstants.SORT_DIR, required=false) String sortDir) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(postResponse);
    }*/

    /*@GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }*/


    //search posts through title
    /*@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostDto> result = postService.searchPosts(keywords);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}*/


}

