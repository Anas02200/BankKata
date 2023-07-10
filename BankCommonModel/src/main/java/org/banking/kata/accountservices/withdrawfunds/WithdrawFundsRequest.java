package org.banking.kata.accountservices.withdrawfunds;


public class WithdrawFundsRequest {
    private String accountNumber;
    private int amount;

    public WithdrawFundsRequest() {
    }


    public WithdrawFundsRequest(String accountNumber, int amount) {
        this.accountNumber = accountNumber; this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
