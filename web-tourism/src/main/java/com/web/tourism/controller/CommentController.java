package com.web.tourism.controller;
import com.web.tourism.reqres.ApiResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.tourism.payload.CommentDto;
import com.web.tourism.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    //@ApiOperation("create comment by 'body'")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId) {
        CommentDto createComment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comment/{commentId}")
    //@ApiOperation("delete comment by 'commentId'")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment deleted successfully!!!", true));
    }

}
