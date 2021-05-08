package ru.diasoft.spring.homework.entity;

import lombok.*;

import javax.persistence.*;

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
    @ManyToOne
    @JoinTable(name = "BOOK_COMMENTS",
        joinColumns = @JoinColumn(name = "COMMENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
    private Book book;
}
