package com.endava.service;

import com.endava.model.Domain;
import com.endava.model.Expense;
import com.endava.model.Income;
import com.endava.model.Wherefrom;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vsaban on 3/23/2017.
 */
@Service
public interface IncomeExpenseService {

    void addIncome(Income income);
    void addExpense(Expense expense);

    void deleteIncome(Income income);
    void deleteExpense(Expense expense);
    void deleteIncomeById(Long id);
    void deleteExpenseById(Long id);

    void updateIncome(Income income);
    void updateExpense(Expense expense);

    List<Income> viewIncomes();
    List<Expense> viewExpenses();

    List<Income> viewIncomesOn(Date date) throws IllegalArgumentException;
    List<Expense> viewExpensesOn(Date date);

    List<Income> viewIncomesLastWeek();
    List<Expense> viewExpensesLastWeek();

    List<Income> viewIncomesLastMonth();
    List<Expense> viewExpensesLastMonth();

    List<Income> viewIncomesMoreExpensiveThan(Double amount);
    List<Expense> viewExpensesMoreExpensiveThan(Double amount);

    List<Income> viewIncomesByWherefrom(Wherefrom wherefrom);
    List<Expense> viewExpensesByDomain(Domain domain);
}
