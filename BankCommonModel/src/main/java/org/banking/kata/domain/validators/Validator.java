package org.banking.kata.domain.validators;


import org.banking.kata.domain.valueobjects.Text;

import org.banking.kata.domain.valueobjects.Money;

public class Validator {

    public static TextValidator guard(Text value) {
        return new TextValidator(value);
    }

    public static MoneyValidator guard(Money value) {
        return new MoneyValidator(value);
    }
}