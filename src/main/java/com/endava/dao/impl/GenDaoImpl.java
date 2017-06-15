package com.endava.dao.impl;

import com.endava.dao.GenDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by vsaban on 3/16/2017.
 */
public class GenDaoImpl<T> implements GenDao<T> {

    private final Class<T> type;

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings({"unchecked"})
    public GenDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public GenDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T persist(T entity) {
        entityManager.persist(entity);
        return entity;
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
    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        T entity = this.read(id);
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void deleteList(List<T> entities) {
        entities.forEach(entityManager::remove);
    }

    @Override
    public boolean exists(T entity) {
        return entityManager.contains(entity);
    }
}
