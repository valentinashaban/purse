package com.endava.service;

import com.endava.config.IntegrationTestConfig;
import com.endava.dao.UserDao;
import com.endava.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.endava.StaticReusedVariables.createUser;
import static org.junit.Assert.*;

/**
 * Created by vsaban on 4/7/2017.
 */
@RunWith(SpringRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class UserServiceImplIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = createUser();
    }

    @Test
    public void testSaveUser() {
        User persistedUser = userService.saveUser(user);

        assertNotNull(persistedUser);
        assertEquals(user, persistedUser);
    }

    @Test
    public void testDeleteUser() {
        User persistedUser = userService.saveUser(user);

        assertNotNull(persistedUser);

        userService.deleteUser(user);

        assertFalse(userDao.exists(user));
    }

    @Test
    public void testUpdateUser() {
        User persistedUser = userService.saveUser(user);

        assertNotNull(persistedUser);

        persistedUser.setEmail("another@example.com");

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(persistedUser, updatedUser);
    }

    @Test
    public void testGetUserById() {
        User persistedUser = userService.saveUser(user);

        assertNotNull(persistedUser);

        User pulledUser = userService.getUserById(persistedUser.getId());

        assertNotNull(pulledUser);
        assertEquals(persistedUser, pulledUser);
    }
}
