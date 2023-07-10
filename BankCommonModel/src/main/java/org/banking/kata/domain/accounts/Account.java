package org.banking.kata.domain.accounts;

import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.banking.kata.domain.values.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {


    private AccountType accountType;
    private AccountNumber accountNumber;
    private AccountHolderName accountHolderName;

    private Balance balance;
    private List<AccountEvent> statement;


    public Account() {
    }

    public Account(AccountType accountType, AccountNumber accountNumber, AccountHolderName accountHolderName,
                   Balance balance, List<AccountEvent> history) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.statement = history;
    }



    public Account(Account other) {
        this(other.accountType, other.accountNumber, other.accountHolderName, other.balance, other.statement);
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public AccountHolderName getAccountHolderName() {
        return accountHolderName;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolderName(AccountHolderName accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<AccountEvent> getStatement() {
        return statement;
    }

    public void setStatement(List<AccountEvent> statement) {
        this.statement = statement;
    }

    public synchronized void deposit(TransactionAmount amount) {
        var initialBalance = balance;
        balance = balance.add(amount);
        AccountEvent accountEvent = new AccountEvent(AccountEventType.DEPOSIT, LocalDateTime.now(), amount,
                initialBalance, balance);


        List<AccountEvent> newStatement = new ArrayList<>(statement);
        newStatement.add(accountEvent);
        statement = newStatement;
    }

    public synchronized void withdraw(TransactionAmount amount) {
        if (amount.greaterThan(balance)) {
            throw new ValidationException(ValidationMessages.INSUFFICIENT_FUNDS);
        }
        var initialBalance = balance;
        balance = balance.subtract(amount);
        AccountEvent accountEvent = new AccountEvent(AccountEventType.WITHDRAWAL, LocalDateTime.now(), amount,
                initialBalance, balance);
        List<AccountEvent> newStatement = new ArrayList<>(statement);
        newStatement.add(accountEvent);
        statement = newStatement;

    }


}
