package com.web.tourism.service;

import com.web.tourism.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long postId);

    void deleteComment(Long commentId);

}