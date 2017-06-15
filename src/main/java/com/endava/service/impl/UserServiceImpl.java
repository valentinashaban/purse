package com.endava.service.impl;

import com.endava.dao.UserDao;
import com.endava.model.User;
import com.endava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by vsaban on 4/7/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        userDao.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        userDao.merge(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userDao.read(id);
    }

    @Override
    public boolean exists(User user) {
        return userDao.exists(user);
    }

    @Override
    public User getAuthenticatedUser(final SecurityContext securityContext) {
        final org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) securityContext.getAuthentication().getPrincipal();

        return Optional.ofNullable(user)
                .map(u -> userDao.findByLogin(u.getUsername()))
                .orElse(null);
    }
}
