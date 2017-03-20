package com.endava.dao.impl;

import com.endava.dao.GenDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
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
    public List<T> readAll(Class<T> type) {
        String tableName = type.getName();
        return entityManager.createQuery(
                "SELECT e FROM" + tableName)
                .getResultList();
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
        for (int i = 0; i < entities.size(); i++)
            entityManager.remove(entities.get(i));
    }

    @Override
    public boolean exists(T entity) {
        return entityManager.contains(entity);
    }
}
