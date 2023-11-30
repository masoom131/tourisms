package com.web.tourism.entity;

import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post posts;

    public void setPost(Post post) {
    }
}