package ru.diasoft.spring.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.homework.entity.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql({"classpath:schema.sql"})
@Import({CommentRepositoryImpl.class})
@DisplayName("Репозиторий комментариев")
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("считает количество комментариев")
    void count() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        commentRepository.save(comment);

        Long count = commentRepository.count();
        assertThat(count).isNotZero();
    }

    @Test
    @DisplayName("ищет список комментариев")
    void findAll() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        commentRepository.save(comment);

        List<Comment> list = commentRepository.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет комментарий по идентификатору")
    void findById() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        commentRepository.save(comment);

        Optional<Comment> optional = commentRepository.findById(comment.getCommentId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("создает комментарий")
    void save() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        commentRepository.save(comment);

        Optional<Comment> optional = commentRepository.findById(comment.getCommentId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("обновляет комментарий")
    void update() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        commentRepository.save(comment);

        Optional<Comment> optional = commentRepository.findById(comment.getCommentId());
        assertThat(optional).isPresent();

        optional.ifPresent(genre1 -> {
            comment.setUserName("newName");
            commentRepository.update(genre1);

            Optional<Comment> optional2 = commentRepository.findById(genre1.getCommentId());
            assertThat(optional2).isPresent();

            optional2.ifPresent( genre2 -> {
                assertThat(genre2).isEqualTo(genre1);
            });
        });
    }

    @Test
    @DisplayName("удаляет комментарий")
    void deleteById() {
        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        commentRepository.save(comment);

        Optional<Comment> optional = commentRepository.findById(comment.getCommentId());
        assertThat(optional).isPresent();

        commentRepository.deleteById(comment.getCommentId());

        Optional<Comment> optional2 = commentRepository.findById(comment.getCommentId());
        assertThat(optional2).isNotPresent();
    }
}