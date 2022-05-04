package com.project.atm.service;

import java.math.BigDecimal;

public interface IAccountService {

    BigDecimal getBalance();

    void deposit(final BigDecimal depositAmount);

    void withdraw(final BigDecimal withdrawAmount);
}
