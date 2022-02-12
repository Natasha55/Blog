package com.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

//    private Long postId;
    @Id
//    @SequenceGenerator(
//            name = "comment_sequence",
//            sequenceName = "comment_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "comment_sequence"
//    )
    private Long commentId;
    private String commentText;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //    @CreatedDate
    private LocalDateTime creationDate;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private BlogPost blogPost;

}
