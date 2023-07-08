package org.banking.kata.accountservices.viewaccount;




public class ViewAccountRequest {
    private String accountNumber;

    public ViewAccountRequest() {
    }

    public ViewAccountRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
