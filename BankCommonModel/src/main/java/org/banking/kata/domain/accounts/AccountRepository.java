package org.banking.kata.domain.accounts;

import org.banking.kata.domain.values.AccountNumber;

import java.util.Optional;

public interface AccountRepository {


    Optional<Account> find(AccountNumber accountNumber);

    void add(Account bankAccount);

    void update(Account bankAccount);
}
