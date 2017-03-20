package com.endava.dao.impl;

import com.endava.dao.DomainDao;
import com.endava.model.Domain;

/**
 * Created by vsaban on 3/16/2017.
 */
public class DomainDaoImpl extends GenDaoImpl<Domain> implements DomainDao {
    public DomainDaoImpl() {
        super(Domain.class);
    }
}
