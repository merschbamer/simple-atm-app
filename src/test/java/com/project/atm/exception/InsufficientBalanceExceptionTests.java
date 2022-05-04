package com.project.atm.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InsufficientBalanceExceptionTests {

    @Test
    public void testConstructor_setsMessage() {
        InvalidSelectionException exception = new InvalidSelectionException("Balance is insufficient");
        assertEquals("Balance is insufficient", exception.getMessage());
    }

}
