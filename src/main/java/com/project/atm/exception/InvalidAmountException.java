package com.project.atm.exception;

public class InvalidAmountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidAmountException(String msg) {
        super(msg);
    }
}
