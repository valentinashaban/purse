package com.endava.controller;

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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vsaban on 4/5/2017.
 */
@Controller
public class MoneyTransferController {

    private final UserService userService;
    private final WherefromService wherefromService;
    private final DomainService domainService;
    private final MoneyTransferService moneyTransferService;

    @Autowired
    public MoneyTransferController(final UserService userService,
                                   final WherefromService wherefromService,
                                   final DomainService domainService,
                                   final MoneyTransferService moneyTransferService) {
        this.userService = userService;
        this.wherefromService = wherefromService;
        this.domainService = domainService;
        this.moneyTransferService = moneyTransferService;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Wherefrom.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                Wherefrom wherefrom = wherefromService.getWherefromById(Long.parseLong(id));
                setValue(wherefrom);
            }
        });

        binder.registerCustomEditor(Domain.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                Domain domain = domainService.getDomainById(Long.parseLong(id));
                setValue(domain);
            }
        });

        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @GetMapping("/money-transfer")
    public String addMoneyTransfer(final Model model) {
        model.addAttribute("moneyTransfer", new MoneyTransfer());
        model.addAttribute("transferTypes", MoneyTransferType.values());
        model.addAttribute("wherefroms", wherefromService.getWherefroms());
        model.addAttribute("domains", domainService.getDomains());
        return "add-money-transfer";
    }

    @PostMapping("/money-transfer")
    public String addMoneyTransfer(@ModelAttribute final MoneyTransfer moneyTransfer) {
        final User user = userService.getAuthenticatedUser(SecurityContextHolder.getContext());

        moneyTransfer.setUser(user);
        moneyTransferService.saveMoneyTransfer(moneyTransfer);

        String page;
        if (moneyTransfer.isIncome())
            page = "redirect:/incomes";
        else if (moneyTransfer.isExpense())
            page = "redirect:/expenses";
        else
            page = "index";

        return page;
    }

    @GetMapping("/expenses")
    public String expenses(final Model model) {
        final User user = userService.getAuthenticatedUser(SecurityContextHolder.getContext());

        model.addAttribute("expenses", moneyTransferService.getExpenses(user));
        return "expenses";
    }

    @GetMapping("/incomes")
    public String incomes(final Model model) {
        final User user = userService.getAuthenticatedUser(SecurityContextHolder.getContext());

        model.addAttribute("incomes", moneyTransferService.getIncomes(user));
        return "incomes";
    }

}
