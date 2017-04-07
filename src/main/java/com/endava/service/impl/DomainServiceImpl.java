package com.endava.service.impl;

import com.endava.dao.DomainDao;
import com.endava.model.Domain;
import com.endava.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vsaban on 4/7/2017.
 */
@Service
public class DomainServiceImpl implements DomainService {

    private DomainDao domainDao;

    @Autowired
    public void setDomainDao(DomainDao domainDao) {
        this.domainDao = domainDao;
    }

    @Override
    @Transactional
    public Domain saveDomain(Domain domain) {
        domainDao.persist(domain);
        return domain;
    }

    @Override
    @Transactional
    public void deleteDomain(Domain domain) {
        domainDao.delete(domain);
    }

    @Override
    @Transactional
    public Domain updateDomain(Domain domain) {
        domainDao.merge(domain);
        return domain;
    }

    @Override
    public Domain getDomainById(Long id) {
        return domainDao.read(id);
    }

    @Override
    public List<Domain> getDomains() {
        return domainDao.readAll();
    }
}
