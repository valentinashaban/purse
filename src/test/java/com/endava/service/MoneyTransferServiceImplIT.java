package com.endava.service;

import com.endava.config.IntegrationTestConfig;
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
import org.springframework.transaction.annotation.Transactional;

import static com.endava.StaticReusedVariables.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by vsaban on 3/30/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class MoneyTransferServiceImplIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WherefromDao wherefromDao;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @Test
    @Transactional
    public void testAddMoneyTransfer() {
        User user = createUser();

        User persistedUser = userDao.create(user);
        assertNotNull(persistedUser);

        wherefromDao.create(WHEREFROM_CASH);


        MONEY_TRANSFER.setUser(persistedUser);
        MoneyTransfer actualMoneyTransfer = moneyTransferService.addMoneyTransfer(MONEY_TRANSFER);
        assertNotNull(actualMoneyTransfer);

        assertEquals(actualMoneyTransfer, MONEY_TRANSFER);
    }
}
