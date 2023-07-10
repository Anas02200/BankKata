package org.banking.kata.accountservices.openaccount;

import org.banking.kata.domain.accounts.AccountRepository;
import org.banking.kata.domain.accounts.AccountType;
import org.banking.kata.domain.exceptions.ValidationException;
import org.banking.kata.domain.exceptions.ValidationMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class OpenAccountServiceTest {


    OpenAccountService openAccountService ;
    private final String accountHolderFirstN = "Anas B";
    private final String accountHolderLastN = "B";

    private final AccountType accountType = AccountType.CHECKING;

    private int initialBalance = 500;


    @BeforeEach
    void setUp(@Mock AccountRepository repository) {



        openAccountService = new OpenAccountService(repository);
    }

    @Test
    void executeShouldReturnNewAccountNumber() {

        //given
        OpenAccountRequest openAccountRequest = new OpenAccountRequest(accountType, accountHolderFirstN,
                accountHolderLastN, initialBalance);

        //when

        OpenAccountResponse createdAccountResponse = openAccountService.execute(openAccountRequest);


        assertNotNull(createdAccountResponse);

        assertNotNull(createdAccountResponse.getAccountNumber());

    }



    @Test
    void executeShouldReturnThrowWhenInvalidFirstName() {



        //given invalid  name

        var accountHolderFirstName = "";

        OpenAccountRequest openAccountRequest = new OpenAccountRequest(accountType, accountHolderFirstName,
                accountHolderLastN, initialBalance);

        //when

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            openAccountService.execute(openAccountRequest);
        });
        //then
        assertEquals(ValidationMessages.FIRST_NAME_EMPTY, thrown.getMessage());

    }
    @Test
    void executeShouldReturnThrowWhenInvalidLastName() {



        //given invalid  name

        var accountHolderLast = "";

        OpenAccountRequest openAccountRequest = new OpenAccountRequest(accountType, accountHolderFirstN,
                accountHolderLast, initialBalance);

        //when

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            openAccountService.execute(openAccountRequest);
        });

        //then
        assertEquals(ValidationMessages.LAST_NAME_EMPTY, thrown.getMessage());

    }

    @Test
    void executeShouldReturnThrowWhenNegativeBalance() {



        //given invalid  name

        var initialBal = -1;

        OpenAccountRequest openAccountRequest = new OpenAccountRequest(accountType, accountHolderFirstN,
                accountHolderLastN, initialBal);

        //when

        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            openAccountService.execute(openAccountRequest);
        });
        //then
        assertEquals(ValidationMessages.BALANCE_NEGATIVE, thrown.getMessage());

    }
}