package com.endava.service;

import com.endava.StaticReusedVariables;
import com.endava.config.IntegrationTestConfig;
import com.endava.dao.MoneyTransferDao;
import com.endava.dao.UserDao;
import com.endava.dao.WherefromDao;
import com.endava.model.MoneyTransfer;
import com.endava.model.User;
import com.endava.model.Wherefrom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.endava.StaticReusedVariables.*;
import static com.endava.enums.MoneyTransferType.INCOME;
import static org.junit.Assert.*;

/**
 * Created by vsaban on 3/30/2017.
 */
@RunWith(SpringRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class MoneyTransferServiceImplIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WherefromDao wherefromDao;

    @Autowired
    private MoneyTransferDao moneyTransferDao;

    @Autowired
    private MoneyTransferService moneyTransferService;

    private User user;
    private MoneyTransfer moneyTransfer;
    private Wherefrom wherefrom;

    @Before
    public void setUp() {
        user = createUser();
        wherefrom = createWherefrom(WHEREFROM_CASH_NAME);
        moneyTransfer = StaticReusedVariables.createMoneyTransfer(INCOME, 50.0, wherefrom, new Date(DATE_ONE));
    }

    @Test
    public void testAddMoneyTransfer() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer actualMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(actualMoneyTransfer);
        assertEquals(actualMoneyTransfer, moneyTransfer);
    }

    @Test
    public void testDeleteMoneyTransfer() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer actualMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(actualMoneyTransfer);

        moneyTransferService.deleteMoneyTransfer(actualMoneyTransfer);

        assertFalse(moneyTransferDao.exists(actualMoneyTransfer));
    }

    @Test
    public void testDeleteMoneyTransferById() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(persistedMoneyTransfer);

        moneyTransferService.deleteMoneyTransferById(persistedMoneyTransfer.getId());

        assertFalse(moneyTransferDao.exists(persistedMoneyTransfer));
    }

    @Test
    public void testUpdateMoneyTransfer() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(persistedMoneyTransfer);

        persistedMoneyTransfer.setAmount(10000000000.0);

        MoneyTransfer updatedMoneyTransfer = moneyTransferService.updateMoneyTransfer(persistedMoneyTransfer);

        assertNotNull(updatedMoneyTransfer);
        assertEquals(persistedMoneyTransfer, updatedMoneyTransfer);
    }

    @Test
    public void testGetMoneyTransferById() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(persistedMoneyTransfer);

        MoneyTransfer pulledMoneyTransfer = moneyTransferService.getMoneyTransferById(persistedMoneyTransfer.getId());

        assertNotNull(pulledMoneyTransfer);
        assertEquals(persistedMoneyTransfer, pulledMoneyTransfer);
    }

    @Test
    public void testGetMoneyTransfers() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(persistedMoneyTransfer);

        List<MoneyTransfer> pulledMoneyTransfers = moneyTransferService.getMoneyTransfers();

        assertNotNull(pulledMoneyTransfers);
        assertEquals(Collections.singletonList(persistedMoneyTransfer), pulledMoneyTransfers);
    }

    @Test
    public void testGetMoneyTransferOn() {
        Date date = new Date(DATE_ONE);

        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);

        assertNotNull(persistedMoneyTransfer);

        List<MoneyTransfer> requiredMoneyTransfer = moneyTransferService.getMoneyTransferOn(date);

        assertNotNull(requiredMoneyTransfer);
        assertEquals(Collections.singletonList(persistedMoneyTransfer), requiredMoneyTransfer);
    }

    @Test
    public void testGetMoneyTransferLastWeek() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        MoneyTransfer newMT = createMoneyTransfer(52.0);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(newMT);

        assertNotNull(persistedMoneyTransfer);

        List<MoneyTransfer> requiredMoneyTransfer = moneyTransferService.getMoneyTransferLastWeek();

        assertNotNull(requiredMoneyTransfer);
        assertEquals(Collections.singletonList(persistedMoneyTransfer), requiredMoneyTransfer);
    }

    @Test
    public void testGetMoneyTransferLastMonth() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        MoneyTransfer newMT = createMoneyTransfer(52.0);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(newMT);
        assertNotNull(persistedMoneyTransfer);

        List<MoneyTransfer> requiredMoneyTransfer = moneyTransferService.getMoneyTransferLastMonth();
        assertNotNull(requiredMoneyTransfer);

        assertEquals(Collections.singletonList(persistedMoneyTransfer), requiredMoneyTransfer);
    }

    @Test
    public void testGetMoreExpensiveThan() {
        Double amount = 49.0;

        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer newMT = createMoneyTransfer(12.0);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);
        moneyTransferService.addMoneyTransfer(newMT);

        assertNotNull(persistedMoneyTransfer);

        List<MoneyTransfer> requiredMoneyTransfers = moneyTransferService.getMoneyTransferMoreExpensiveThan(amount);

        assertNotNull(requiredMoneyTransfers);
        assertEquals(Collections.singletonList(persistedMoneyTransfer), requiredMoneyTransfers);
    }

    @Test
    public void testGetMoneyTransferByWherefrom() {
        userDao.persist(user);
        wherefromDao.persist(wherefrom);

        moneyTransfer.setUser(user);

        MoneyTransfer persistedMoneyTransfer = moneyTransferService.addMoneyTransfer(moneyTransfer);
        assertNotNull(persistedMoneyTransfer);

        List<MoneyTransfer> requiredMoneyTransfers = moneyTransferService.getMoneyTransferByCategory(wherefrom);

        assertNotNull(requiredMoneyTransfers);
        assertEquals(Collections.singletonList(persistedMoneyTransfer), requiredMoneyTransfers);
    }

    private MoneyTransfer createMoneyTransfer(Double amount) {
        Date date = new Date(System.currentTimeMillis() - 172800000L);

        return MoneyTransfer.builder()
                            .withAmount(amount)
                            .withDate(date)
                            .withType(INCOME)
                            .withUser(user)
                            .withWherefrom(wherefrom)
                            .build();
    }

}
