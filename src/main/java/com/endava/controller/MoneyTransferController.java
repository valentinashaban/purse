package com.endava.controller;

import com.endava.dao.DomainDao;
import com.endava.dao.UserDao;
import com.endava.dao.WherefromDao;
import com.endava.enums.MoneyTransferType;
import com.endava.model.Domain;
import com.endava.model.MoneyTransfer;
import com.endava.model.User;
import com.endava.model.Wherefrom;
import com.endava.service.DomainService;
import com.endava.service.MoneyTransferService;
import com.endava.service.UserService;
import com.endava.service.WherefromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;

import static com.endava.enums.Role.USER;

/**
 * Created by vsaban on 4/5/2017.
 */
@Controller
@RequestMapping("/money-transfer")
public class MoneyTransferController {

    private UserService userService;
    private WherefromService wherefromService;
    private DomainService domainService;
    private MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyTransferController(WherefromService wherefromService,
                                   DomainService domainService,
                                   UserService userService,
                                   MoneyTransferService moneyTransferService) {
        this.wherefromService = wherefromService;
        this.domainService = domainService;
        this.userService = userService;
        this.moneyTransferService = moneyTransferService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Wherefrom.class, new PropertyEditorSupport(){

            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                Wherefrom wherefrom = wherefromService.getWherefromById(Long.parseLong(id));
                setValue(wherefrom);
            }
        });

        binder.registerCustomEditor(Domain.class, new PropertyEditorSupport(){

            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                Domain domain = domainService.getDomainById(Long.parseLong(id));
                setValue(domain);
            }
        });
    }

    @GetMapping("/add")
    public String addMoneyTransfer(Model model) {
        List<MoneyTransferType> transferTypes = Arrays.asList(MoneyTransferType.values());

        List<Wherefrom> wherefroms = wherefromService.getWherefroms();
        List<Domain> domains = domainService.getDomains();

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
        userService.saveUser(user);

        moneyTransfer.setUser(user);

        moneyTransferService.saveMoneyTransfer(moneyTransfer);
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
