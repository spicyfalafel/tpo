package org.example.usecase;

import org.example.Utils;
import org.example.model.MainPage;
import org.example.model.OuterLinksPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinksOuterTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }
    static final String TEST_URL = "https://se.ifmo.ru/";

    @Test
    void testOuterLinks() {
        Utils.getDrivers().forEach(driver ->
        {
            MainPage mainPage = new MainPage(driver);
            mainPage.goToMainPage();
            mainPage.login();
            OuterLinksPage linksPage =mainPage.clickOuterLinks();
            linksPage.checkUrl(TEST_URL);
            assertEquals("https://xtool.ru/backlinks/?site=se.ifmo.ru", driver.getCurrentUrl());
            assertEquals("se.ifmo.ru", linksPage.getSiteText());
            assertTrue(Integer.parseInt(linksPage.getNumberOfLinks()) >= 0);


            linksPage.checkUrl("se.ifmonsteirnstiesr.ru");
            assertEquals("Обратных ссылок для сайта не найдено", linksPage.getErrorMessageText());
            assertEquals("rgba(255, 0, 0, 1)", linksPage.getErrorMessageColor());
        });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}
