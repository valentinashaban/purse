package com.endava.service.impl;

import com.endava.dao.MoneyTransferDao;
import com.endava.model.Domain;
import com.endava.model.MoneyTransfer;
import com.endava.model.Wherefrom;
import com.endava.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by vsaban on 3/21/2017.
 */

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {

    private final static long DAY_IN_MS = 1000 * 60 * 60 * 24;
    private final static int WEEK = 7;
    private final static int MONTH = 30;

    private MoneyTransferDao moneyTransferDao;

    @Autowired
    public void setMoneyTransferDao(MoneyTransferDao moneyTransferDao) {
        this.moneyTransferDao = moneyTransferDao;
    }

    @Override
    public MoneyTransfer addMoneyTransfer(@Valid @NotNull MoneyTransfer moneyTransfer) {
        moneyTransferDao.persist(moneyTransfer);
        return moneyTransfer;
    }

    @Override
    public void deleteMoneyTransfer(@Valid @NotNull MoneyTransfer moneyTransfer) {
        moneyTransferDao.delete(moneyTransfer);
    }

    @Override
    public void deleteMoneyTransferById(@NotNull Long id) {
        Optional.of(id)
                .filter(i -> i > 0)
                .ifPresent(i -> moneyTransferDao.deleteById(i));
    }

    @Override
    public MoneyTransfer updateMoneyTransfer(@Valid MoneyTransfer moneyTransfer) {
        return moneyTransferDao.merge(moneyTransfer);
    }

    public MoneyTransfer getMoneyTransferById(@NotNull Long id) {
        return moneyTransferDao.read(id);
    }

    @Override
    public List<MoneyTransfer> getMoneyTransfers() {
        return moneyTransferDao.readAll();
    }

    @Override
    public List<MoneyTransfer> getMoneyTransferOn(Date date) {

        if (date == null || date.after(this.getCurrentDate()))
            throw new IllegalArgumentException();

        return moneyTransferDao.readAll().stream()
                .filter(m -> m.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyTransfer> getMoneyTransferLastWeek() {

        return moneyTransferDao.readAll().stream()
                .filter(m -> m.getDate().after(this.getDateWeekAgo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyTransfer> getMoneyTransferLastMonth() {

        return moneyTransferDao.readAll().stream()
                .filter(m -> m.getDate().after(this.getDateMonthAgo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyTransfer> getMoneyTransferMoreExpensiveThan(Double amount) {

        if (amount == null || amount < 0)
            throw new IllegalArgumentException();

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getAmount() >= amount)
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyTransfer> getMoneyTransferByWherefrom(@Valid Wherefrom wherefrom) {

        Optional.ofNullable(wherefrom).orElseThrow(IllegalArgumentException::new);

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getWherefrom().equals(wherefrom))
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyTransfer> getMoneyTransferByDomain(@Valid Domain domain) {

        Optional.ofNullable(domain).orElseThrow(IllegalArgumentException::new);

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getDomain().equals(domain))
                .collect(Collectors.toList());
    }

    public Date getCurrentDate() { return new Date(System.currentTimeMillis()); }

    private Date getDateWeekAgo() {
        return new Date(this.getCurrentDate().getTime() - (WEEK * DAY_IN_MS));
    }

    private Date getDateMonthAgo() {
        return new Date(this.getCurrentDate().getTime() - (MONTH * DAY_IN_MS));
    }

}
