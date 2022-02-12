package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(
//            name = "blogPost_sequence",
//            sequenceName = "blogPost_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "blogPost_sequence"
//    )
    private Long id;
    private String title;
    private String content;
    private Boolean star;

    @OneToMany(cascade = CascadeType.ALL)// fetch = FetchType.LAZY)
    @JoinColumn(name = "blogPost_id", referencedColumnName = "id")
    private List<Comment> comments;

}
