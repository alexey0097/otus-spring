package ru.diasoft.spring.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.diasoft.spring.homework.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long count() {
        return em.createQuery(
                "select count(c) from Comment c", Long.class)
                .getSingleResult();
    }

    @Override
    public Comment save(Comment comment) {
        em.persist(comment);
        em.flush();
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        em.merge(comment);
        em.flush();
        return comment;
    }

    @Override
    public void deleteById(Long commentId) {
        Optional.ofNullable(em.find(Comment.class, commentId))
                .ifPresent(em::remove);
    }

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }
}
