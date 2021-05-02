package ru.diasoft.spring.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(
        name = "BooksGraph",
        attributeNodes = {
                @NamedAttributeNode(value = "author"),
                @NamedAttributeNode(value = "genre"),
                @NamedAttributeNode(value = "comments")
        }
)
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;
    @Column(name = "BOOK_NAME")
    private String bookName;
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;
    @ManyToMany(targetEntity = Comment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "BOOK_COMMENTS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID")
    )
    private List<Comment> comments = new ArrayList<>();

}
