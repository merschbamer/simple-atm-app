package com.project.atm.service;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.atm.exception.InsufficientBalanceException;
import com.project.atm.repository.IAccountRepository;
import com.project.atm.repository.InMemoryAccountRepositoryImpl;
import com.project.atm.validator.InputValidator;

@Service
public class AccountServiceImpl implements IAccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private IAccountRepository accountRepository;

    private InputValidator validator;

    public AccountServiceImpl(@Autowired final InMemoryAccountRepositoryImpl accountRepository, @Autowired final InputValidator validator) {
        this.accountRepository = accountRepository;
        this.validator = validator;
    }

    @Override
    public BigDecimal getBalance() {
        return accountRepository.getBalance();
    }

    @Override
    public void deposit(final BigDecimal depositAmount) {
        validator.validateAmount(depositAmount);

        accountRepository.deposit(depositAmount);
    }

    @Override
    public void withdraw(final BigDecimal withdrawAmount) {
        validator.validateAmount(withdrawAmount);

        BigDecimal currentBalance = this.getBalance();

        if (currentBalance.compareTo(withdrawAmount) == -1) {
            throw new InsufficientBalanceException(MessageFormat.format("Insufficient funds for withdraw of amount: {0}", withdrawAmount.toString()));
        }

        accountRepository.withdraw(withdrawAmount);
    }
}
