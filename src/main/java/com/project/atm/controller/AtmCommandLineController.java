package com.project.atm.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.atm.exception.InsufficientBalanceException;
import com.project.atm.service.IAccountService;
import com.project.atm.validator.InputValidator;

@Component
public class AtmCommandLineController {

    private static final Logger logger = LoggerFactory.getLogger(AtmCommandLineController.class);

    private IAccountService accountService;

    private InputValidator validator;

    private Scanner scanner;

    public AtmCommandLineController(@Autowired final Scanner scanner, @Autowired final IAccountService accountService, @Autowired final InputValidator validator) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.validator = validator;
    }

    public void start() {
        Integer menuSelection = null;
        while (menuSelection == null) {
            displayMessage("Hi, welcome to the ATM what would you like to do? \n1. Login \n2. Exit");
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                validator.validateSelection(userInput, 1, 2);
                menuSelection = userInput;
            } catch (Exception e) {
                displayMessage("Please enter a valid selection.");
            }
        }

        switch (menuSelection) {
        case 1:
            handleLogin();
            break;
        case 2:
            handleExit();
            break;
        };
    };

    public void handleLogin() {
        displayMessage("Please enter your 4 digit PIN.");

        String pin = scanner.nextLine();
        try {
            validator.validatePin(pin);
            displayMessage("Login Successful.");
            accountActionMenu();
        } catch (Exception e) {
            displayMessage("The PIN you entered is invalid.");
            start();
        }
    }

    public void accountActionMenu() {
        Integer menuSelection = null;
        while (menuSelection == null) {
            displayMessage("What would you like to do? \n1. View balance \n2. Deposit \n3. Withdraw \n4. Logout");
            try {
                int userInput = Integer.parseInt(scanner.nextLine());
                validator.validateSelection(userInput, 1, 4);
                menuSelection = userInput;
            } catch (Exception e) {
                displayMessage("Please enter a valid selection.");
            }
        }

        switch (menuSelection) {
        case 1:
            handleViewBalance();
            break;
        case 2:
            handleDeposit();
            break;
        case 3:
            handleWithdraw();
            break;
        case 4:
            handleLogout();
            break;
        };
    }

    public void handleViewBalance() {
        BigDecimal currentBalance = accountService.getBalance();

        displayMessage("Your current balance is: {0}", currentBalance.toString());

        accountActionMenu();
    }

    public void handleDeposit() {
        displayMessage("Please enter the amount you would like to deposit.");

        try {
            BigDecimal depositAmount = new BigDecimal(scanner.nextLine());
            accountService.deposit(depositAmount);
            displayMessage("Your money has been deposited successfully.");
        } catch (Exception e) {
            displayMessage("The amount you entered is not valid.");
        }

        accountActionMenu();
    }

    public void handleWithdraw() {
        displayMessage("Please enter the amount you would like to withdraw.");

        try {
            BigDecimal withdrawAmount = new BigDecimal(scanner.nextLine());
            accountService.withdraw(withdrawAmount);
            displayMessage("Your money has been withdrawn successfully.");
        } catch (InsufficientBalanceException e) {
            displayMessage("You do not have enough funds to withdraw the requested amount. Please check your balance and try again.");
        } catch (Exception e) {
            displayMessage("The amount you entered is not valid.");
        }

        accountActionMenu();
    }

    public void handleLogout() {
        start();
    }

    public void handleExit() {
        System.exit(0);
    }

    public void displayMessage(String message, String... args) {
        System.out.println("\n" + MessageFormat.format(message, args) + "\n");
    }
}
