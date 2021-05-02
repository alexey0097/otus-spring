package ru.diasoft.spring.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long count() {
        return em.createQuery(
                "select count(a) from Author a", Long.class)
                .getSingleResult();
    }

    @Override
    public Author save(Author author) {
        em.persist(author);
        em.flush();
        return author;
    }

    @Override
    public Author update(Author author) {
        em.merge(author);
        em.flush();
        return author;
    }

    @Override
    public void deleteById(Long authorId) {
        Optional.ofNullable(em.find(Author.class, authorId))
            .ifPresent(em::remove);
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        return Optional.ofNullable(em.find(Author.class, authorId));
    }
}
