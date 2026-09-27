package com.parabank.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.math.BigDecimal;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TransferFundsPage {

    private final SelenideElement amountInput = $("#amount");
    private final SelenideElement fromAccountSelect = $("#fromAccountId");
    private final SelenideElement toAccountSelect = $("#toAccountId");
    private final SelenideElement transferButton = $("#rightPanel input[type='submit']");

    private final SelenideElement resultTitle = $("#showResult h1");
    private final SelenideElement resultAmount = $("#amountResult");
    private final SelenideElement resultFrom = $("#fromAccountIdResult");
    private final SelenideElement resultTo = $("#toAccountIdResult");

    private final SelenideElement errorTitle = $("#showError h1");

    @Step("Открыть страницу перевода")
    public TransferFundsPage openPage() {
        open("/transfer.htm");
        amountInput.shouldBe(visible);
        fromAccountSelect.$$("option").shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Перевести {amount} со счёта {fromAccount} на счёт {toAccount}")
    public TransferFundsPage transfer(BigDecimal amount, String fromAccount, String toAccount) {
        amountInput.setValue(amount.toPlainString());
        fromAccountSelect.selectOptionContainingText(fromAccount);
        toAccountSelect.selectOptionContainingText(toAccount);
        transferButton.click();
        return this;
    }

    @Step("Проверить, что перевод выполнен")
    public TransferFundsPage shouldBeCompleted(BigDecimal amount, String fromAccount, String toAccount) {
        resultTitle.shouldHave(text("Transfer Complete!"));
        resultAmount.shouldHave(text(amount.toPlainString()));
        resultFrom.shouldHave(text(fromAccount));
        resultTo.shouldHave(text(toAccount));
        return this;
    }

    @Step("Проверить, что перевод отклонён с ошибкой")
    public TransferFundsPage shouldBeRejected() {
        errorTitle.shouldHave(text("Error!"));
        return this;
    }
}