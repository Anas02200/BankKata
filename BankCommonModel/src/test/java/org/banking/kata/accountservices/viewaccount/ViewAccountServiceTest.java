package org.banking.kata.accountservices.viewaccount;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ViewAccountServiceTest {


    private ViewAccountService viewAccountService;
    private final String accNumber = UUID.randomUUID().toString();
    private final Money initalDeposit = Money.ZERO;

    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";

    @BeforeEach
    void setUp(@Mock AccountRepository repository) {


        List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING,
                LocalDateTime.now(), TransactionAmount.of(initalDeposit), Balance.of(initalDeposit),
                Balance.of(initalDeposit)));


        Account account = new Account(AccountType.CHECKING, AccountNumber.of(accNumber),
                AccountHolderName.of(accountHolderFirstN, accountHolderLastN), Balance.of(initalDeposit),
                initialAccountEvents);

        account.deposit(TransactionAmount.of(10));

        account.deposit(TransactionAmount.of(50));

        account.withdraw(TransactionAmount.of(60));


        account.deposit(TransactionAmount.of(500));
        Mockito.when(repository.find(AccountNumber.of(accNumber))).thenReturn(Optional.of(account));

        this.viewAccountService = new ViewAccountService(repository);


    }

    @Test
    void shouldPrintStatement() {

        //given view account request
        ViewAccountRequest viewAccountRequest = new ViewAccountRequest(accNumber);
        //when
        ViewAccountResponse statement = viewAccountService.execute(viewAccountRequest);

        //then , no assertions here , just to check printing format


        String printedStatement = statement.print();

        System.out.println(printedStatement);

        // checking if the printed statement contains the right informations
        assertTrue(printedStatement.contains(accNumber));
        assertTrue(printedStatement.contains(statement.getFullName()));
        assertTrue(printedStatement.contains(Integer.toString(statement.getCurrentBalance())));


        //assertion to check the response for the right acc number
        assertEquals(accNumber, statement.getAccountNumber());
    }
}