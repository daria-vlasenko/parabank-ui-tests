package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.RegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegistrationTests extends BaseWebTest {

    @Test
    @DisplayName("Регистрация нового пользователя проходит успешно")
    void registersNewUser() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());
    }
}