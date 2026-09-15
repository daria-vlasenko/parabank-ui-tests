package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.LeftPanel;
import com.parabank.pages.LoginPage;
import com.parabank.pages.RegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginTests extends BaseWebTest {

    @Test
    @DisplayName("Вход с пустыми полями показывает сообщение об ошибке")
    void showsErrorWhenCredentialsAreEmpty() {
        new LoginPage()
                .openPage()
                .loginExpectingError("", "")
                .shouldShowError("Please enter a username and password.");
    }
    @Test
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
