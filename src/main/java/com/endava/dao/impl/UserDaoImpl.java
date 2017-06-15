package com.endava.dao.impl;

import com.endava.dao.UserDao;
import com.endava.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * Created by vsaban on 3/16/2017.
 */
@Repository
public class UserDaoImpl extends GenDaoImpl<User> implements UserDao {

    @Override
    public User findByLogin(final String login) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root<User> root = query.from(User.class);
        final ParameterExpression<String> param = builder.parameter(String.class);
        query.select(root).where(builder.equal(root.get("login"), param));

        return entityManager.createQuery(query)
                .setParameter(param, login)
                .getSingleResult();
    }
}
