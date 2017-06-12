package com.endava.service;

import com.endava.model.Wherefrom;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vsaban on 4/7/2017.
 */
@Service
public interface WherefromService {
    Wherefrom saveWherefrom(Wherefrom wherefrom);

    void deleteWherefrom(Wherefrom wherefrom);

    void deleteWherefroms(List<Wherefrom> wherefroms);

    Wherefrom updateWherefrom(Wherefrom wherefrom);

    Wherefrom getWherefromById(Long id);

    List<Wherefrom> getWherefroms();
}
