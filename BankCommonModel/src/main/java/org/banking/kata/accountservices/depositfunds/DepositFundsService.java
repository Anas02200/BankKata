package org.banking.kata.accountservices.depositfunds;


import org.banking.kata.accountservices.AccountVoidService;
import org.banking.kata.domain.accounts.Account;
import org.banking.kata.domain.accounts.AccountRepository;
import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.banking.kata.domain.valueobjects.AccountNumber;
import org.banking.kata.domain.valueobjects.TransactionAmount;

public class DepositFundsService implements AccountVoidService<DepositFundsRequest> {

    private final AccountRepository repository;

    public DepositFundsService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DepositFundsRequest request) {
        var accountNumber = getAccountNumber(request);
        var amount = getTransactionAmount(request);

        var account = findBankAccount(accountNumber);
        account.deposit(amount);
        repository.update(account);
    }

    private AccountNumber getAccountNumber(DepositFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(DepositFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }

    private Account findBankAccount(AccountNumber accountNumber) {
        return repository.find(accountNumber)
                         .orElseThrow(() -> new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST));
    }
}
