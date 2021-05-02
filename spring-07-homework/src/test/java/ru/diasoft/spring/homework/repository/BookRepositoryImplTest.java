package ru.diasoft.spring.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.homework.config.JdbcTestConfiguration;
import ru.diasoft.spring.homework.entity.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Sql({"classpath:schema.sql"})
@Import({JdbcTestConfiguration.class, BookRepositoryImpl.class})
@DisplayName("Репозиторий книг")
@Transactional(propagation = Propagation.SUPPORTS)
class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("считает количество книг")
    void count() {
        Book book = new Book();
        book.setBookName("testNameFindAll");
        bookRepository.save(book);

        Integer count = bookRepository.count();
        assertThat(count).isNotZero();
    }

    @Test
    @DisplayName("ищет список книг")
    void findAll() {
        Book book = new Book();
        book.setBookName("testNameFindAll");
        bookRepository.save(book);

        List<Book> list = bookRepository.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет книгу по идентификатору")
    void findById() {
        Book book = new Book();
        book.setBookName("testNameFindById");
        bookRepository.save(book);

        Optional<Book> optional = bookRepository.findById(book.getBookId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("создает книгу")
    void save() {
        Book book = new Book();
        book.setBookName("testNameFindById");
        bookRepository.save(book);

        Optional<Book> optional = bookRepository.findById(book.getBookId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("обновляет книгу")
    void update() {
        Book book = new Book();
        book.setBookName("testNameFindById");
        bookRepository.save(book);

        Optional<Book> optional = bookRepository.findById(book.getBookId());
        assertThat(optional).isPresent();

        optional.ifPresent(book1 -> {
            book1.setBookName("newName");
            bookRepository.update(book1);

            Optional<Book> optional2 = bookRepository.findById(book1.getBookId());
            assertThat(optional2).isPresent();

            optional2.ifPresent( book2 -> {
                assertThat(book2).isEqualTo(book1);
            });
        });
    }

    @Test
    @DisplayName("удаляет книгу")
    void deleteById() {
        Book book = new Book();
        book.setBookName("testNameFindById");
        bookRepository.save(book);

        Optional<Book> optional = bookRepository.findById(book.getBookId());
        assertThat(optional).isPresent();

        bookRepository.deleteById(book.getBookId());

        Optional<Book> optional2 = bookRepository.findById(book.getBookId());
        assertThat(optional2).isNotPresent();
    }
}