package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.LeftPanel;
import com.parabank.pages.LoginPage;
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
class LoginTests extends BaseWebTest {

    @Test
    @Story("Вход в систему")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Вход с пустыми полями показывает сообщение об ошибке")
    void showsErrorWhenCredentialsAreEmpty() {
        new LoginPage()
                .openPage()
                .loginExpectingError("", "")
                .shouldShowError("Please enter a username and password.");
    }

    @Test
    @Story("Вход в систему")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Зарегистрированный пользователь может войти в систему")
    void registeredUserCanLogIn() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());

        new LeftPanel()
                .logOut()
                .loginAs(user.username(), user.password())
                .shouldBeOpened();
    }
}
