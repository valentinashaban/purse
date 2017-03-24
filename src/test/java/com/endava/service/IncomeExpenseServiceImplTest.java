package com.endava.service;

import com.endava.dao.ExpenseDao;
import com.endava.dao.IncomeDao;
import com.endava.model.*;
import com.endava.service.impl.IncomeExpenseServiceImpl;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by vsaban on 3/21/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class IncomeExpenseServiceImplTest {

    private final long DAY_IN_MS = 1000 * 60 * 60 * 24;

    @InjectMocks
    IncomeExpenseServiceImpl incomeExpenseService = new IncomeExpenseServiceImpl();

    @Mock
    IncomeDao incomeDao;

    @Mock
    ExpenseDao expenseDao;

    private Income income1;
    private Income income2;
    private Income income3;
    private Income income4;

    private static Validator validator;
    private Set<ConstraintViolation<Income>> constraintViolationsIncome;
    private Set<ConstraintViolation<Wherefrom>> constraintViolationsWherefrom;

    @Before
    public void setUp() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        DateTimeUtils.setCurrentMillisFixed(1_000_000_000L);
        Wherefrom wherefrom1 = createWherefrom("cash");
        Wherefrom wherefrom2 = createWherefrom("salary");
        income1 = createIncome(50.0, wherefrom1, new Date(1489960800000L));//20.03.2017
        income2 = createIncome(12.0, wherefrom1, new Date(1490220000000L));//23.03.2017
        income3 = createIncome(128.0, wherefrom2, new Date(1483221600000L));//1.01.2017
        income4 = createIncome(1000.0, wherefrom2, new Date(1472677200000L));//1.09.2016
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(incomeDao);
        verifyNoMoreInteractions(expenseDao);
    }

    @Test
    public void testAddIncomeIncorrectAmount() {
        Income income = createIncome(-50.0, new Wherefrom(), new Date(System.currentTimeMillis()));

        constraintViolationsIncome = validator.validate(income);

        assertEquals( 1, constraintViolationsIncome.size() );
        assertEquals("must be greater than or equal to 0",
                constraintViolationsIncome.iterator().next().getMessage());
    }

    @Test
    public void testAddIncomeIncorrectDate() {
        Income income = createIncome(50.0, new Wherefrom(), new Date(System.currentTimeMillis() + 1000000L));

        constraintViolationsIncome = validator.validate(income);

        assertEquals( 1, constraintViolationsIncome.size());
        assertEquals("must be in the past", constraintViolationsIncome.iterator().next().getMessage());
    }

    @Test
    public void testAddIncomeForNotNull() {

        doNothing().when(incomeDao).create(income1);

        constraintViolationsIncome = validator.validate(income1);

        incomeExpenseService.addIncome(income1);

        verify(incomeDao).create(income1);
        assertEquals(0, constraintViolationsIncome.size());
    }

    @Test
    public void testDeleteIncomeIncorrectAmount() {
        Income income = createIncome(-50.0, new Wherefrom(), new Date(System.currentTimeMillis()));

        constraintViolationsIncome = validator.validate(income);

        assertEquals( 1, constraintViolationsIncome.size() );
        assertEquals("must be greater than or equal to 0",
                constraintViolationsIncome.iterator().next().getMessage());
    }

    @Test
    public void testDeleteIncomeIncorrectDate() {
        Income income = createIncome(50.0, new Wherefrom(), new Date(System.currentTimeMillis() + 1000000L));

        constraintViolationsIncome = validator.validate(income);

        assertEquals( 1, constraintViolationsIncome.size());
        assertEquals("must be in the past", constraintViolationsIncome.iterator().next().getMessage());
    }

    @Test
    public void testDeleteIncomeForNotNull() {

        doNothing().when(incomeDao).delete(income1);

        constraintViolationsIncome = validator.validate(income1);

        incomeExpenseService.deleteIncome(income1);

        verify(incomeDao).delete(income1);
        assertEquals( 0, constraintViolationsIncome.size());
    }

    @Test
    public void testDeleteById() {
        Long id = 12L;

        doNothing().when(incomeDao).deleteById(id);

        incomeExpenseService.deleteIncomeById(id);

        verify(incomeDao).deleteById(id);
    }

    @Test
    public void testDeleteByIncorrectId() {
        Long id = -12L;

        incomeExpenseService.deleteIncomeById(id);

        verify(incomeDao, never()).deleteById(id);
    }

    @Test
    public void testUpdateIncomeCorrectData() {

        doNothing().when(incomeDao).update(income1);

        constraintViolationsIncome = validator.validate(income1);

        incomeExpenseService.updateIncome(income1);

        verify(incomeDao).update(income1);
        assertEquals( 0, constraintViolationsIncome.size());
    }

    @Test
    public void testUpdateIncomeIncorrectAmount() {
        Income income = createIncome(-50.0, new Wherefrom(), new Date(System.currentTimeMillis()));

        constraintViolationsIncome = validator.validate(income);

        assertEquals( 1, constraintViolationsIncome.size());
        assertEquals("must be greater than or equal to 0",
                constraintViolationsIncome.iterator().next().getMessage());
    }

    @Test
    public void testUpdateIncomeIncorrectDate() {
        Income income = createIncome(50.0, new Wherefrom(), new Date(System.currentTimeMillis() + 100000000L));

        constraintViolationsIncome = validator.validate(income);

        assertEquals( 1, constraintViolationsIncome.size());
        assertEquals("must be in the past",
                constraintViolationsIncome.iterator().next().getMessage());
    }

    @Test
    public void testViewIncomesOnCorrectDate() {
        List<Income> incomes = new ArrayList<>( Arrays.asList(income1, income2, income3, income4) );
        Date date = new Date(1489960800000L);

        when(incomeDao.readAll()).thenReturn(incomes);

        List<Income> actualIncomes = incomeExpenseService.viewIncomesOn(date);
        List<Income> expectedIncomes = new ArrayList<>( Arrays.asList(income1) );

        verify(incomeDao).readAll();
        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testViewIncomesOnIncorrectDate() {

        Date date = new Date(System.currentTimeMillis() + 1000000000L);

        incomeExpenseService.viewIncomesOn(date);

        verify(incomeDao, never()).readAll();
    }

    @Test
    public void testViewIncomesLastWeek() {
        List<Income> incomes = new ArrayList<>( Arrays.asList(income1, income2, income3, income4) );
        Date date = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));

        when(incomeDao.readAll()).thenReturn(incomes);

        List<Income> actualIncomes = incomeExpenseService.viewIncomesLastWeek();
        List<Income> expectedIncomes = new ArrayList<>( Arrays.asList(income1, income2) );

        verify(incomeDao).readAll();
        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testViewIncomesMoreExpensiveThanIncorrectAmount() {

        Double amount = -50.0;

        incomeExpenseService.viewIncomesMoreExpensiveThan(amount);

        verify(incomeDao, never()).readAll();
    }

    @Test
    public void testViewIncomesMoreExpensiveThanCorrectAmount() {
        List<Income> incomes = new ArrayList<>( Arrays.asList(income1, income2, income3, income4) );
        Double amount = 100.0;

        when(incomeDao.readAll()).thenReturn(incomes);

        List<Income> actualIncomes = incomeExpenseService.viewIncomesMoreExpensiveThan(amount);
        List<Income> expectedIncomes = new ArrayList<>( Arrays.asList(income3, income4) );

        verify(incomeDao).readAll();
        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test
    public void testViewIncomeByIncorrectWherefrom() {
        Wherefrom wherefrom = new Wherefrom();

        constraintViolationsWherefrom = validator.validate(wherefrom);

        assertEquals(1, constraintViolationsWherefrom.size());
        assertEquals("may not be null", constraintViolationsWherefrom.iterator().next().getMessage());
    }

    @Test
    public void testViewIncomeByCorrectWherefrom() {
        Wherefrom wherefrom = new Wherefrom();
        wherefrom.setName("salary");
        List<Income> incomes = new ArrayList<>( Arrays.asList(income1, income2, income3, income4) );

        when(incomeDao.readAll()).thenReturn(incomes);

        List<Income> actualIncomes = incomeExpenseService.viewIncomesByWherefrom(wherefrom);
        List<Income> expectedIncomes = new ArrayList<>( Arrays.asList(income3, income4) );

        verify(incomeDao).readAll();
        assertEquals(expectedIncomes, actualIncomes);
    }

    private User createUser() {
        User user = new User();
        user.setLogin("valera");
        user.setPassword("1111");
        user.setEmail("valera@gmail.com");
        return user;
    }

    private Wherefrom createWherefrom(String name) {
        Wherefrom wherefrom = new Wherefrom();
        wherefrom.setName(name);

        return wherefrom;
    }

    private Income createIncome(Double amount, Wherefrom wherefrom, Date date) {
        Income income = new Income();
        income.setAmount(amount);
        income.setWherefrom(wherefrom);
        income.setDate(date);
        income.setUser(createUser());

        return income;
    }
}
