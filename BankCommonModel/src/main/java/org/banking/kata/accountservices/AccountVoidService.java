package org.banking.kata.accountservices;

public interface AccountVoidService<R> {

    void execute(R request);
}
