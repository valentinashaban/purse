package com.endava.dao.impl;

import com.endava.dao.UserDao;
import com.endava.model.User;

/**
 * Created by vsaban on 3/16/2017.
 */
public class UserDaoImpl extends GenDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
