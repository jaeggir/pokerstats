package ch.rogerjaeggi.pokerstats.repository;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class JpaRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;

    public JpaRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Transactional(readOnly = true)
    public List<T> getAll() {
        Query query = entityManager.createQuery("from " + clazz.getSimpleName());
        return Collections.checkedList(query.getResultList(), clazz);
    }

    @Transactional(readOnly = true)
    public T getByUuid(String uuid) {
        return entityManager.find(clazz, uuid);
    }

    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
