package com.parabank.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OpenAccountPage {

    private final SelenideElement accountTypeSelect = $("#type");
    private final SelenideElement fromAccountSelect = $("#fromAccountId");
    private final SelenideElement openAccountButton = $("input[value='Open New Account']");

    private final SelenideElement resultTitle = $("#openAccountResult h1");
    private final SelenideElement newAccountId = $("#newAccountId");

    @Step("Открыть страницу создания счёта")
    public OpenAccountPage openPage() {
        open("/openaccount.htm");
        accountTypeSelect.shouldBe(visible);
        fromAccountSelect.$$("option").shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Открыть счёт типа {accountType}")
    public OpenAccountPage openAccount(String accountType) {
        accountTypeSelect.selectOption(accountType);
        openAccountButton.click();
        return this;
    }

    @Step("Проверить, что счёт открыт")
    public OpenAccountPage shouldBeOpened() {
        resultTitle.shouldHave(text("Account Opened!"));
        newAccountId.shouldBe(visible);
        return this;
    }

    @Step("Получить номер нового счёта")
    public String newAccountNumber() {
        return newAccountId.shouldBe(visible).getText();
    }
}
