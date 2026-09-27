package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.AccountsOverviewPage;
import com.parabank.pages.LeftPanel;
import com.parabank.pages.RegistrationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("ParaBank")
@Feature("Аутентификация")
class LogoutTests extends BaseWebTest {

    @Test
    @Story("Выход из системы")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Выход из системы возвращает на форму входа")
    void logOutReturnsToLoginForm() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());

        new LeftPanel()
                .logOut()
                .shouldBeOpened();
    }

    @Test
    @Story("Выход из системы")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("После выхода обзор счетов по прямой ссылке не отдаёт данные")
    void closedPagesAreNotAccessibleAfterLogOut() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());

        new LeftPanel().logOut().shouldBeOpened();

        new AccountsOverviewPage()
                .openPageDirectly()
                .shouldNotShowAccounts();
    }
}