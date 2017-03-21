package com.endava.dao.impl;

import com.endava.dao.GenDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by vsaban on 3/16/2017.
 */
public class GenDaoImpl<T> implements GenDao<T> {

    private final Class<T> type;

    @Autowired
    private EntityManager entityManager;

    public GenDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T read(Long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<T> readAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        T entity = this.read(id);
        entityManager.remove(entity);
    }

    @Override
    public void deleteList(List<T> entities) {
        for (T entity : entities) entityManager.remove(entity);
    }

    @Override
    public boolean exists(T entity) {
        return entityManager.contains(entity);
    }
}
