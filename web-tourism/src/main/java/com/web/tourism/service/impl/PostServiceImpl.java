package com.web.tourism.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.web.tourism.entity.Post;
import com.web.tourism.entity.User;
import com.web.tourism.reqres.PostResponse;
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
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDto createPost(PostDto postDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setPostImage("default.png");
        post.setCreateDate(new Date());
        post.setUser(user);
        Post addPost = postRepository.save(post);
        return modelMapper.map(addPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setPostImage(postDto.getPostImage());

        Post updatePost = postRepository.save(post);
        return this.modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        /*Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();*/
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
    }


    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        postRepository.delete(post);
    }

	/*@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postRepository.findByTitleContaining(keyword);
		return posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}*/

}