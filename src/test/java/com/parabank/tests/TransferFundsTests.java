package com.parabank.tests;

import com.parabank.data.NewUser;
import com.parabank.data.TestDataFactory;
import com.parabank.pages.AccountsOverviewPage;
import com.parabank.pages.OpenAccountPage;
import com.parabank.pages.RegistrationPage;
import com.parabank.pages.TransferFundsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("ParaBank")
@Feature("Переводы")
class TransferFundsTests extends BaseWebTest {

    private static final BigDecimal TRANSFER_AMOUNT = new BigDecimal("25.00");
    private static final BigDecimal OVER_BALANCE_EXTRA = new BigDecimal("1000.00");

    @Test
    @Story("Перевод между собственными счетами")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Перевод между своими счетами: списалось с одного, пришло на другой")
    void transfersBetweenOwnAccounts() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());

        String targetAccount = new OpenAccountPage()
                .openPage()
                .openAccount("SAVINGS")
                .shouldBeOpened()
                .newAccountNumber();

        AccountsOverviewPage overview = new AccountsOverviewPage().openPage();
        String sourceAccount = firstAccountOtherThan(overview, targetAccount);

        BigDecimal sourceBefore = overview.balanceOf(sourceAccount);
        BigDecimal targetBefore = overview.balanceOf(targetAccount);

        new TransferFundsPage()
                .openPage()
                .transfer(TRANSFER_AMOUNT, sourceAccount, targetAccount)
                .shouldBeCompleted(TRANSFER_AMOUNT, sourceAccount, targetAccount);

        overview.openPage();
        BigDecimal sourceAfter = overview.balanceOf(sourceAccount);
        BigDecimal targetAfter = overview.balanceOf(targetAccount);

        assertEquals(0, sourceBefore.subtract(TRANSFER_AMOUNT).compareTo(sourceAfter),
                "Со счёта-источника списалась не та сумма");
        assertEquals(0, targetBefore.add(TRANSFER_AMOUNT).compareTo(targetAfter),
                "На счёт-получатель пришла не та сумма");
    }

    @Test
    @Story("Перевод суммы больше доступного остатка")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("DEF-002")
    @DisplayName("ДЕФЕКТ DEF-002: перевод сверх остатка выполняется, баланс уходит в минус")
    void documentsOverdraftDefect() {
        NewUser user = TestDataFactory.uniqueUser();

        new RegistrationPage()
                .openPage()
                .register(user)
                .shouldBeRegisteredAs(user.username());

        String targetAccount = new OpenAccountPage()
                .openPage()
                .openAccount("SAVINGS")
                .shouldBeOpened()
                .newAccountNumber();

        AccountsOverviewPage overview = new AccountsOverviewPage().openPage();
        String sourceAccount = firstAccountOtherThan(overview, targetAccount);

        BigDecimal available = overview.balanceOf(sourceAccount);
        BigDecimal tooMuch = available.add(OVER_BALANCE_EXTRA);

        new TransferFundsPage()
                .openPage()
                .transfer(tooMuch, sourceAccount, targetAccount)
                .shouldBeCompleted(tooMuch, sourceAccount, targetAccount);

        overview.openPage();
        BigDecimal sourceAfter = overview.balanceOf(sourceAccount);

        assertTrue(sourceAfter.signum() < 0,
                "Ожидалось, что баланс уйдёт в минус, фактически: " + sourceAfter);
        assertEquals(0, available.subtract(tooMuch).compareTo(sourceAfter),
                "Списана не та сумма при переводе сверх остатка");
    }

    private String firstAccountOtherThan(AccountsOverviewPage overview, String excluded) {
        for (int i = 0; i < overview.accountCount(); i++) {
            String number = overview.accountNumber(i);
            if (!number.equals(excluded)) {
                return number;
            }
        }
        throw new IllegalStateException("Не найден второй счёт, отличный от " + excluded);
    }
}