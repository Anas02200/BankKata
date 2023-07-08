package org.banking.kata.accountservices.viewaccount;


import org.banking.kata.domain.accounts.AccountEvent;

import java.util.List;

public class ViewAccountResponse {
    private String accountNumber;
    private String fullName;
    private int currentBalance;
    private List<AccountEvent> history;

    public ViewAccountResponse() {

    }

    public ViewAccountResponse(String accountNumber, String fullName, int currentBalance, List<AccountEvent> history) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.currentBalance = currentBalance;
        this.history = history;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<AccountEvent> getHistory() {
        return history;
    }

    public void setHistory(List<AccountEvent> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "ViewAccountResponse{" + "accountNumber='" + accountNumber + '\'' + ", fullName='" + fullName + '\'' + ", currentBalance=" + currentBalance + ", history=" + history.toString() + '}';
    }
}
