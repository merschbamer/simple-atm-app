package com.project.atm.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidPinExceptionTests {

    @Test
    public void testConstructor_setsMessage() {
        InvalidPinException exception = new InvalidPinException("Pin is invalid");
        assertEquals("Pin is invalid", exception.getMessage());
    }
}
