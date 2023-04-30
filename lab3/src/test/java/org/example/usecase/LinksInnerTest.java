package org.example.usecase;

import org.example.Utils;
import org.example.model.InnerLinksPage;
import org.example.model.MainPage;
import org.example.model.OuterLinksPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LinksInnerTest {
    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }
    static final String TEST_URL = "https://se.ifmo.ru/";

    @Test
    void testInnerLinks() {
        Utils.getDrivers().forEach(driver ->
        {
            MainPage mainPage = new MainPage(driver);
            mainPage.goToMainPage();
            mainPage.login();
            OuterLinksPage linksPage = mainPage.clickOuterLinks();
            InnerLinksPage innerLinksPage = linksPage.clickInnerLinks();
            innerLinksPage.checkUrl(TEST_URL);
            var links = innerLinksPage.getAllInnerLinks();
            var size = links.size();
            var domain = "se.ifmo.ru";
            var expected = Collections.nCopies(size, domain);
            var domains = links.stream().map(s -> s.substring(0, domain.length())).collect(Collectors.toList());
            assertIterableEquals(expected, domains, "some of inner links in table were not started with se.ifmo.ru");

            innerLinksPage.checkUrl("se.ifmonsteirnstiesr.ru");
            assertEquals("Ничего не найдено.", innerLinksPage.getErrorMessageText());
        });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}
