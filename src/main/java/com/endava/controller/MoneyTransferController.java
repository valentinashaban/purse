package com.endava.controller;

import com.endava.dao.DomainDao;
import com.endava.dao.UserDao;
import com.endava.dao.WherefromDao;
import com.endava.enums.MoneyTransferType;
import com.endava.model.Domain;
import com.endava.model.MoneyTransfer;
import com.endava.model.User;
import com.endava.model.Wherefrom;
import com.endava.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.endava.enums.Role.USER;

/**
 * Created by vsaban on 4/5/2017.
 */
@Controller
@RequestMapping("/money-transfer")
public class MoneyTransferController {

    private WherefromDao wherefromDao;
    private DomainDao domainDao;
    private UserDao userDao;
    private MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyTransferController(WherefromDao wherefromDao,
                                   DomainDao domainDao,
                                   UserDao userDao,
                                   MoneyTransferService moneyTransferService) {
        this.wherefromDao = wherefromDao;
        this.domainDao = domainDao;
        this.userDao = userDao;
        this.moneyTransferService = moneyTransferService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        //sdf.setLenient(true);
        //binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

        binder.registerCustomEditor(Wherefrom.class, new PropertyEditorSupport(){

            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                Wherefrom wherefrom = wherefromDao.read(Long.parseLong(id));
                setValue(wherefrom);
            }
        });

        binder.registerCustomEditor(Domain.class, new PropertyEditorSupport(){

            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                Domain domain = domainDao.read(Long.parseLong(id));
                setValue(domain);
            }
        });
    }

    @GetMapping("/add")
    public String addMoneyTransfer(Model model) {
        List<MoneyTransferType> transferTypes = Arrays.asList(MoneyTransferType.values());

        List<Wherefrom> wherefroms = wherefromDao.readAll();
        List<Domain> domains = domainDao.readAll();

        model.addAttribute("moneyTransfer", new MoneyTransfer());
        model.addAttribute("transferTypes", transferTypes);
        model.addAttribute("wherefroms", wherefroms);
        model.addAttribute("domains", domains);
        return "add-money-transfer";
    }

    @Transactional
    @PostMapping("/add")
    public String addMoneyTransfer(@ModelAttribute MoneyTransfer moneyTransfer, HttpServletRequest req) {
        User user = createUser();  //TODO
        userDao.persist(user);

        moneyTransfer.setUser(user);

        moneyTransferService.addMoneyTransfer(moneyTransfer);
        return "index";
    }

    private User createUser() {
        return User.builder()
                   .withLogin("valera")
                   .withPassword("111111")
                   .withRole(USER)
                   .build();
    }
}
