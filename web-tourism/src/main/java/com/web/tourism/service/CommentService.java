package com.web.tourism.service;

import com.web.tourism.entity.Comment;

public interface CommentService {

    String addComment(Comment comment);

    String updateComment(Comment comment, Long commentId);

    String deleteComment(Long commentId);

    String getComment(Long commentId);

    String getAllComment();

}
