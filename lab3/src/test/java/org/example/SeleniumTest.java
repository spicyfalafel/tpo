package org.example;

import org.example.model.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    static MainPage mainPage;
    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void testDriver() {
        Utils.getDrivers().forEach(driver ->
        {
            new Thread(() -> {
                mainPage = new MainPage(driver);
                mainPage.goToMainPage();
                assertEquals("Траст сайта (Сервис) - Seo анализ сайта и проверка качества ссылок онлайн", mainPage.getTitle());
            }
            ).start();
        });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}

