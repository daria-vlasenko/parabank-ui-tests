package com.parabank.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LeftPanel {

    private final SelenideElement logoutLink = $("#leftPanel a[href*='logout.htm']");

    public LoginPage logOut() {
        logoutLink.click();
        return new LoginPage();
    }
}