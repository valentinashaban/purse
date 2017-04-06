package com.endava.controller;

import com.endava.dao.DomainDao;
import com.endava.dao.WherefromDao;
import com.endava.model.Domain;
import com.endava.model.Wherefrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by vsaban on 4/5/2017.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private WherefromDao wherefromDao;  // TODO

    @Autowired
    private DomainDao domainDao;

    @Transactional      // TODO
    @PostMapping("/add-wherefrom")
    public ResponseEntity<Wherefrom> addWherefrom(@RequestBody Wherefrom wherefrom) {
        if (null == wherefrom)
            return new ResponseEntity<>(wherefrom, NO_CONTENT);

        wherefromDao.persist(wherefrom);

        return new ResponseEntity<>(wherefrom, OK);
    }

    @Transactional
    @PostMapping("/add-domain")
    public ResponseEntity<Domain> addDomain(@RequestBody Domain domain) {
        if (null == domain)
            return new ResponseEntity<>(domain, NO_CONTENT);

        domainDao.persist(domain);

        return new ResponseEntity<>(domain, OK);
    }

    @GetMapping("/all-wherefroms")
    public ResponseEntity<List<Wherefrom>> getAllWherefroms() {
        return new ResponseEntity<>(wherefromDao.readAll(), OK);
    }

    @Transactional
    @DeleteMapping("/delete-wherefroms")
    public ResponseEntity<Void> deleteAllWherefroms() {
        wherefromDao.deleteList(wherefromDao.readAll());
        return new ResponseEntity<>(OK);
    }
}
