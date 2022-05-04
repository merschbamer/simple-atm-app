package com.project.atm.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InMemoryAccountRepositoryImplTests {

    private IAccountRepository accountRepository;

    @BeforeEach
    public void init() {
        accountRepository = new InMemoryAccountRepositoryImpl();
    }

    @Test
    public void testDefaultBalance_initializedToZero() {
        assertEquals(new BigDecimal(0), accountRepository.getBalance());
    }

    @Test
    public void testDeposit_increasesBalance() {
        assertEquals(new BigDecimal(0), accountRepository.getBalance());

        accountRepository.deposit(new BigDecimal(100));

        assertEquals(new BigDecimal(100), accountRepository.getBalance());
    }

    @Test
    public void testWithdraw_decreasesBalance() {
        assertEquals(new BigDecimal(0), accountRepository.getBalance());

        accountRepository.withdraw(new BigDecimal(100));

        assertEquals(new BigDecimal(-100), accountRepository.getBalance());
    }
}
