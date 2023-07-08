package org.banking.kata.accountservices.openaccount;


public class OpenAccountResponse {
    private String accountNumber;


    public OpenAccountResponse(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public OpenAccountResponse() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
