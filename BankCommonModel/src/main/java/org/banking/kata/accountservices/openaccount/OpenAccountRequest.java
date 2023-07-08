package org.banking.kata.accountservices.openaccount;


import org.banking.kata.domain.accounts.AccountType;

public class OpenAccountRequest {

    private AccountType accountType;
    private String firstName;
    private String lastName;
    private int balance;




    public OpenAccountRequest() {
    }

    public OpenAccountRequest(AccountType accountType, String firstName, String lastName, int balance) {
        this.accountType = accountType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
