package org.banking.kata.accountservices.viewaccount;


import org.banking.kata.accountservices.AccountBaseService;
import org.banking.kata.domain.accounts.Account;
import org.banking.kata.domain.accounts.AccountRepository;
import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.banking.kata.domain.values.AccountNumber;

public class ViewAccountService implements AccountBaseService<ViewAccountRequest, ViewAccountResponse> {

    private final AccountRepository repository;


    public ViewAccountService(AccountRepository repository) {
        this.repository = repository;

    }

    @Override
    public ViewAccountResponse execute(ViewAccountRequest request) {
        var accountNumber = getAccountNumber(request);
        var bankAccount = findBankAccount(accountNumber);
        return getResponse(bankAccount);
    }

    private AccountNumber getAccountNumber(ViewAccountRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private Account findBankAccount(AccountNumber accountNumber) {
        return repository.find(accountNumber)
                         .orElseThrow(() -> new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST));
    }

    private ViewAccountResponse getResponse(Account bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();
        var fullName = bankAccount.getAccountHolderName().toString();
        var balance = bankAccount.getBalance().toInt();
        var statement = bankAccount.getStatement();
        //better if we used a builder
        return new ViewAccountResponse(accountNumber,fullName,balance,statement);
    }

}
