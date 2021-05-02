package ru.diasoft.spring.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "TEXT")
    private String text;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "comments", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
}
