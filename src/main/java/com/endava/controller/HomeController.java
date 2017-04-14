package com.endava.controller;

import com.endava.model.Domain;
import com.endava.model.MoneyTransfer;
import com.endava.model.Wherefrom;
import com.endava.service.DomainService;
import com.endava.service.MoneyTransferService;
import com.endava.service.WherefromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * Created by vsaban on 3/27/2017.
 */
@Controller
public class HomeController {

    private WherefromService wherefromService;
    private DomainService domainService;
    private MoneyTransferService moneyTransferService;

    @Autowired
    public HomeController(WherefromService wherefromService,
                          DomainService domainService,
                          MoneyTransferService moneyTransferService) {
        this.wherefromService = wherefromService;
        this.domainService = domainService;
        this.moneyTransferService = moneyTransferService;
    }

    @GetMapping(value = {"/", "/index"})
    public String homePage(Model model) {
        model.addAttribute("wherefroms", wherefromService.getWherefroms());
        model.addAttribute("domains", domainService.getDomains());
        model.addAttribute("moneyTransfers", moneyTransferService.getMoneyTransfers());

        return "index";
    }
}
