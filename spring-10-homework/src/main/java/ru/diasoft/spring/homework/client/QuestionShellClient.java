package ru.diasoft.spring.homework.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Comment;
import ru.diasoft.spring.homework.service.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ShellComponent
@Log4j2
@RequiredArgsConstructor
public class QuestionShellClient {

    private final MessageSourceService messageSourceService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

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
        if (Objects.nonNull(authorId)) book.setAuthor(authorService.findById(authorId));
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

    @ShellMethod(key = {"fbib","find-by-id-book"}, value = "Method find book by id")
    @ShellMethodAvailability(value = "isAuthUser")
    public String findById(@ShellOption Long id) {
        return bookService.findById(id).toString();
    }

    @ShellMethod(key = {"cc","create-comment"}, value = "Method create comment")
    @ShellMethodAvailability(value = "isAuthUser")
    public void createComment(
            @ShellOption Long bookId,
            @ShellOption String userName,
            @ShellOption String text
    ) {
        Comment comment = new Comment();
        comment.setUserName(userName);
        comment.setText(text);
        bookService.findById(bookId).ifPresent(book -> {
            comment.setBook(book);
            commentService.save(comment);
        });
    }
    @ShellMethod(key = {"facbbi","find-all-comment-by-book-id"}, value = "Method find all comments")
    @ShellMethodAvailability(value = "isAuthUser")
    public String findAllCommentByBookId(@ShellOption Long bookId) {
        List<Comment> commentList = new ArrayList<>();
        bookService.findById(bookId).ifPresent(book -> {
            commentList.addAll(book.getComments());
        });
        return commentList.toString();
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
