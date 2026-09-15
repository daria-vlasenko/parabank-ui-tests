package com.parabank.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement loginButton = $("input[value='Log In']");
    private final SelenideElement errorMessage = $("#rightPanel .error");

    @Step("Открыть страницу входа")
    public LoginPage openPage() {
        open("/index.htm");
        usernameInput.shouldBe(visible);
        return this;
    }

    @Step("Войти под пользователем {username}")
    public AccountsOverviewPage loginAs(String username, String password) {
        fillCredentials(username, password);
        loginButton.click();
        return new AccountsOverviewPage();
    }

    @Step("Попытаться войти под пользователем {username}")
    public LoginPage loginExpectingError(String username, String password) {
        fillCredentials(username, password);
        loginButton.click();
        return this;
    }

    @Step("Проверить сообщение об ошибке: {expectedText}")
    public LoginPage shouldShowError(String expectedText) {
        errorMessage.shouldHave(text(expectedText));
        return this;
    }

    private void fillCredentials(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
    }
}
