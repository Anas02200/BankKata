package org.banking.kata.domain.valueobjects;

import org.banking.kata.domain.exceptions.ValidationMessages;
import static org.banking.kata.domain.validators.Validator.guard;
public record AccountNumber(Text value) {

    public AccountNumber {
        // validation against empty acc numbers
        guard(value).againstNullOrWhitespace(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    public static AccountNumber of(String value) {
        return new AccountNumber(Text.of(value));
    }

    @Override
    public String toString() {
        return value.value();
    }
}