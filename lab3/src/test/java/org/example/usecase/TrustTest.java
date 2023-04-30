package org.example.usecase;

import org.example.Utils;
import org.example.model.MainPage;
import org.example.model.TrustPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrustTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }
    static final String TEST_URL = "https://se.ifmo.ru/";

    @Test
    void testTrust() {
        Utils.getDrivers().forEach(driver ->
        {
            MainPage mainPage = new MainPage(driver);
            mainPage.goToMainPage();
            mainPage.login();
            mainPage.checkTrust(TEST_URL);
            assertEquals("https://xtool.ru/analyze/_se.ifmo.ru/", driver.getCurrentUrl());
            TrustPage trustPage = new TrustPage(driver);
            assertTrue(Float.isFinite(trustPage.getTrustIndex()));
            assertEquals("se.ifmo.ru", trustPage.getTrustSiteText());
        });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}
