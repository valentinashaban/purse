package com.endava.dao;

import com.endava.model.User;

/**
 * Created by vsaban on 3/16/2017.
 */
public interface UserDao extends GenDao<User> {

    User findByLogin(String login);

}
