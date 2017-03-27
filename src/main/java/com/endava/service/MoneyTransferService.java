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
    void addMoneyTransfer(MoneyTransfer moneyTransfer);

    void deleteMoneyTransfer(MoneyTransfer moneyTransfer);

    void deleteMoneyTransferById(Long id);

    void updateMoneyTransfer(MoneyTransfer moneyTransfer);

    List<MoneyTransfer> getMoneyTransfer();

    List<MoneyTransfer> getMoneyTransferOn(Date date) throws IllegalArgumentException;

    List<MoneyTransfer> getMoneyTransferLastWeek();

    List<MoneyTransfer> getMoneyTransferLastMonth();

    List<MoneyTransfer> getMoneyTransferMoreExpensiveThan(Double amount);

    List<MoneyTransfer> getMoneyTransferByWherefrom(Wherefrom wherefrom);

    List<MoneyTransfer> getMoneyTransferByDomain(Domain domain);

}
