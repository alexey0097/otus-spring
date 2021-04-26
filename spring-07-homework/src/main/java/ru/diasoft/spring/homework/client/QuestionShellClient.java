package ru.diasoft.spring.homework.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.service.AuthorService;
import ru.diasoft.spring.homework.service.BookService;
import ru.diasoft.spring.homework.service.GenreService;
import ru.diasoft.spring.homework.service.MessageSourceService;

import java.util.Objects;

@ShellComponent
@Log4j2
@RequiredArgsConstructor
public class QuestionShellClient {

    private final MessageSourceService messageSourceService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private Boolean isAuth = false;

    @ShellMethod(key = {"a", "auth"}, value = "Method auth user")
    public void auth(
            @ShellOption String firstName,
            @ShellOption String lastName
    ) {
        isAuth = true;
    }

    @ShellMethod(key = {"cb", "create-book"}, value = "Method create book")
    @ShellMethodAvailability(value = "isAuthUser")
    public String startTest(
            @ShellOption String name,
            @ShellOption Long authorId,
            @ShellOption Long genreId
    ) {
        Book book = new Book();
        book.setBookName(name);
        if (Objects.nonNull(authorId)) authorService.findById(authorId).ifPresent(book::setAuthor);
        if (Objects.nonNull(genreId)) genreService.findById(genreId).ifPresent(book::setGenre);

        return bookService.save(book).toString();
    }

    @ShellMethod(key = {"faa","find-all-author"}, value = "Method create book")
    @ShellMethodAvailability(value = "isAuthUser")
    public String findAllAuthor() {
        var stringBuffer = new StringBuffer();
        authorService.findAll()
                .stream()
                .map(Object::toString)
                .forEach( str -> {
                    stringBuffer.append(str);
                    stringBuffer.append("\n");
                });
        return stringBuffer.toString();
    }

    @ShellMethod(key = {"fag","find-all-genre"}, value = "Method create book")
    @ShellMethodAvailability(value = "isAuthUser")
    public String findAllGenre() {
        var stringBuffer = new StringBuffer();
        genreService.findAll().stream()
                .map(Object::toString)
                .forEach( str -> {
                    stringBuffer.append(str);
                    stringBuffer.append("\n");
                });
        return stringBuffer.toString();
    }

    @ShellMethod(key = {"fab","find-all-book"}, value = "Method create book")
    @ShellMethodAvailability(value = "isAuthUser")
    public String findAll() {
        var stringBuffer = new StringBuffer();
        bookService.findAll().stream()
                .map(Object::toString)
                .forEach( str -> {
                    stringBuffer.append(str);
                    stringBuffer.append("\n");
                });
        return stringBuffer.toString();
    }

    @ShellMethod(key = {"fbib","find-by-id-book"}, value = "Method create book")
    @ShellMethodAvailability(value = "isAuthUser")
    public String findById(@ShellOption Long id) {
        return bookService.findById(id).toString();
    }

    private Availability isAuthUser() {
        if (!isAuth) {
            var msg = messageSourceService.getMessage("prop.is.not.auth");
            return Availability.unavailable(msg);
        } else {
            return Availability.available();
        }
    }

}
