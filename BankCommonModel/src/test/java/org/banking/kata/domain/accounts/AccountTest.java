package org.banking.kata.domain.accounts;

import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.banking.kata.domain.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {


    private Account account;
    private final String accNumber = "00125895";
    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";

    private final Money initalDeposit = Money.ZERO;


    List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING, LocalDateTime.now(),
            TransactionAmount.of(initalDeposit), Balance.of(initalDeposit),
            Balance.of(initalDeposit)));

    @BeforeEach
    void setUp() {

        this.account = new Account(AccountType.CHECKING, AccountNumber.of(accNumber),
                AccountHolderName.of(accountHolderFirstN, accountHolderLastN), Balance.of(initalDeposit),
                initialAccountEvents);


    }

    @Test
    void deposit() {

        //given
        int deposit = 10;

        int initialDeposit = account.getBalance().toInt();
        //when
        account.deposit(TransactionAmount.of(deposit));
        //then expected,actual
        assertEquals(Balance.of(deposit + initialDeposit), account.getBalance());


    }


    @Test
    void withdraw() {

        account.deposit(TransactionAmount.of(50));
        int withdraw = 10;

        int balance = account.getBalance().toInt();
        //when
        account.withdraw(TransactionAmount.of(withdraw));

        assertEquals(Balance.of(balance - withdraw), account.getBalance());
    }

    @Test
    void withdrawMmoreThanAvailable() {
        //given account with initalDeposit = 100

        //when widthraw 200
        int withdraw = 200;

        int initialDeposit = account.getBalance().toInt();
        //when

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            account.withdraw(TransactionAmount.of(withdraw));
        });
        assertEquals(ValidationMessages.INSUFFICIENT_FUNDS, thrown.getMessage());
    }


}