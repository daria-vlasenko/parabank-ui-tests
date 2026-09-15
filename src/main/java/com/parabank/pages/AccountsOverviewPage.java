package com.parabank.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AccountsOverviewPage {

    private final SelenideElement title = $("#rightPanel h1");

    public AccountsOverviewPage shouldBeOpened() {
        title.shouldHave(text("Accounts Overview"));
        return this;
    }
}