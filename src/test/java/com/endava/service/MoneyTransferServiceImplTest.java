package com.endava.service;

import com.endava.dao.MoneyTransferDao;
import com.endava.model.MoneyTransfer;
import com.endava.model.Wherefrom;
import com.endava.service.impl.MoneyTransferServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

import static com.endava.StaticReusedVariables.*;
import static com.endava.enums.MoneyTransferType.INCOME;
import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * Created by vsaban on 3/21/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoneyTransferServiceImplTest {

    private final static Validator VALIDATOR = buildDefaultValidatorFactory().getValidator();
    private Set<ConstraintViolation<MoneyTransfer>> constraintViolationsMoneyTransfer;

    @Mock
    private MoneyTransferDao moneyTransferDao;

    @Spy
    @InjectMocks
    private MoneyTransferServiceImpl moneyTransferService;

    @Before
    public void setUp() {}

    @After
    public void tearDown() {
        verifyNoMoreInteractions(moneyTransferDao);
    }

    @Test
    public void testAddMoneyTransferIncorrectAmount() {
        MoneyTransfer moneyTransfer = createMoneyTransfer(INCOME, -50.0, WHEREFROM_CASH, new Date(System.currentTimeMillis()));

        constraintViolationsMoneyTransfer = VALIDATOR.validate(moneyTransfer);

        assertEquals(1, constraintViolationsMoneyTransfer.size());
        assertEquals("must be greater than or equal to 0",
                constraintViolationsMoneyTransfer.iterator().next().getMessage());
    }

    @Test
    public void testAddMoneyTransferIncorrectDate() {
        MoneyTransfer moneyTransfer = createMoneyTransfer(INCOME, 50.0, WHEREFROM_CASH, new Date(System.currentTimeMillis() + 1000000L));

        constraintViolationsMoneyTransfer = VALIDATOR.validate(moneyTransfer);

        assertEquals(1, constraintViolationsMoneyTransfer.size());
        assertEquals("must be in the past", constraintViolationsMoneyTransfer.iterator().next().getMessage());
    }

    @Test
    public void testAddMoneyTransferForNotNull() {
        doNothing().when(moneyTransferDao).create(MONEY_TRANSFER);

        constraintViolationsMoneyTransfer = VALIDATOR.validate(MONEY_TRANSFER);

        moneyTransferService.addMoneyTransfer(MONEY_TRANSFER);

        verify(moneyTransferDao).create(MONEY_TRANSFER);
        assertEquals(0, constraintViolationsMoneyTransfer.size());
    }

    @Test
    public void testDeleteMoneyTransferIncorrectAmount() {
        MoneyTransfer moneyTransfer = createMoneyTransfer(INCOME, -50.0, WHEREFROM_CASH, new Date(System.currentTimeMillis()));

        constraintViolationsMoneyTransfer = VALIDATOR.validate(moneyTransfer);

        assertEquals(1, constraintViolationsMoneyTransfer.size());
        assertEquals("must be greater than or equal to 0",
                constraintViolationsMoneyTransfer.iterator().next().getMessage());
    }

    @Test
    public void testDeleteMoneyTransferIncorrectDate() {
        MoneyTransfer moneyTransfer = createMoneyTransfer(INCOME, 50.0, WHEREFROM_CASH, new Date(System.currentTimeMillis() + 1000000L));

        constraintViolationsMoneyTransfer = VALIDATOR.validate(moneyTransfer);

        assertEquals(1, constraintViolationsMoneyTransfer.size());
        assertEquals("must be in the past", constraintViolationsMoneyTransfer.iterator().next().getMessage());
    }

    @Test
    public void testDeleteMoneyTransferForNotNull() {
        doNothing().when(moneyTransferDao).delete(MONEY_TRANSFER);

        constraintViolationsMoneyTransfer = VALIDATOR.validate(MONEY_TRANSFER);

        moneyTransferService.deleteMoneyTransfer(MONEY_TRANSFER);

        verify(moneyTransferDao).delete(MONEY_TRANSFER);
        assertEquals(0, constraintViolationsMoneyTransfer.size());
    }

    @Test
    public void testDeleteById() {
        Long id = 12L;

        doNothing().when(moneyTransferDao).deleteById(id);

        moneyTransferService.deleteMoneyTransferById(id);

        verify(moneyTransferDao).deleteById(id);
    }

    @Test
    public void testDeleteByIncorrectId() {
        Long id = -12L;

        moneyTransferService.deleteMoneyTransferById(id);

        verify(moneyTransferDao, never()).deleteById(id);
    }

    @Test
    public void testUpdateMoneyTransferCorrectData() {
        doNothing().when(moneyTransferDao).update(MONEY_TRANSFER);

        constraintViolationsMoneyTransfer = VALIDATOR.validate(MONEY_TRANSFER);

        moneyTransferService.updateMoneyTransfer(MONEY_TRANSFER);

        verify(moneyTransferDao).update(MONEY_TRANSFER);
        assertEquals(0, constraintViolationsMoneyTransfer.size());
    }

    @Test
    public void testUpdateMoneyTransferIncorrectAmount() {
        MoneyTransfer moneyTransfer = createMoneyTransfer(INCOME, -50.0, WHEREFROM_CASH, new Date(System.currentTimeMillis()));

        constraintViolationsMoneyTransfer = VALIDATOR.validate(moneyTransfer);

        assertEquals(1, constraintViolationsMoneyTransfer.size());
        assertEquals("must be greater than or equal to 0",
                constraintViolationsMoneyTransfer.iterator().next().getMessage());
    }

    @Test
    public void testUpdateMoneyTransferIncorrectDate() {
        MoneyTransfer moneyTransfer = createMoneyTransfer(INCOME, 50.0, WHEREFROM_CASH, new Date(System.currentTimeMillis() + 100000000L));

        constraintViolationsMoneyTransfer = VALIDATOR.validate(moneyTransfer);

        assertEquals(1, constraintViolationsMoneyTransfer.size());
        assertEquals("must be in the past",
                constraintViolationsMoneyTransfer.iterator().next().getMessage());
    }

    @Test
    public void testGetMoneyTransferById() {
        Long id = 1L;
        MONEY_TRANSFER.setId(id);

        when(moneyTransferDao.read(id)).thenReturn(MONEY_TRANSFER);

        MoneyTransfer actualMoneyTransfer = moneyTransferService.getMoneyTransferById(id);

        verify(moneyTransferDao).read(id);
        assertEquals(MONEY_TRANSFER, actualMoneyTransfer);
    }

    @Test
    public void testGetMoneyTransferByIncorrectId() {
        Long id = -12L;

        when(moneyTransferDao.read(id)).thenReturn(null);

        MoneyTransfer actualMoneyTransfer = moneyTransferService.getMoneyTransferById(id);

        verify(moneyTransferDao).read(id);
        assertNull(actualMoneyTransfer);
    }

    @Test
    public void testViewMoneyTransfersOnCorrectDate() {
        Date date = new Date(DATE_ONE);

        when(moneyTransferDao.readAll()).thenReturn(MONEY_TRANSFERS);

        List<MoneyTransfer> actualMoneyTransfers = moneyTransferService.getMoneyTransferOn(date);
        List<MoneyTransfer> expectedMoneyTransfers = Collections.singletonList(MONEY_TRANSFER);

        verify(moneyTransferDao).readAll();
        assertEquals(expectedMoneyTransfers, actualMoneyTransfers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testViewMoneyTransfersOnIncorrectDate() {
        Date date = new Date(System.currentTimeMillis() + 1000000000L);

        moneyTransferService.getMoneyTransferOn(date);

        verify(moneyTransferDao, never()).readAll();
    }

    @Test
    public void testViewMoneyTransfersLastWeek() {
        when(moneyTransferService.getCurrentDate()).thenReturn(CURRENT_FIXED_DATE);
        when(moneyTransferDao.readAll()).thenReturn(MONEY_TRANSFERS);

        List<MoneyTransfer> actualMoneyTransfers = moneyTransferService.getMoneyTransferLastWeek();
        List<MoneyTransfer> expectedMoneyTransfers = Arrays.asList(MONEY_TRANSFER, MONEY_TRANSFER1);

        verify(moneyTransferDao).readAll();
        assertEquals(expectedMoneyTransfers, actualMoneyTransfers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testViewMoneyTransfersMoreExpensiveThanIncorrectAmount() {
        Double amount = -50.0;

        moneyTransferService.getMoneyTransferMoreExpensiveThan(amount);

        verify(moneyTransferDao, never()).readAll();
    }

    @Test
    public void testViewMoneyTransfersMoreExpensiveThanCorrectAmount() {
        Double amount = 100.0;

        when(moneyTransferDao.readAll()).thenReturn(MONEY_TRANSFERS);

        List<MoneyTransfer> actualMoneyTransfers = moneyTransferService.getMoneyTransferMoreExpensiveThan(amount);
        List<MoneyTransfer> expectedMoneyTransfers = Arrays.asList(MONEY_TRANSFER2, MONEY_TRANSFER3);

        verify(moneyTransferDao).readAll();
        assertEquals(expectedMoneyTransfers, actualMoneyTransfers);
    }

    @Test
    public void testViewMoneyTransferByIncorrectWherefrom() {
        Set<ConstraintViolation<Wherefrom>> constraintViolationsWherefrom = VALIDATOR.validate(new Wherefrom());

        assertEquals(1, constraintViolationsWherefrom.size());
        assertEquals("may not be null", constraintViolationsWherefrom.iterator().next().getMessage());
    }

    @Test
    public void testViewMoneyTransferByCorrectWherefrom() {
        when(moneyTransferDao.readAll()).thenReturn(MONEY_TRANSFERS);

        List<MoneyTransfer> actualMoneyTransfers = moneyTransferService.getMoneyTransferByWherefrom(WHEREFROM_SALARY);
        List<MoneyTransfer> expectedMoneyTransfers = Arrays.asList(MONEY_TRANSFER2, MONEY_TRANSFER3);

        verify(moneyTransferDao).readAll();
        assertEquals(expectedMoneyTransfers, actualMoneyTransfers);
    }
}
