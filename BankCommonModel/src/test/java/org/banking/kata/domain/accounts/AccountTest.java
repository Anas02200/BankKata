package org.banking.kata.domain.accounts;

import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.banking.kata.domain.valueobjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {


    private Account account;
    private final String accNumber = "00125895";
    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";

    private final int initalDeposit = 100;



    List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING, LocalDateTime.now(),
            TransactionAmount.of(Money.of(initalDeposit)), Balance.of(Money.of(initalDeposit)),
            Balance.of(Money.of(initalDeposit))));

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
        assertEquals(Balance.of(deposit+initialDeposit),account.getBalance());


    }




    @Test
    void withdraw_should_pass() {

        int withdraw = 10;

        int initialDeposit = account.getBalance().toInt();
        //when
        account.withdraw(TransactionAmount.of(withdraw));

        assertEquals(Balance.of(initialDeposit-withdraw),account.getBalance());
    }
    @Test
    void withdraw_more_than_available() {
        //given account with initalDeposit = 100

        //when widthraw 200
        int withdraw = 200;

        int initialDeposit = account.getBalance().toInt();
        //when

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            account.withdraw(TransactionAmount.of(withdraw));
        });
        assertEquals(ValidationMessages.INSUFFICIENT_FUNDS,thrown.getMessage());
    }


    @Test
    void concurrentOperations() {

        //given
        int randomAmount = new Random().nextInt(500,10000);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        List<CompletableFuture<Void>> futures2 = new ArrayList<>();
        int initialDeposit = account.getBalance().toInt();


        //when

        //concurrent deposits
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> voidCompletableFuture =
                    CompletableFuture.runAsync(() -> account.deposit(TransactionAmount.of(randomAmount)));
            futures.add(voidCompletableFuture);

        }
        futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        //concurrent withdrawals
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> voidCompletableFuture1 =
                    CompletableFuture.runAsync(() -> account.withdraw(TransactionAmount.of(randomAmount)));
            futures2.add(voidCompletableFuture1);
        }
        futures2.stream().map(CompletableFuture::join).collect(Collectors.toList());



        //then actual balance equals initial balance
        assertEquals(Balance.of(initialDeposit),account.getBalance());
    }
}