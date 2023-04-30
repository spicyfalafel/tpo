package org.example.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class DomainsPage extends Page{

    public DomainsPage (WebDriver driver) {
        super(driver, "https://xtool.ru/svobodnye-domeny/");
    }

    public DomainsPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    @FindBy(xpath = "//main/div[2]/div//a[2]//li")
    public WebElement comZone;


    @FindBy(xpath = "//main/div[2]/div//a[3]//li")
    public WebElement netZone;

    public void clickCom(){
        waitElement(comZone);
        comZone.click();
        waitPageLoads();
    }

    public void clickNet(){
        waitElement(netZone);
        netZone.click();
        waitPageLoads();
    }


    @FindBy(xpath = "//tbody//tr/td[1]")
    public List<WebElement> domainsInTable;

    public List<String> getDomains() {
        return domainsInTable.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}

