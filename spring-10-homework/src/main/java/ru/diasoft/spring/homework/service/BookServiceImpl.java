package ru.diasoft.spring.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.homework.entity.Book;
import ru.diasoft.spring.homework.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Long count() {
        return bookRepository.count();
    }

    @Transactional
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}
