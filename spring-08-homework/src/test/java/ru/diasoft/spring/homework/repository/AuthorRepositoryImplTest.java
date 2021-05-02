package ru.diasoft.spring.homework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.homework.entity.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql({"classpath:schema.sql"})
@Import({AuthorRepositoryImpl.class})
@DisplayName("Репозиторий авторов кнги")
class AuthorRepositoryImplTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("считает количество авторов")
    void count() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        authorRepository.save(author);

        Long count = authorRepository.count();
        assertThat(count).isNotZero();
    }

    @Test
    @DisplayName("ищет список авторов")
    void findAll() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        authorRepository.save(author);

        List<Author> list = authorRepository.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет автора по идентификатору")
    void findById() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        authorRepository.save(author);

        Optional<Author> optional = authorRepository.findById(author.getAuthorId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("создает автора")
    void save() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        authorRepository.save(author);

        Optional<Author> optional = authorRepository.findById(author.getAuthorId());
        assertThat(optional).isPresent();
    }

    @Test
    @DisplayName("обновляет автора")
    void update() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        authorRepository.save(author);

        Optional<Author> optional = authorRepository.findById(author.getAuthorId());
        assertThat(optional).isPresent();

        optional.ifPresent(author1 -> {
            author1.setFirstName("newName");
            authorRepository.update(author1);

            Optional<Author> optional2 = authorRepository.findById(author1.getAuthorId());
            assertThat(optional2).isPresent();

            optional2.ifPresent( book2 -> {
                assertThat(book2).isEqualTo(author1);
            });
        });
    }

    @Test
    @DisplayName("удаляет автора")
    void deleteById() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        authorRepository.save(author);

        Optional<Author> optional = authorRepository.findById(author.getAuthorId());
        assertThat(optional).isPresent();

        authorRepository.deleteById(author.getAuthorId());

        Optional<Author> optional2 = authorRepository.findById(author.getAuthorId());
        assertThat(optional2).isNotPresent();
    }
}