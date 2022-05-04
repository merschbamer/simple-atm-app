package com.project.atm.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.atm.exception.InvalidAmountException;
import com.project.atm.exception.InvalidPinException;
import com.project.atm.exception.InvalidSelectionException;

public class InputValidatorTests {

    private InputValidator inputValidator;

    @BeforeEach
    public void init() {
        inputValidator = new InputValidator();
    }

    @Test
    public void testValidatePin_throwsWhenPinIsNull() {
        assertThrows(InvalidPinException.class, () -> {
            inputValidator.validatePin(null);
        });
    }

    @Test
    public void testValidatePin_throwsWhenPinLengthIsLessThan4() {
        assertThrows(InvalidPinException.class, () -> {
            inputValidator.validatePin("123");
        });
    }

    @Test
    public void testValidatePin_throwsWhenPinLengthIsMoreThan4() {
        assertThrows(InvalidPinException.class, () -> {
            inputValidator.validatePin("12345");
        });
    }

    @Test
    public void testValidatePin_throwsWhenPinIsNotAllNumeric() {
        assertThrows(InvalidPinException.class, () -> {
            inputValidator.validatePin("123a!");
        });
    }

    @Test
    public void testValidatePin_doesNotThrowWhenPinIsValid() {
        inputValidator.validatePin("1234");
    }

    @Test
    public void testValidateSelection_throwsWhenValueIsGreaterThanUpperRange() {
        assertThrows(InvalidSelectionException.class, () -> {
            inputValidator.validateSelection(5, 1, 4);
        });
    }

    @Test
    public void testValidateSelection_trowsWhenValueIsLessThanLowerRange() {
        assertThrows(InvalidSelectionException.class, () -> {
            inputValidator.validateSelection(1, 2, 4);
        });
    }

    public void testValidateSelection_doesNotThrowWhenValueIsWithinRange() {
        int lowerRange = 1;
        int upperRange = 5;

        for (int i = lowerRange; i <= upperRange; i++) {
            inputValidator.validateSelection(i, lowerRange, upperRange);
        }
    }

    @Test
    public void testValidateAmount_throwsWhenAmountIsNull() {
        assertThrows(InvalidAmountException.class, () -> {
            inputValidator.validateAmount(null);
        });
    }

    @Test
    public void testValidateAmount_throwsWhenAmountIsNegative() {
        assertThrows(InvalidAmountException.class, () -> {
            inputValidator.validateAmount(new BigDecimal(-100));
        });
    }

    @Test
    public void testValidateAmount_throwsWhenAmountIsZero() {
        assertThrows(InvalidAmountException.class, () -> {
            inputValidator.validateAmount(new BigDecimal(0));
        });
    }

    @Test
    public void testValidateAmount_throwsWhenAmountContainsMoreThanTwoDecimalPlaces() {
        assertThrows(InvalidAmountException.class, () -> {
            inputValidator.validateAmount(new BigDecimal(100.123));
        });
    }

    @Test
    public void testValidateAmount_doesNotThrowWhenValidAmount() {
        inputValidator.validateAmount(new BigDecimal(100.50));
    }
}
