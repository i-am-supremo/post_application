package com.learning.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate createdOn;
    private LocalDate modifiedOn;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "commentedPost", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Like> likes;

}
