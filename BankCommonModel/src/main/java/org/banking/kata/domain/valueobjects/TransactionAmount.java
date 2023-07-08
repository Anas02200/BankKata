package org.banking.kata.domain.valueobjects;


import org.banking.kata.domain.exceptions.ValidationMessages;
import static org.banking.kata.domain.validators.Validator.guard;


public record TransactionAmount(Money value) {

    public TransactionAmount {
        guard(value).againstNonPositive(ValidationMessages.AMOUNT_NOT_POSITIVE);
    }

    public boolean greaterThan(Balance balance) {
        return value.greaterThan(balance.value());
    }

    public static TransactionAmount of(Money value) {
        return new TransactionAmount(value);
    }

    public static TransactionAmount of(int value) {
        return of(Money.of(value));
    }

    @Override
    public String toString() {
        return "TransactionAmount{" + "value=" + value + '}';
    }
}
