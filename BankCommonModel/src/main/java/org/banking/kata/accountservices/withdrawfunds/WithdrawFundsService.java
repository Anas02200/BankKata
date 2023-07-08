package org.banking.kata.accountservices.withdrawfunds;


import org.banking.kata.accountservices.AccountVoidService;
import org.banking.kata.domain.accounts.Account;
import org.banking.kata.domain.accounts.AccountRepository;
import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.banking.kata.domain.valueobjects.AccountNumber;
import org.banking.kata.domain.valueobjects.TransactionAmount;

public class WithdrawFundsService implements AccountVoidService<WithdrawFundsRequest> {

    private final AccountRepository repository;

    public WithdrawFundsService(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(WithdrawFundsRequest request) {
        var accountNumber = getAccountNumber(request);
        var amount = getTransactionAmount(request);

        var bankAccount = findBankAccount(accountNumber);
        bankAccount.withdraw(amount);
        repository.update(bankAccount);
    }

    private AccountNumber getAccountNumber(WithdrawFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(WithdrawFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }

    private Account findBankAccount(AccountNumber accountNumber) {


        return repository.find(accountNumber)
                         .orElseThrow(() -> new ValidationException(ValidationMessages.ACCOUNT_NUMBER_NOT_EXIST));

    }

}
