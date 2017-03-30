package com.endava.dao;

import java.util.List;

/**
 * Created by vsaban on 3/16/2017.
 */

public interface GenDao<T> {
    void create(T entity);
    T read(Long id);
    List<T> readAll();
    void update(T entity);
    void delete(T entity);
    void deleteById(Long id);
    void deleteList(List<T> entities);
    boolean exists(T entity);
}
