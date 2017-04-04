package com.endava.controller;

import com.endava.model.MoneyTransfer;
import com.endava.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vsaban on 3/27/2017.
 */
@RestController
@RequestMapping("/api")
public class IncomeController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @GetMapping("/all-incomes")
    public List<MoneyTransfer> viewAllIncomes() {
        return moneyTransferService.getMoneyTransfers();
    }

}
