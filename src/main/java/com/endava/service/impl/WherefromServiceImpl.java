package com.endava.service.impl;

import com.endava.dao.WherefromDao;
import com.endava.model.Wherefrom;
import com.endava.service.WherefromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vsaban on 4/7/2017.
 */
@Service
public class WherefromServiceImpl implements WherefromService {

    private WherefromDao wherefromDao;

    @Autowired
    public void setWherefromDao(WherefromDao wherefromDao) {
        this.wherefromDao = wherefromDao;
    }

    @Override
    @Transactional
    public Wherefrom saveWherefrom(Wherefrom wherefrom) {
        wherefromDao.persist(wherefrom);
        return wherefrom;
    }

    @Override
    @Transactional
    public void deleteWherefrom(Wherefrom wherefrom) {
        wherefromDao.delete(wherefrom);
    }

    @Override
    @Transactional
    public Wherefrom updateWherefrom(Wherefrom wherefrom) {
        wherefromDao.merge(wherefrom);
        return wherefrom;
    }

    @Override
    public Wherefrom getWherefromById(Long id) {
        return wherefromDao.read(id);
    }

    @Override
    public List<Wherefrom> getWherefroms() {
        return wherefromDao.readAll();
    }
}
