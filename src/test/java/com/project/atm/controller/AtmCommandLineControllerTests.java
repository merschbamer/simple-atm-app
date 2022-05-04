package com.project.atm.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project.atm.service.AccountServiceImpl;
import com.project.atm.service.IAccountService;
import com.project.atm.validator.InputValidator;

public class AtmCommandLineControllerTests {

    private AtmCommandLineController atmCommandLineController;

    private Scanner scanner;

    private IAccountService accountServiceMock;

    private InputValidator inputValidatorMock;

    public void setup(String userInput) {
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        scanner = new Scanner(System.in);

        accountServiceMock = Mockito.mock(AccountServiceImpl.class);
        inputValidatorMock = Mockito.mock(InputValidator.class);

        atmCommandLineController = new AtmCommandLineController(scanner, accountServiceMock, inputValidatorMock);
        atmCommandLineController = Mockito.spy(atmCommandLineController);
    }

    @Test
    public void testStart_CallsHandleLogin_WhenUserEnters1() {
        setup("1");

        Mockito.doNothing().when(atmCommandLineController).handleLogin();

        atmCommandLineController.start();

        Mockito.verify(atmCommandLineController, times(1)).handleLogin();
    }

    @Test
    public void testStart_CallsHandleLExit_WhenUserEnters2() {
        setup("2");

        Mockito.doNothing().when(atmCommandLineController).handleExit();

        atmCommandLineController.start();

        Mockito.verify(atmCommandLineController, times(1)).handleExit();
    }

    @Test
    public void testHandleLogin_CallsAccountActionMenu_WhenUserEntersValidPin() {
        setup("1234");

        Mockito.doCallRealMethod().when(inputValidatorMock).validatePin("1234");
        Mockito.doNothing().when(atmCommandLineController).accountActionMenu();

        atmCommandLineController.handleLogin();

        Mockito.verify(atmCommandLineController, times(1)).accountActionMenu();
    }

    @Test
    public void testHandleLogin_CallsStart_WhenUserEntersInvalidPin() {
        setup("1");

        Mockito.doCallRealMethod().when(inputValidatorMock).validatePin("1");
        Mockito.doNothing().when(atmCommandLineController).start();

        atmCommandLineController.handleLogin();

        Mockito.verify(atmCommandLineController, times(1)).start();
    }

    @Test
    public void testAccountActionMenu_CallsHandleViewBalance_WhenUserEnters1() {
        setup("1");

        Mockito.doNothing().when(atmCommandLineController).handleViewBalance();

        atmCommandLineController.accountActionMenu();

        Mockito.verify(atmCommandLineController, times(1)).handleViewBalance();
    }

    @Test
    public void testAccountActionMenu_CallsHandleDeposit_WhenUserEnters2() {
        setup("2");

        Mockito.doNothing().when(atmCommandLineController).handleDeposit();

        atmCommandLineController.accountActionMenu();

        Mockito.verify(atmCommandLineController, times(1)).handleDeposit();
    }

    @Test
    public void testAccountActionMenu_CallsHandleWithdrawWhenUserEnters3() {
        setup("3");

        Mockito.doNothing().when(atmCommandLineController).handleWithdraw();

        atmCommandLineController.accountActionMenu();

        Mockito.verify(atmCommandLineController, times(1)).handleWithdraw();
    }

    @Test
    public void testAccountActionMenu_CallsHandleLogout_WhenUserEnters4() {
        setup("4");

        Mockito.doNothing().when(atmCommandLineController).handleLogout();

        atmCommandLineController.accountActionMenu();

        Mockito.verify(atmCommandLineController, times(1)).handleLogout();
    }

    @Test
    public void testHandleViewBalance_getsBalanceAndReturnsToAccountActionMenu() {
        setup("4");

        when(accountServiceMock.getBalance()).thenReturn(new BigDecimal(100));
        Mockito.doNothing().when(atmCommandLineController).accountActionMenu();

        atmCommandLineController.handleViewBalance();

        Mockito.verify(accountServiceMock, times(1)).getBalance();
        Mockito.verify(atmCommandLineController, times(1)).accountActionMenu();
    }

    @Test
    public void testHandleDeposit_depositsAmountAndReturnsToAccountActionMenu() {
        setup("100");

        Mockito.doNothing().when(atmCommandLineController).accountActionMenu();

        atmCommandLineController.handleDeposit();

        Mockito.verify(accountServiceMock, times(1)).deposit(new BigDecimal("100"));
        Mockito.verify(atmCommandLineController, times(1)).accountActionMenu();
    }

    @Test
    public void testHandleWithdraw_withdrawsAmountAndReturnsToAccountActionMenu() {
        setup("100");

        Mockito.doNothing().when(atmCommandLineController).accountActionMenu();

        atmCommandLineController.handleWithdraw();

        Mockito.verify(accountServiceMock, times(1)).withdraw(new BigDecimal("100"));
        Mockito.verify(atmCommandLineController, times(1)).accountActionMenu();
    }

    @Test
    public void testHandleLogout_callsStart() {
        setup("100");

        Mockito.doNothing().when(atmCommandLineController).start();

        atmCommandLineController.handleLogout();

        Mockito.verify(atmCommandLineController, times(1)).start();
    }
}
