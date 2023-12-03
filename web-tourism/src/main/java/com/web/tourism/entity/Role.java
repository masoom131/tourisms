package com.web.tourism.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long roleId;

    private String roleName;

    private String roleType;

    @CreationTimestamp
    private Date createDate;

    private Date modifiedDate;
}
