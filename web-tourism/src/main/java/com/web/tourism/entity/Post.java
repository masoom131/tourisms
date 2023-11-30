package com.web.tourism.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postTitle;
    private String postContent;
    private String postImage;
    private Date createDate;

    public void setUser(User user) {
    }

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

    /*@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();*/

    /*public void setUser(User user) {
    }*/
}
