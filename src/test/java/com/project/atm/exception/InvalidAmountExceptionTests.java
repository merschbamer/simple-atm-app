package com.project.atm.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidAmountExceptionTests {

    @Test
    public void testConstructor_setsMessage() {
        InvalidAmountException exception = new InvalidAmountException("Amount is invalid");
        assertEquals("Amount is invalid", exception.getMessage());
    }
}
