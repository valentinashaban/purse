package com.endava.service.impl;

import com.endava.dao.MoneyTransferDao;
import com.endava.model.Domain;
import com.endava.model.MoneyTransfer;
import com.endava.model.Wherefrom;
import com.endava.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public MoneyTransfer saveMoneyTransfer(@Valid @NotNull MoneyTransfer moneyTransfer) {
        moneyTransferDao.persist(moneyTransfer);
        return moneyTransfer;
    }

    @Override
    @Transactional
    public void deleteMoneyTransfer(@Valid @NotNull MoneyTransfer moneyTransfer) {
        moneyTransferDao.delete(moneyTransfer);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteMoneyTransferById(@NotNull Long id) {
        Optional.of(id)
                .filter(i -> i > 0)
                .ifPresent(i -> moneyTransferDao.deleteById(i));
    }

    @Override
    @Transactional
    public MoneyTransfer updateMoneyTransfer(@Valid MoneyTransfer moneyTransfer) {
        return moneyTransferDao.merge(moneyTransfer);
    }

    @Override
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

    public List<MoneyTransfer> getMoneyTransferByCategory(String category) {
        Optional.ofNullable(category).orElseThrow(IllegalArgumentException::new);

        if (category == "income")
            return this.getMoneyTransferByWherefromName(category);
        else if (category == "expense")
            return this.getMoneyTransferByDomainName(category);
        else
            throw new IllegalArgumentException();
    }

    private List<MoneyTransfer> getMoneyTransferByWherefrom(@Valid Wherefrom wherefrom) {

        Optional.ofNullable(wherefrom).orElseThrow(IllegalArgumentException::new);

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getWherefrom().equals(wherefrom))
                .collect(Collectors.toList());
    }

    private List<MoneyTransfer> getMoneyTransferByWherefromName(@Valid String wherefromName) {

        Optional.ofNullable(wherefromName).orElseThrow(IllegalArgumentException::new);

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getWherefrom().getName().equals(wherefromName))
                .collect(Collectors.toList());
    }

    private List<MoneyTransfer> getMoneyTransferByDomain(@Valid Domain domain) {

        Optional.ofNullable(domain).orElseThrow(IllegalArgumentException::new);

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getDomain().equals(domain))
                .collect(Collectors.toList());
    }

    private List<MoneyTransfer> getMoneyTransferByDomainName(@Valid String domainName) {

        Optional.ofNullable(domainName).orElseThrow(IllegalArgumentException::new);

        return moneyTransferDao.readAll().stream()
                .filter(i -> i.getDomain().getName().equals(domainName))
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
