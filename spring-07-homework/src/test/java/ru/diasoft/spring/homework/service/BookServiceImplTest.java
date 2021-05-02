package ru.diasoft.spring.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.repository.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис книг")
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("считает авторов")
    void count() {
        when(bookRepository.count()).thenReturn(1);
        assertThat(bookService.count()).isNotZero();
    }

    @Test
    @DisplayName("сохраняет автора")
    void save() {
        Book book = new Book();
        book.setBookName("BookName");
        when(bookRepository.save(book)).thenReturn(book);

        Book book1 = bookService.save(book);
        assertThat(book).isEqualTo(book1);
    }

    @Test
    @DisplayName("обновляет автора")
    void update() {
        Book book = new Book();
        book.setBookName("BookName");
        when(bookRepository.update(book)).thenReturn(book);

        Book book1 = bookService.update(book);
        assertThat(book).isEqualTo(book1);
    }

    @Test
    @DisplayName("ищет авторов")
    void findAll() {
        Book book = new Book();
        book.setBookName("BookName");
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        List<Book> list = bookService.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет автора по ид")
    void findById() {
        Book book = new Book();
        book.setBookName("BookName");
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));

        Optional<Book> optionalBook = bookService.findById(1L);
        assertThat(optionalBook).isPresent();
    }

}