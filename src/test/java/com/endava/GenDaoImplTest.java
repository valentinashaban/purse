package com.endava;

import com.endava.dao.impl.GenDaoImpl;
import com.endava.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by vsaban on 3/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GenDaoImplTest {
    private final static Long USER_ID = 1L;
    private final static Class<User> USER_CLASS = User.class;

    @InjectMocks
    private GenDaoImpl<User> genDao = new GenDaoImpl<>(USER_CLASS);

    @Mock
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        //initMocks(this);
    }

    @Test
    public void testCreate() {
        User user = createUser();

        doNothing().when(entityManager).persist(user);

        genDao.create(user);

        verify(entityManager).persist(user);
    }

    @Test
    public void testRead() {
        User expectedUser = createUser();

        when(entityManager.find(USER_CLASS, USER_ID)).thenReturn(expectedUser);

        User actualUser = genDao.read(USER_ID);

        verify(entityManager).find(USER_CLASS, USER_ID);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testReadAll() {
        List<User> expectedUsers = createUsers();
        Query someQuery = mock(Query.class);

        when(entityManager.createQuery(anyString())).thenReturn(someQuery);
        when(someQuery.getResultList()).thenReturn(expectedUsers);

        List<User> actualUsers = genDao.readAll(USER_CLASS);

        verify(entityManager).createQuery(anyString());
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testUpdate() {
        User user = createUser();

        when(entityManager.merge(user)).thenReturn(user);

        genDao.update(user);

        verify(entityManager).merge(user);
    }

    @Test
    public void testDelete() {
        User user = createUser();

        doNothing().when(entityManager).remove(user);

        genDao.delete(user);

        verify(entityManager).remove(user);
    }

    @Test
    public void testDeleteById() {
        User user = createUser();

        when(entityManager.find(USER_CLASS, USER_ID)).thenReturn(user);
        doNothing().when(entityManager).remove(user);

        genDao.deleteById(USER_ID);

        verify(entityManager).find(USER_CLASS, USER_ID);
        verify(entityManager).remove(ArgumentMatchers.eq(user));
        verifyNoMoreInteractions(entityManager); //no other methods called
    }

    @Test
    public void testDeleteList() {
        List<User> users = createUsers();

        doNothing().when(entityManager).remove(any());

        genDao.deleteList(users);

        verify(entityManager, times(users.size())).remove(any());
    }

    @Test
    public void testExists() {
        User user = createUser();

        when(entityManager.contains(user)).thenReturn(true);

        genDao.exists(user);

        verify(entityManager).contains(user);
    }

    private User createUser() {
        User user = new User();
        user.setLogin("valera");
        user.setPassword("1111");
        user.setEmail("valera@endava.com");
        return user;
    }

    private User createUser(String login, String password, String email) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    private List<User> createUsers() {
        List<User> users = Arrays.asList(
                createUser("valentina", "2222", "valentina@endava.com"),
                createUser("natalia", "3333", "natalia@endava.com"),
                createUser("olga", "4444", "olga@endava.com"));

        return users;
    }

}
