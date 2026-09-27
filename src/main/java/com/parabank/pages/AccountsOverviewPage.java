package com.parabank.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.math.BigDecimal;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AccountsOverviewPage {

    private final SelenideElement title = $("#rightPanel h1");
    private final ElementsCollection accountRows = $$("#accountTable tbody tr");

    @Step("Открыть обзор счетов")
    public AccountsOverviewPage openPage() {
        open("/overview.htm");
        return shouldBeOpened();
    }

    @Step("Перейти по прямому адресу обзора счетов")
    public AccountsOverviewPage openPageDirectly() {
        open("/overview.htm");
        return this;
    }

    @Step("Проверить, что открыт обзор счетов")
    public AccountsOverviewPage shouldBeOpened() {
        title.shouldHave(text("Accounts Overview"));
        accountRows.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверить, что счета не отображаются")
    public AccountsOverviewPage shouldNotShowAccounts() {
        accountRows.shouldHave(size(0));
        return this;
    }

    @Step("Получить номер счёта в строке {index}")
    public String accountNumber(int index) {
        return accountRows.get(index).$("td", 0).$("a").getText().trim();
    }

    @Step("Получить баланс счёта {accountNumber}")
    public BigDecimal balanceOf(String accountNumber) {
        SelenideElement row = accountRows.findBy(text(accountNumber));
        return parseMoney(row.$("td", 1).getText());
    }

    @Step("Количество счетов")
    public int accountCount() {
        return accountRows.size();
    }

    private BigDecimal parseMoney(String raw) {
        return new BigDecimal(raw.replace("$", "").replace(",", "").trim());
    }
}