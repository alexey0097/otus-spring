package ru.diasoft.spring.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.entity.Comment;
import ru.diasoft.spring.homework.exception.InternalServerException;
import ru.diasoft.spring.homework.repository.BookRepository;
import ru.diasoft.spring.homework.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public Long count() {
        return commentRepository.count();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveByBookId(Long bookId, Comment comment) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new InternalServerException("domain not found..."));
        comment.setBook(book);
        commentRepository.save(comment);
        return comment;
    }
}
