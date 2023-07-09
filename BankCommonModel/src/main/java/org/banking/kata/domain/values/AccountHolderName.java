package org.banking.kata.domain.values;

import org.banking.kata.domain.exceptions.ValidationMessages;
import static org.banking.kata.domain.validators.Validator.guard;
public record AccountHolderName(Text firstName, Text lastName) {

    public AccountHolderName {
        // validations for holder name
        guard(firstName).againstNullOrWhitespace(ValidationMessages.FIRST_NAME_EMPTY);
        guard(lastName).againstNullOrWhitespace(ValidationMessages.LAST_NAME_EMPTY);
    }

    public static AccountHolderName of(String firstName, String lastName) {
        return new AccountHolderName(Text.of(firstName), Text.of(lastName));
    }

    public Text getFullName() {
        return firstName().addSpace().add(lastName());
    }

    @Override
    public String toString() {
        return getFullName().value();
    }
}
