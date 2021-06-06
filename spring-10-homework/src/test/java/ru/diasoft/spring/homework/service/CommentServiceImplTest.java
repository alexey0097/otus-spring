package ru.diasoft.spring.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Comment;
import ru.diasoft.spring.homework.repository.BookRepository;
import ru.diasoft.spring.homework.repository.CommentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис комментариев")
class CommentServiceImplTest {
    
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("считает комментарии")
    void count() {
        when(commentRepository.count()).thenReturn(1L);
        assertThat(commentService.count()).isNotZero();
    }

    @Test
    @DisplayName("сохраняет комментарии")
    void save() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment comment1 = commentService.save(comment);
        assertThat(comment).isEqualTo(comment1);
    }

    @Test
    @DisplayName("обновляет комментарии")
    void update() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment comment1 = commentService.update(comment);
        assertThat(comment).isEqualTo(comment1);
    }

    @Test
    @DisplayName("ищет комментарии")
    void findAll() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.findAll()).thenReturn(Collections.singletonList(comment));

        List<Comment> list = commentService.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет комментарии по ид")
    void findById() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.findById(any())).thenReturn(Optional.of(comment));

        Optional<Comment> optionalComment = commentService.findById(1L);
        assertThat(optionalComment).isPresent();
    }

    @Test
    @DisplayName("ищет комментарии")
    void findAllByBookId() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.findAllByBookId(any())).thenReturn(Collections.singletonList(comment));

        List<Comment> list = commentService.findAllByBookId(1L);
        assertThat(list).isNotEmpty();
    }

    @Test
    void saveByBookId() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("CommentName");
        when(commentRepository.save(comment)).thenReturn(comment);

        Book book = new Book();
        book.setBookName("BookName");
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));

        Comment comment1 = commentService.saveByBookId(1L, comment);
        assertThat(comment).isEqualTo(comment1);
    }
}