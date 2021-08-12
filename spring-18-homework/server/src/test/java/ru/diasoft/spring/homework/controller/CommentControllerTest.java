package ru.diasoft.spring.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Comment;
import ru.diasoft.spring.homework.service.AuthorService;
import ru.diasoft.spring.homework.service.CommentService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CommentController.class)
@DisplayName("Контроллер для комментариев")
class CommentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @DisplayName("возвращает список комментариев по идентификатору книги")
    @Test
    void findAllCommentsByBookId() throws Exception {

        Book book = new Book();
        book.setBookName("testNameFindById");

        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        comment.setBook(book);

        when(commentService.findAllByBookId(any())).thenReturn(List.of(comment));

        final String expectedResponseBody = objectMapper.writeValueAsString(List.of(comment));

        final MvcResult mvcResult = mockMvc.perform(get("/api/v1/book/1/comments")
            .contentType("application/json")
            .content(expectedResponseBody))
            .andExpect(status().isOk())
            .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @DisplayName("сохраняет комментарий по идентификатору книги")
    @Test
    void saveCommentsByBookId() throws Exception {
        Book book = new Book();
        book.setBookName("testNameFindById");

        Comment comment = new Comment();
        comment.setUserName("userName");
        comment.setText("text");
        comment.setBook(book);

        when(commentService.saveByBookId(any(), any())).thenReturn(comment);

        final String expectedResponseBody = objectMapper.writeValueAsString(comment);

        final MvcResult mvcResult = mockMvc.perform(post("/api/v1/book/1/comment")
                .contentType("application/json")
                .content(expectedResponseBody))
                .andExpect(status().isOk())
                .andReturn();

        final String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

}