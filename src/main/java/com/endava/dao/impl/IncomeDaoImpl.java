package com.endava.dao.impl;

import com.endava.dao.IncomeDao;
import com.endava.model.Income;

/**
 * Created by vsaban on 3/16/2017.
 */
public class IncomeDaoImpl extends GenDaoImpl<Income> implements IncomeDao {
    public IncomeDaoImpl() {
        super(Income.class);
    }
}
