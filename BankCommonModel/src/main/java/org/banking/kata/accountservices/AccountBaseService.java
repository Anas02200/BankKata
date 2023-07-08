package org.banking.kata.accountservices;

public interface AccountBaseService<R, P> {

    P execute(R request);
}
