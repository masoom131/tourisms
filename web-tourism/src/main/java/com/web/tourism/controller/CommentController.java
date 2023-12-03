package com.web.tourism.controller;
import com.web.tourism.entity.Comment;
import com.web.tourism.reqres.ApiResponse;
import com.web.tourism.service.CommentService;
import com.web.tourism.util.WebTourismUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private WebTourismUtil webTourismUtil;

    @PostMapping("/add")
    //@ApiOperation("create comment by 'body'")
    public ResponseEntity<String> addComment(@RequestBody Comment comment) {
        String addComment = commentService.addComment(comment);
        return webTourismUtil.responseStatus(addComment);

    }

    @PutMapping("/up/{commentId}")
    //@ApiOperation("update comment by 'body' id")
    public ResponseEntity<String> updateComment(@RequestBody Comment comment, @PathVariable Long commentId) {
        String updateComment = commentService.updateComment(comment, commentId);
        return webTourismUtil.responseStatus(updateComment);
    }

    @DeleteMapping("/del/{commentId}")
    //@ApiOperation("delete comment by 'commentId'")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        String deleteComment = commentService.deleteComment(commentId);
        return webTourismUtil.responseStatus(deleteComment);
    }

    @GetMapping("/get/{commentId}")
    //@ApiOperation("fetch comment by 'commentId'")
    public ResponseEntity<String> getComment(@PathVariable Long commentId) {
        String getComment = commentService.getComment(commentId);
        return webTourismUtil.responseStatus(getComment);
    }

    @GetMapping("/get/all")
    //@ApiOperation("fetch all comment by ''")
    public ResponseEntity<String> getAllComment() {
        String getAllComment = commentService.getAllComment();
        return webTourismUtil.responseStatus(getAllComment);
    }

}
