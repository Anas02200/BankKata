package org.banking.kata.accountservices.withdrawfunds;

import org.banking.kata.domain.accounts.*;
import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WithdrawFundsServiceTest {

    private WithdrawFundsService withdrawFundsService;

    private Account account;
    private final String accNumber = UUID.randomUUID().toString();
    private final Money initalDeposit = Money.of(500);

    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";

    @BeforeEach
    void setUp(@Mock AccountRepository repository) {


        List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING,
                LocalDateTime.now(), TransactionAmount.of(initalDeposit), Balance.of(initalDeposit),
                Balance.of(initalDeposit)));


        // initial balance is 500

        account = new Account(AccountType.CHECKING, AccountNumber.of(accNumber),
                AccountHolderName.of(accountHolderFirstN, accountHolderLastN), Balance.of(initalDeposit),
                initialAccountEvents);


        Mockito.when(repository.find(AccountNumber.of(accNumber))).thenReturn(Optional.of(account));

        this.withdrawFundsService = new WithdrawFundsService(repository);


    }


    @Test
    void executeShouldWithdrawFunds() {

        //given amount to deposit
        int withdraw = 300;

        //when deposit this amount

        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest(accNumber, withdraw);

        withdrawFundsService.execute(withdrawFundsRequest);


        //then
        assertEquals(Balance.of(initalDeposit.value() - withdraw), account.getBalance());

    }

    @Test
    void executeShouldThrowWhenWithdawGreaterThanBalance() {

        //given amount to deposit
        int withdraw = 501;

        //when deposit this amount

        WithdrawFundsRequest withdrawFundsRequest = new WithdrawFundsRequest(accNumber, withdraw);


        ValidationException thrown = assertThrows(ValidationException.class, () -> withdrawFundsService.execute(withdrawFundsRequest));
        assertEquals(ValidationMessages.INSUFFICIENT_FUNDS, thrown.getMessage());

    }


}