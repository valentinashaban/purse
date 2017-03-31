package com.endava.service;

import com.endava.model.Domain;
import com.endava.model.MoneyTransfer;
import com.endava.model.Wherefrom;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by vsaban on 3/23/2017.
 */
@Service
public interface MoneyTransferService {
    MoneyTransfer addMoneyTransfer(MoneyTransfer moneyTransfer);

    void deleteMoneyTransfer(MoneyTransfer moneyTransfer);

    void deleteMoneyTransferById(Long id);

    MoneyTransfer updateMoneyTransfer(MoneyTransfer moneyTransfer);

    MoneyTransfer getMoneyTransferById(Long id);

    List<MoneyTransfer> getMoneyTransfers();

    List<MoneyTransfer> getMoneyTransferOn(Date date) throws IllegalArgumentException;

    List<MoneyTransfer> getMoneyTransferLastWeek();

    List<MoneyTransfer> getMoneyTransferLastMonth();

    List<MoneyTransfer> getMoneyTransferMoreExpensiveThan(Double amount);

    List<MoneyTransfer> getMoneyTransferByCategory(Object category);

}
