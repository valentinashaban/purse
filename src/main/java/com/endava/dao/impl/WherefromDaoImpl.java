package com.endava.dao.impl;

import com.endava.dao.WherefromDao;
import com.endava.model.Wherefrom;

/**
 * Created by vsaban on 3/16/2017.
 */
public class WherefromDaoImpl extends GenDaoImpl<Wherefrom> implements WherefromDao {
    public WherefromDaoImpl() {
        super(Wherefrom.class);
    }
}
