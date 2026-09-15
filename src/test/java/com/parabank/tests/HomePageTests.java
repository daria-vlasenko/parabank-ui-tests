package com.parabank.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

class HomePageTests extends BaseWebTest {

    @Test
    @DisplayName("Главная страница открывается и показывает форму входа")
    void homePageShowsLoginForm() {
        Selenide.open("/index.htm");

        $("input[name='username']").shouldBe(visible);
        $("input[name='password']").shouldBe(visible);
    }
}