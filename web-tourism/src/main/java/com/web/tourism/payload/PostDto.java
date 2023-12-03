package com.web.tourism.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    @NotEmpty
    @Size(min=4, message="title at least 4 characters !!")
    private String postTitle;

    @NotEmpty
    @Size(min=25, message="comment at least 25 characters !!")
    private String postContent;

    private String postImage;

    private Date postDate;

    private UserDto user;

    //private Set<CommentDto> comments = new HashSet<>();

}
