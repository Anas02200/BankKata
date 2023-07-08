package org.banking.kata.domain.validators;

import org.banking.kata.domain.valueobjects.Money;

public class MoneyValidator extends BaseValidator<Money> {
    public MoneyValidator(Money value) {
        super(value);
    }

    public void againstNegative(String message) {
        against(value::isNegative, message);
    }

    public void againstNonPositive(String message) {
        against(value::isNonPositive, message);
    }
}
