package ru.diasoft.spring.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

}
