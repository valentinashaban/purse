package com.endava.service.impl;

import com.endava.dao.ExpenseDao;
import com.endava.dao.IncomeDao;
import com.endava.model.Domain;
import com.endava.model.Expense;
import com.endava.model.Income;
import com.endava.model.Wherefrom;
import com.endava.service.IncomeExpenseService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by vsaban on 3/21/2017.
 */

@Data
@Service
public class IncomeExpenseServiceImpl implements IncomeExpenseService {

    private final long DAY_IN_MS = 1000 * 60 * 60 * 24;

    @Autowired
    private IncomeDao incomeDao;

    @Autowired
    private ExpenseDao expenseDao;

    private Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date getDateWeekAgo() {
        return new Date(this.getCurrentDate().getTime() - (7 * DAY_IN_MS));
    }

    private Date getDateMonthAgo() {
        return new Date(this.getCurrentDate().getTime() - (30 * DAY_IN_MS));
    }

    @Override
    public void addIncome(@Valid @NotNull Income income) {
        incomeDao.create(income);
    }

    @Override
    public void addExpense(@Valid @NotNull Expense expense) {
        expenseDao.create(expense);
    }

    @Override
    public void deleteIncome(@Valid @NotNull Income income) {
        incomeDao.delete(income);
    }

    @Override
    public void deleteExpense(@Valid @NotNull Expense expense) {
        expenseDao.delete(expense);
    }

    @Override
    public void deleteIncomeById(@NotNull Long id) {
        if (id > 0)
            incomeDao.deleteById(id);
    }

    @Override
    public void deleteExpenseById(@NotNull Long id) {
        if (id > 0)
            incomeDao.deleteById(id);
    }

    @Override
    public void updateIncome(@Valid Income income) {
        incomeDao.update(income);
    }

    @Override
    public void updateExpense(@Valid Expense expense) {
        expenseDao.update(expense);
    }

    @Override
    public List<Income> viewIncomes() {
        return incomeDao.readAll();
    }

    @Override
    public List<Expense> viewExpenses() {
        return expenseDao.readAll();
    }

    @Override
    public List<Income> viewIncomesOn(Date date) {

        if (date == null || date.after(this.getCurrentDate()))
            throw new IllegalArgumentException();

        return incomeDao.readAll().stream()
                .filter(i -> i.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> viewExpensesOn(Date date) {

        if (date == null || date.after(this.getCurrentDate()))
            throw new IllegalArgumentException();

        return expenseDao.readAll().stream()
                .filter(e -> e.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Income> viewIncomesLastWeek() {

        return incomeDao.readAll().stream()
                .filter(i -> i.getDate().after(this.getDateWeekAgo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> viewExpensesLastWeek() {

        return expenseDao.readAll().stream()
                .filter(e -> e.getDate().after(this.getDateWeekAgo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Income> viewIncomesLastMonth() {

        return incomeDao.readAll().stream()
                .filter(i -> i.getDate().after(this.getDateMonthAgo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> viewExpensesLastMonth() {

        return expenseDao.readAll().stream()
                .filter(e -> e.getDate().after(this.getDateMonthAgo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Income> viewIncomesMoreExpensiveThan(Double amount) {

        if (amount == null || amount < 0)
            throw new IllegalArgumentException();

        return incomeDao.readAll().stream()
                .filter(i -> i.getAmount() >= amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> viewExpensesMoreExpensiveThan(Double amount) {

        if (amount == null || amount < 0)
            return new ArrayList<>();

        return expenseDao.readAll().stream()
                .filter(e -> e.getAmount() >= amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Income> viewIncomesByWherefrom(@Valid Wherefrom wherefrom) {

        Optional.ofNullable(wherefrom).orElseThrow(() -> new IllegalArgumentException());

        return incomeDao.readAll().stream()
                .filter(i -> i.getWherefrom().equals(wherefrom))
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> viewExpensesByDomain(@Valid Domain domain) {

        Optional.ofNullable(domain).orElseThrow(() -> new IllegalArgumentException());

        return expenseDao.readAll().stream()
                .filter(i -> i.getDomain().equals(domain))
                .collect(Collectors.toList());
    }
}
