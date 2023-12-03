package com.web.tourism.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String commentContent;

    @CreationTimestamp
    private Date createDate;

    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post posts;

    public void setPost(Post post) {
    }
}
