package org.banking.kata.accountservices.openaccount;


import org.banking.kata.accountservices.AccountBaseService;
import org.banking.kata.domain.accounts.*;
import org.banking.kata.domain.valueobjects.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpenAccountService implements AccountBaseService<OpenAccountRequest, OpenAccountResponse> {


    private final AccountRepository repository;

    public OpenAccountService(AccountRepository bankAccountRepository) {
        this.repository = bankAccountRepository;
    }

    public OpenAccountResponse execute(OpenAccountRequest request) {
        var accountHolderName = getAccountHolderName(request);
        var balance = getBalance(request);

        var accountType = request.getAccountType();

        var account = createBankAccount(accountType,accountHolderName, balance);
        repository.add(account);

        return getResponse(account);
    }

    private AccountHolderName getAccountHolderName(OpenAccountRequest request) {
        return AccountHolderName.of(request.getFirstName(), request.getLastName());
    }

    private Balance getBalance(OpenAccountRequest request) {
        return Balance.of(request.getBalance());
    }

    private Account createBankAccount(AccountType accountType, AccountHolderName accountHolderName, Balance balance) {
        var accountNumber = AccountNumber.of(UUID.randomUUID().toString());
        List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING,
                LocalDateTime.now(), TransactionAmount.of(balance.toInt()), Balance.of(Money.ZERO), balance));
        return new Account(accountType, accountNumber, accountHolderName, balance, initialAccountEvents);
    }

    private OpenAccountResponse getResponse(Account bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();

        return new OpenAccountResponse(accountNumber);
    }
}
