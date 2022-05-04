package com.project.atm.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidSelectionExceptionTests {

    @Test
    public void testConstructor_setsMessage() {
        InvalidSelectionException exception = new InvalidSelectionException("Selection is invalid");
        assertEquals("Selection is invalid", exception.getMessage());
    }
}
