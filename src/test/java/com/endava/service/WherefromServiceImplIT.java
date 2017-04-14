package com.endava.service;

import com.endava.config.IntegrationTestConfig;
import com.endava.dao.WherefromDao;
import com.endava.model.Wherefrom;
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

import static com.endava.StaticReusedVariables.WHEREFROM_CASH_NAME;
import static com.endava.StaticReusedVariables.createWherefrom;
import static org.junit.Assert.*;

/**
 * Created by vsaban on 4/7/2017.
 */
@RunWith(SpringRunner.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
@ContextConfiguration(classes = IntegrationTestConfig.class)
public class WherefromServiceImplIT {

    @Autowired
    private WherefromDao wherefromDao;

    @Autowired
    private WherefromService wherefromService;

    private Wherefrom wherefrom;

    @Before
    public void setUp() {
        wherefrom = createWherefrom(WHEREFROM_CASH_NAME);
    }

    @Test
    public void testSaveWherefrom() {

        Wherefrom actualWherefrom = wherefromService.saveWherefrom(wherefrom);

        assertNotNull(actualWherefrom);
        assertEquals(wherefrom, actualWherefrom);
    }

    @Test
    public void testDeleteWherefrom() {

        Wherefrom actualWherefrom = wherefromService.saveWherefrom(wherefrom);

        assertNotNull(actualWherefrom);

        wherefromService.deleteWherefrom(actualWherefrom);

        assertFalse(wherefromDao.exists(actualWherefrom));
    }

    @Test
    public void testUpdateWherefrom() {
        Wherefrom persistedWherefrom = wherefromService.saveWherefrom(wherefrom);

        assertNotNull(persistedWherefrom);

        persistedWherefrom.setName("other name");
        Wherefrom updatedWherefrom = wherefromService.updateWherefrom(wherefrom);

        assertNotNull(updatedWherefrom);
        assertEquals(persistedWherefrom, updatedWherefrom);
    }

    @Test
    public void testGetWherefromById() {
        Wherefrom persistedWherefrom = wherefromService.saveWherefrom(wherefrom);

        assertNotNull(persistedWherefrom);

        Wherefrom pulledWherefrom = wherefromService.getWherefromById(persistedWherefrom.getId());

        assertNotNull(pulledWherefrom);
        assertEquals(persistedWherefrom, pulledWherefrom);
    }

    @Test
    public void testGetWherefroms() {
        Wherefrom persistedWherefrom = wherefromService.saveWherefrom(wherefrom);

        assertNotNull(persistedWherefrom);

        List<Wherefrom> wherefroms = wherefromService.getWherefroms();

        assertNotNull(wherefroms);
        assertEquals(Collections.singletonList(persistedWherefrom), wherefroms);
    }
}
