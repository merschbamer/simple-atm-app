package com.project.atm.repository;

import java.math.BigDecimal;

public interface IAccountRepository {

    BigDecimal getBalance();

    void deposit(BigDecimal depositAmount);

    void withdraw(BigDecimal withdrawAmount);
}
