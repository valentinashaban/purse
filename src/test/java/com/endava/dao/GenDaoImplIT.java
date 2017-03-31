package com.endava.dao;

import com.endava.config.IntegrationTestConfig;
import com.endava.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.endava.StaticReusedVariables.USER1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by vsaban on 3/30/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class GenDaoImplIT {

    @Autowired
    private UserDao userDao;

//    @Test
//    public void testCreate() {
//        User user = userDao.create(USER1);
//
//        User actualUser = userDao.read(user.getId());
//
//        assertNotNull(actualUser);
//
//        assertEquals(user, actualUser);
//    }

    @Test
    @Rollback(false)
    public void testCreate() {
        userDao.create(USER1);
    }
}