package com.project.atm.validator;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.springframework.stereotype.Component;

import com.project.atm.exception.InvalidAmountException;
import com.project.atm.exception.InvalidPinException;
import com.project.atm.exception.InvalidSelectionException;

@Component
public class InputValidator {

    private static final int MIN_PIN_LENGTH = 4;
    private static final int MAX_PIN_LENGTH = 4;

    public InputValidator() {
    }

    public void validatePin(String pin) {
        if (pin == null || pin.trim().length() < MIN_PIN_LENGTH || pin.trim().length() > MAX_PIN_LENGTH || !pin.matches("[0-9]+")) {
            throw new InvalidPinException(MessageFormat.format("{0} is not a valid pin", pin == null ? "null" : pin));
        }
    }

    public void validateSelection(int selectedValue, int lowerRange, int upperRange) {
        if (selectedValue < lowerRange || selectedValue > upperRange) {
            throw new InvalidSelectionException(MessageFormat.format("{0} is not a valid selection", selectedValue));
        }
    }

    public void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 1 || getNumDecimalPlaces(amount) > 2) {
            throw new InvalidAmountException(MessageFormat.format("{0} is not a valid amount", amount == null ? "null" : amount.toString()));
        }
    }

    private int getNumDecimalPlaces(BigDecimal amount) {
        return Math.max(0, amount.stripTrailingZeros().scale());
    }
}
