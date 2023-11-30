package com.web.tourism.controller;
import java.util.List;

import com.web.tourism.reqres.ApiResponse;
import com.web.tourism.util.TourismConstants;
import com.web.tourism.reqres.PostResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.tourism.payload.PostDto;
import com.web.tourism.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    /*@Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;*/

    //create post through userId & categoryId
    @PostMapping("/user/{userId}")
    @ApiOperation("create post by 'body'")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId) {
        PostDto createPostDto = postService.createPost(postDto, userId);
        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    //update post through postId
    @PutMapping("/update/{postId}")
    @ApiOperation("update post by 'commentId & body'")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long postId) {
        PostDto updatePostDto = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePostDto, HttpStatus.OK);
    }

    //delete post through postId
    @DeleteMapping("/delete/{postId}")
    //@ApiOperation("delete post by 'commentId'")
    public ApiResponse deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ApiResponse("Post is deleted successfully!!!!", true);
    }

    //get post through postId
    @GetMapping("/post/{postId}")
    //@ApiOperation("get post by 'commentId'")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        PostDto postDto = postService.getPost(postId);
        return ResponseEntity.ok(postDto);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<PostDto> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }

    //get posts through size & number
    @GetMapping("/all/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value="pageNumber", defaultValue= TourismConstants.PAGE_NUMBER, required=false) Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue= TourismConstants.PAGE_SIZE, required=false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue= TourismConstants.SORT_BY, required=false) String sortBy,
            @RequestParam(value="sortDir", defaultValue= TourismConstants.SORT_DIR, required=false) String sortDir) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(postResponse);
    }

    //get posts through userId
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }


    //search posts through title
	/*@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostDto> result = postService.searchPosts(keywords);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}*/

}
