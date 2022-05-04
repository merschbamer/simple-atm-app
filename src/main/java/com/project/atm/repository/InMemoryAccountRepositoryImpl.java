package com.project.atm.repository;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAccountRepositoryImpl implements IAccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryAccountRepositoryImpl.class);

    private BigDecimal balance = new BigDecimal(0);

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public synchronized void deposit(final BigDecimal depositAmount) {
        balance = balance.add(depositAmount);
    }

    @Override
    public synchronized void withdraw(final BigDecimal withdrawAmount) {
        balance = balance.subtract(withdrawAmount);
    }
}
