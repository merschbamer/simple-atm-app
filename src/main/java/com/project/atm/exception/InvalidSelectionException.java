package com.project.atm.exception;

public class InvalidSelectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidSelectionException(String msg) {
        super(msg);
    }
}
