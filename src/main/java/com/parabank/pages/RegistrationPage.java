package com.parabank.pages;

import com.codeborne.selenide.SelenideElement;
import com.parabank.data.NewUser;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("input[id='customer.firstName']");
    private final SelenideElement lastNameInput = $("input[id='customer.lastName']");
    private final SelenideElement streetInput = $("input[id='customer.address.street']");
    private final SelenideElement cityInput = $("input[id='customer.address.city']");
    private final SelenideElement stateInput = $("input[id='customer.address.state']");
    private final SelenideElement zipCodeInput = $("input[id='customer.address.zipCode']");
    private final SelenideElement phoneInput = $("input[id='customer.phoneNumber']");
    private final SelenideElement ssnInput = $("input[id='customer.ssn']");
    private final SelenideElement usernameInput = $("input[id='customer.username']");
    private final SelenideElement passwordInput = $("input[id='customer.password']");
    private final SelenideElement repeatedPasswordInput = $("#repeatedPassword");
    private final SelenideElement registerButton = $("#customerForm input[value='Register']");

    private final SelenideElement title = $("#rightPanel h1");
    private final SelenideElement message = $("#rightPanel p");

    @Step("Открыть страницу регистрации")
    public RegistrationPage openPage() {
        open("/register.htm");
        firstNameInput.shouldBe(visible);
        return this;
    }

    @Step("Зарегистрировать пользователя {user.username}")
    public RegistrationPage register(NewUser user) {
        firstNameInput.setValue(user.firstName());
        lastNameInput.setValue(user.lastName());
        streetInput.setValue(user.street());
        cityInput.setValue(user.city());
        stateInput.setValue(user.state());
        zipCodeInput.setValue(user.zipCode());
        phoneInput.setValue(user.phone());
        ssnInput.setValue(user.ssn());
        usernameInput.setValue(user.username());
        passwordInput.setValue(user.password());
        repeatedPasswordInput.setValue(user.password());
        registerButton.click();
        return this;
    }

    @Step("Проверить, что пользователь {username} зарегистрирован")
    public RegistrationPage shouldBeRegisteredAs(String username) {
        title.shouldHave(text("Welcome " + username));
        message.shouldHave(text("Your account was created successfully"));
        return this;
    }
}
