package com.endava.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.*;
import java.util.List;

/**
 * Created by vsaban on 3/16/2017.
 */
public class GenDaoImpl<T> implements GenDao<T> {

    private final Class<T> type;

    @Autowired
    private EntityManager entityManager;

    public GenDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Transactional
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
        return null;
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void updateById(Long id) {
        T entity = this.read(id);
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