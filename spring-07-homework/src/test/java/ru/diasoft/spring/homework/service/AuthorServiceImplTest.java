package ru.diasoft.spring.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.homework.entity.Author;
import ru.diasoft.spring.homework.repository.AuthorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис авторов")
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    @DisplayName("считает авторов")
    void count() {
        when(authorRepository.count()).thenReturn(1);
        assertThat(authorService.count()).isNotZero();
    }

    @Test
    @DisplayName("сохраняет автора")
    void save() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        when(authorRepository.save(author)).thenReturn(author);

        Author author1 = authorService.save(author);
        assertThat(author).isEqualTo(author1);
    }

    @Test
    @DisplayName("обновляет автора")
    void update() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        when(authorRepository.update(author)).thenReturn(author);

        Author author1 = authorService.update(author);
        assertThat(author).isEqualTo(author1);
    }

    @Test
    @DisplayName("ищет авторов")
    void findAll() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(author));

        List<Author> list = authorService.findAll();
        assertThat(list).isNotEmpty();
    }

    @Test
    @DisplayName("ищет автора по ид")
    void findById() {
        Author author = new Author();
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        author.setMiddleName("MiddleName");
        when(authorRepository.findById(any())).thenReturn(Optional.of(author));

        Optional<Author> optionalAuthor = authorService.findById(1L);
        assertThat(optionalAuthor).isPresent();
    }
}