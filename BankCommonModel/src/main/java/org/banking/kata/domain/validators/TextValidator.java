package org.banking.kata.domain.validators;

import org.banking.kata.domain.values.Text;

;

public class TextValidator extends BaseValidator<Text> {

    public TextValidator(Text value) {
        super(value);
    }



    public void againstNullOrWhitespace(String message) {
        against(value::isNullOrWhitespace, message);
    }
}
