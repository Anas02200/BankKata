package org.banking.kata.accountservices.depositfunds;




public class DepositFundsRequest {
    private String accountNumber;
    private int amount;

    public DepositFundsRequest() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
