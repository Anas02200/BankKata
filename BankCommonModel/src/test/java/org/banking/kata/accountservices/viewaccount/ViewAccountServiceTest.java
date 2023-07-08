package org.banking.kata.accountservices.viewaccount;

import org.banking.kata.domain.accounts.*;
import org.banking.kata.domain.valueobjects.*;
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
class ViewAccountServiceTest {



    private ViewAccountService viewAccountService;
    private final String accNumber = UUID.randomUUID().toString();
    private final int initalDeposit = 1 ;

    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";
    @BeforeEach
    void setUp( @Mock AccountRepository repository) {


        List<AccountEvent> initialAccountEvents = List.of(new AccountEvent(AccountEventType.OPENING,
                LocalDateTime.now(), TransactionAmount.of(Money.of(initalDeposit)), Balance.of(Money.of(initalDeposit)),
                Balance.of(Money.of(initalDeposit))));


        Account account = new Account(AccountType.CHECKING, AccountNumber.of(accNumber),
                AccountHolderName.of(accountHolderFirstN, accountHolderLastN), Balance.of(initalDeposit),initialAccountEvents );

        account.deposit(TransactionAmount.of(10));

        account.deposit(TransactionAmount.of(50));

        account.withdraw(TransactionAmount.of(60));


        account.deposit(TransactionAmount.of(500));
        Mockito.when(repository.find(AccountNumber.of(accNumber))).thenReturn(Optional.of(account));

        this.viewAccountService= new ViewAccountService(repository);


    }

    @Test
    void execute_should_print_statement() {

        //given view account request
        ViewAccountRequest viewAccountRequest = new ViewAccountRequest(accNumber);
        //when
        ViewAccountResponse  printedStatement = viewAccountService.execute(viewAccountRequest);

        //then , no assertions here , just to check printing format
        System.out.println(printedStatement);

        assertEquals(accNumber,printedStatement.getAccountNumber());
    }
}