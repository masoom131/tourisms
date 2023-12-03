package com.web.tourism.service.impl;

import com.web.tourism.service.CommentService;
import com.web.tourism.entity.Comment;
import com.web.tourism.entity.Post;
import com.web.tourism.util.WebTourismUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tourism.exception.ResourceNotFoundException;
import com.web.tourism.payload.CommentDto;
import com.web.tourism.repository.CommentRepository;
import com.web.tourism.repository.PostRepository;
import com.web.tourism.service.CommentService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private WebTourismUtil webTourismUtil;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addComment(Comment comment) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try {
            Comment addComment = commentRepository.save(comment);
            message_en = "Comment Added Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Adding Comment!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String updateComment(Comment comment, Long commentId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try {
            Comment updateComment = commentRepository.findById(commentId)
                    .orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
            updateComment.setCommentContent(comment.getCommentContent());
            updateComment.setCreateDate(new Date());
            message_en = "Comment Updated Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Updating Comment!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String deleteComment(Long commentId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
            commentRepository.delete(comment);
            message_en = "Comment Delete Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While Adding Comment!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getComment(Long commentId) {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        try{
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(()->new ResourceNotFoundException("Comment", "Comment Id", commentId));
            Map<String, Comment> map = new HashMap<>();
            map.put("comment", comment);
            jsonResponse.put("date", map);
            message_en = "Comment fetched Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While fetching the Comment!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }

    @Override
    public String getAllComment() {
        String message_en="";
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            List<Comment> comments = commentRepository.findAll();
            for(Comment comment : comments){
                Map<String, Comment> map = new HashMap<>();
                map.put("comment", comment);
                jsonArray.put(map);
                jsonResponse.put("date", jsonArray);
            }
            message_en = "Comment fetched Successfully!!!";
        }catch (Exception e){
            message_en = "Exception While fetching the Comment!";
        }
        return webTourismUtil.setJsonResponse(true, jsonResponse, message_en);
    }
}
