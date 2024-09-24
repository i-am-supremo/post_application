package com.learning.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
    @JsonIgnore
    private Post commentedPost;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User userWhoCommented;

    private LocalDate createdOn;

    private LocalDate modifiedOn;

    private String isEdited;

}
