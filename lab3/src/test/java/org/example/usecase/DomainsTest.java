package org.example.usecase;

import org.example.Utils;
import org.example.model.DomainsPage;
import org.example.model.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DomainsTest {
    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDrivers();
    }

    @Test
    void testDomains() {
        Utils.getDrivers().forEach(driver ->
        {
            MainPage mainPage = new MainPage(driver);
            mainPage.goToMainPage();
            mainPage.login();
            DomainsPage domainsPage = mainPage.clickFreeDomains();
            assertEquals("https://xtool.ru/svobodnye-domeny/", driver.getCurrentUrl());

            List<String> domains = domainsPage.getDomains();
            var end = domains.stream().map(s -> s.substring(s.length()-3)).collect(Collectors.toList());
            var expected = Collections.nCopies(domains.size(), ".ru");
            assertIterableEquals(expected, end, "some of links in domains are not .ru");

            domainsPage.clickCom();
            domains = domainsPage.getDomains();
            end = domains.stream().map(s -> s.substring(s.length()-4)).collect(Collectors.toList());
            expected = Collections.nCopies(domains.size(), ".com");
            assertIterableEquals(expected, end, "some of links in domains are not .com");

            domainsPage.clickNet();
            domains = domainsPage.getDomains();
            end = domains.stream().map(s -> s.substring(s.length()-4)).collect(Collectors.toList());
            expected = Collections.nCopies(domains.size(), ".net");
            assertIterableEquals(expected, end, "some of links in domains are not .net");
        });
    }

    @AfterAll
    static void exit(){
        Utils.exit();
    }
}
