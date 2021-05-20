package ru.diasoft.spring.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long count() {
        return em.createQuery(
                "select count(b) from Book b", Long.class)
                .getSingleResult();
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        em.merge(book);
        return book;
    }

    @Override
    public void deleteById(Long bookId) {
        Optional.ofNullable(em.find(Book.class, bookId))
                .ifPresent(em::remove);
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b ", Book.class)
                .getResultList();
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

}
