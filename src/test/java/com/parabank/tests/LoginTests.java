package com.parabank.tests;

import com.parabank.pages.LoginPage;
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
}