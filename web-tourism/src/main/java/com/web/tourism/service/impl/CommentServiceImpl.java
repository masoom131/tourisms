package com.web.tourism.service.impl;

import com.web.tourism.service.CommentService;
import com.web.tourism.entity.Comment;
import com.web.tourism.entity.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.payload.CommentDto;
import com.web.tourism.repository.CommentRepository;
import com.web.tourism.repository.PostRepository;
import com.web.tourism.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        return modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
        commentRepository.delete(comment);

    }
}
