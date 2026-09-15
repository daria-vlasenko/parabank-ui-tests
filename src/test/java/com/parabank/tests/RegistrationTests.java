package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.RegistrationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("ParaBank")
@Feature("Регистрация")
class RegistrationTests extends BaseWebTest {

    @Test
    @Story("Создание учётной записи")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Регистрация нового пользователя проходит успешно")
    void registersNewUser() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());
    }
}
