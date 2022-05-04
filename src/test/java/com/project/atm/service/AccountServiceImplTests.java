package com.project.atm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project.atm.exception.InsufficientBalanceException;
import com.project.atm.exception.InvalidAmountException;
import com.project.atm.repository.InMemoryAccountRepositoryImpl;
import com.project.atm.validator.InputValidator;

public class AccountServiceImplTests {

    private IAccountService accountService;

    private InMemoryAccountRepositoryImpl accountRepositoryMock;

    private InputValidator validatorMock;

    @BeforeEach
    public void init() {
        accountRepositoryMock = Mockito.mock(InMemoryAccountRepositoryImpl.class);
        validatorMock = Mockito.mock(InputValidator.class);

        accountService = new AccountServiceImpl(accountRepositoryMock, validatorMock);
    }

    @Test
    public void testGetBalance_callsRepositoryGetBalance() {
        when(accountRepositoryMock.getBalance()).thenReturn(new BigDecimal(100));

        BigDecimal balance = accountService.getBalance();

        assertEquals(new BigDecimal(100), balance);
    }

    @Test
    public void testDeposit_throwsWhenValidatorThrows() {
        BigDecimal depositAmount = new BigDecimal(100.12314);

        Mockito.doThrow(new InvalidAmountException("invalid amount")).when(validatorMock).validateAmount(depositAmount);

        assertThrows(InvalidAmountException.class, () -> {
            accountService.deposit(depositAmount);
        });
    }

    @Test
    public void testDeposit_success() {
        BigDecimal depositAmount = new BigDecimal(100.12);

        accountService.deposit(depositAmount);
    }

    @Test
    public void testWithdraw_throwsWhenValidatorThrows() {
        BigDecimal withdrawAmount = new BigDecimal(100.12314);

        Mockito.doThrow(new InvalidAmountException("invalid amount")).when(validatorMock).validateAmount(withdrawAmount);

        assertThrows(InvalidAmountException.class, () -> {
            accountService.withdraw(withdrawAmount);
        });
    }

    @Test
    public void testWithdraw_throwsWhenNotEnoughFunds() {
        BigDecimal withdrawAmount = new BigDecimal(100);

        when(accountRepositoryMock.getBalance()).thenReturn(new BigDecimal(10));

        assertThrows(InsufficientBalanceException.class, () -> {
            accountService.withdraw(withdrawAmount);
        });
    }

    @Test
    public void testWithdraw_Success() {
        BigDecimal withdrawAmount = new BigDecimal(100);

        when(accountRepositoryMock.getBalance()).thenReturn(new BigDecimal(1000));

        accountService.withdraw(withdrawAmount);
    }
}
