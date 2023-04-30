package org.example.usecase;

import org.example.Utils;
import org.example.model.MainPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest {
    public static final String MY_EMAIL= "krivosheev.sviat@gmail.com";
    public static final String MY_PASSWORD= "pU45yp";
    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void testAuthorization() {
        Utils.getDrivers().forEach(driver ->
                {
                    MainPage mainPage = new MainPage(driver);
                    mainPage.goToMainPage();
                    mainPage.login(MY_EMAIL, MY_PASSWORD);
                    assertEquals(MY_EMAIL, mainPage.authorizedEmailText());

                    mainPage.exit();
                    assertEquals("https://xtool.ru/", driver.getCurrentUrl());
                    assertTrue(mainPage.loginButton.isDisplayed());

                    mainPage.login("notemail", "badpas");
                    var badLogin = mainPage.getBadLoginText();
                    assertEquals("Не верный формат e-mail адреса", badLogin);
                });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}
