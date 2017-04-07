package com.endava.service;

import com.endava.config.IntegrationTestConfig;
import com.endava.dao.DomainDao;
import com.endava.model.Domain;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vsaban on 4/7/2017.
 */
@RunWith(SpringRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class DomainServiceImplIT {

    @Autowired
    private DomainDao domainDao;

    @Autowired
    private DomainService domainService;

    private Domain domain;

    @Before
    public void setUp() {
        domain = new Domain();
        domain.setName("food");
    }

    @Test
    public void testSaveDomain() {

        Domain actualDomain = domainService.saveDomain(domain);

        assertNotNull(actualDomain);
        assertEquals(domain, actualDomain);
    }

    @Test
    public void testDeleteDomain() {

        Domain actualDomain = domainService.saveDomain(domain);

        assertNotNull(actualDomain);

        domainService.deleteDomain(actualDomain);

        assertFalse(domainDao.exists(actualDomain));
    }

    @Test
    public void testUpdateDomain() {
        Domain persistedDomain = domainService.saveDomain(domain);

        assertNotNull(persistedDomain);

        persistedDomain.setName("other name");
        Domain updatedDomain = domainService.updateDomain(domain);

        assertNotNull(updatedDomain);
        assertEquals(persistedDomain, updatedDomain);
    }

    @Test
    public void testGetDomainById() {
        Domain persistedDomain = domainService.saveDomain(domain);

        assertNotNull(persistedDomain);

        Domain pulledDomain = domainService.getDomainById(persistedDomain.getId());

        assertNotNull(pulledDomain);
        assertEquals(persistedDomain, pulledDomain);
    }

    @Test
    public void testGetDomains() {
        Domain persistedDomain = domainService.saveDomain(domain);

        assertNotNull(persistedDomain);

        List<Domain> domains = domainService.getDomains();

        assertNotNull(domains);
        assertEquals(Collections.singletonList(persistedDomain), domains);
    }
}
