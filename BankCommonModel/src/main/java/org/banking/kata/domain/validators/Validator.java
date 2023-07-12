package org.banking.kata.domain.validators;


import org.banking.kata.domain.values.Money;
import org.banking.kata.domain.values.Text;

public class Validator {

    private Validator() {
    }

    public static TextValidator guard(Text value) {
        return new TextValidator(value);
    }

    public static MoneyValidator guard(Money value) {
        return new MoneyValidator(value);
    }
}