package com.parabank.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.parabank.config.WebConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseWebTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = WebConfig.BROWSER;
        Configuration.baseUrl = WebConfig.BASE_URL;
        Configuration.timeout = WebConfig.TIMEOUT_MS;
        Configuration.browserSize = WebConfig.BROWSER_SIZE;

        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}