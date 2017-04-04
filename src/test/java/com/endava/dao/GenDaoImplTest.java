package com.endava.dao;

import com.endava.dao.impl.GenDaoImpl;
import com.endava.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.endava.StaticReusedVariables.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by vsaban on 3/17/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GenDaoImplTest {
    private final static Long USER_ID = 1L;
    private final static Class<User> USER_CLASS = User.class;
    private final static User USER = createUser();

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private GenDaoImpl<User> genDao = new GenDaoImpl<>(USER_CLASS);

    @After
    public void tearDown() {
        verifyNoMoreInteractions(entityManager);
    }

    @Test
    public void testCreate() {
        doNothing().when(entityManager).persist(USER);

        genDao.persist(USER);

        verify(entityManager).persist(USER);
    }

    @Test
    public void testRead() {
        when(entityManager.find(USER_CLASS, USER_ID)).thenReturn(USER);

        User actualUser = genDao.read(USER_ID);

        verify(entityManager).find(USER_CLASS, USER_ID);

        assertEquals(USER, actualUser);
    }

    @Test
    public void testReadAll() {
        CriteriaBuilder builder = mock(CriteriaBuilder.class);
        CriteriaQuery<User> criteriaQuery = mock(CriteriaQuery.class);
        Root<User> root = mock(Root.class);
        TypedQuery<User> typedQuery = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(builder);
        when(builder.createQuery(USER_CLASS)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(USER_CLASS)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(USERS);

        List<User> actualUsers = genDao.readAll();

        verify(entityManager).getCriteriaBuilder();
        verify(builder).createQuery(USER_CLASS);
        verify(criteriaQuery).from(USER_CLASS);
        verify(entityManager).createQuery(criteriaQuery);
        verify(typedQuery).getResultList();

        assertEquals(USERS, actualUsers);
    }

    @Test
    public void testUpdate() {
        when(entityManager.merge(USER)).thenReturn(USER);

        genDao.merge(USER);

        verify(entityManager).merge(USER);
    }

    @Test
    public void testDelete() {
        doNothing().when(entityManager).remove(USER);

        genDao.delete(USER);

        verify(entityManager).remove(USER);
    }

    @Test
    public void testDeleteById() {
        when(entityManager.find(USER_CLASS, USER_ID)).thenReturn(USER);
        doNothing().when(entityManager).remove(USER);

        genDao.deleteById(USER_ID);

        verify(entityManager).find(USER_CLASS, USER_ID);
        verify(entityManager).remove(ArgumentMatchers.eq(USER));
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
        when(entityManager.contains(USER)).thenReturn(true);

        boolean exists = genDao.exists(USER);

        verify(entityManager).contains(USER);

        assertTrue(exists);
    }

    @Test
    public void testNotExists() {
        when(entityManager.contains(USER)).thenReturn(false);

        boolean exists = genDao.exists(USER);

        verify(entityManager).contains(USER);

        assertFalse(exists);
    }

}
