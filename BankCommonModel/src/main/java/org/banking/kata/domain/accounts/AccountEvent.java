package org.banking.kata.domain.accounts;

import org.banking.kata.domain.values.Balance;
import org.banking.kata.domain.values.TransactionAmount;

import java.time.LocalDateTime;

public class AccountEvent {

    private AccountEventType event;
    private LocalDateTime time;

    private TransactionAmount amount;

    private Balance before;

    private Balance after;

    public AccountEvent() {
    }

    public AccountEvent(AccountEventType event, LocalDateTime time, TransactionAmount amount, Balance before,
                        Balance after) {
        this.event = event;
        this.time = time;
        this.amount = amount;
        this.before = before;
        this.after = after;
    }

    public AccountEventType getEvent() {
        return event;
    }

    public void setEvent(AccountEventType event) {
        this.event = event;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public TransactionAmount getAmount() {
        return amount;
    }

    public void setAmount(TransactionAmount amount) {
        this.amount = amount;
    }

    public Balance getBefore() {
        return before;
    }

    public void setBefore(Balance before) {
        this.before = before;
    }

    public Balance getAfter() {
        return after;
    }

    public void setAfter(Balance after) {
        this.after = after;
    }

    @Override
    public String toString() {
        return event + " at : " + time + ", transaction amount : " + amount + ", balance before :" + before +
                ", balance after=" + after ;
    }
}
