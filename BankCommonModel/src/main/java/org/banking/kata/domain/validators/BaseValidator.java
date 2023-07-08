package org.banking.kata.domain.validators;



import org.banking.kata.domain.exceptions.ValidationException;

import java.util.function.BooleanSupplier;

public class BaseValidator<T> {

    protected final T value;

    public BaseValidator(T value) {
        this.value = value;
    }


    //we test against a boolean , if statement is true we throw an exception
    protected void against(BooleanSupplier tester, String message) {
        if (tester.getAsBoolean()) {
            throw new ValidationException(message);
        }
    }
}
