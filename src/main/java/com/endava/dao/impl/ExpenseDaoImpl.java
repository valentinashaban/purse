package com.endava.dao.impl;

import com.endava.dao.ExpenseDao;
import com.endava.model.Expense;

/**
 * Created by vsaban on 3/16/2017.
 */
public class ExpenseDaoImpl extends GenDaoImpl<Expense> implements ExpenseDao {
    public ExpenseDaoImpl() {
        super(Expense.class);
    }
}
