package org.banking.kata.accountservices.depositfunds;

import org.banking.kata.accountservices.viewaccount.ViewAccountService;
import org.banking.kata.domain.accounts.*;
import org.banking.kata.domain.values.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepositFundsServiceTest {

    private DepositFundsService depositFundsService;

    private Account account;
    private final String accNumber = UUID.randomUUID().toString();
    private final Money initalDeposit = Money.ZERO;

    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";

    @BeforeEach
    void setUp(@Mock AccountRepository repository) {


        List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING,
                LocalDateTime.now(), TransactionAmount.of(initalDeposit), Balance.of(initalDeposit),
                Balance.of(initalDeposit)));


        account = new Account(AccountType.CHECKING, AccountNumber.of(accNumber),
                AccountHolderName.of(accountHolderFirstN, accountHolderLastN), Balance.of(initalDeposit),
                initialAccountEvents);


        Mockito.when(repository.find(AccountNumber.of(accNumber))).thenReturn(Optional.of(account));

        this.depositFundsService = new DepositFundsService(repository);


    }


    @Test
    void executeShouldDepositFunds() {

        //given amount to deposit
        int deposit = 5000;

        //when deposit this amount

        DepositFundsRequest depositFundsRequest = new DepositFundsRequest(accNumber, deposit);

        depositFundsService.execute(depositFundsRequest);


        //then
        assertEquals(Balance.of(deposit+initalDeposit.value()),account.getBalance());

    }
}