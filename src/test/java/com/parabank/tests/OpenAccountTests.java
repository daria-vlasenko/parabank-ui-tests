package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.OpenAccountPage;
import com.parabank.pages.RegistrationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("ParaBank")
@Feature("Счета")
class OpenAccountTests extends BaseWebTest {

    @Test
    @Story("Открытие счёта")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Зарегистрированный пользователь открывает новый сберегательный счёт")
    void opensNewSavingsAccount() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());

        String accountNumber = new OpenAccountPage()
                .openPage()
                .openAccount("SAVINGS")
                .shouldBeOpened()
                .newAccountNumber();

        assertFalse(accountNumber.isBlank(), "Номер нового счёта не отображается");
    }
}
