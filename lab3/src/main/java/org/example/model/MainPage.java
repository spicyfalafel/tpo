package org.example.model;

import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Page{

    public static final String BASE_URL = "https://xtool.ru/";

    public MainPage(WebDriver driver) {
        super(driver, BASE_URL);
    }

    @FindBy(xpath = "//form/div/input")
    public WebElement trustInput;


    @FindBy(xpath = "//form/div/button")
    public WebElement trustCheckButton;


    @FindBy(xpath = "//nav//div[@class='container-fluid']//div[2]//a[2]")
    public WebElement loginButton;

    @FindBy(xpath = "//div//form//div[@class='modal-body']//input[1]")
    public WebElement loginEmailInput;

    @FindBy(xpath = "//div//form//div[@class='modal-body']//input[2]")
    public WebElement loginPasswordInput;

    @FindBy(xpath = "//div//form//div[@class='modal-footer']//button[@type='submit']")
    public WebElement loginSubmitButton;

    @FindBy(xpath = "//div//form//div[@class='modal-body']//div[1]")
    public WebElement badLoginDiv;

    public String getBadLoginText(){
        waitElement(badLoginDiv);
        waitPageLoads();
        return badLoginDiv.getText();
    }
    @FindBy(xpath = "//nav//div[2]//div[1]/div[1]//div")
    public WebElement authedEmailText;

    @FindBy(xpath = "//nav//div[2]//div[1]//a")
    public WebElement authedAccountButton;

    @FindBy(xpath = "//nav//div[2]//div[1]//a")
    public WebElement authedExitButton;

    @FindBy(xpath = "//main/section[1]//div/div[2]/div[2]//a")
    public WebElement outerLinks;

    @FindBy(xpath = "//main/section[1]//div/div[2]/div[6]//a")
    public WebElement freeDomains;

    public void goToMainPage(){
        this.driver.get(BASE_URL);
        new WebDriverWait(this.driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static final String MY_EMAIL= "krivosheev.sviat@gmail.com";
    public static final String MY_PASSWORD= "pU45yp";

    public void login(){
        login(MY_EMAIL, MY_PASSWORD);
    }

    public void login(String email, String password){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(loginEmailInput));
        loginEmailInput.click();
        loginEmailInput.sendKeys(email);
        loginPasswordInput.click();
        loginPasswordInput.sendKeys(password);
        loginSubmitButton.click();
    }

    public String authorizedEmailText(){
        waitElement(this.authedEmailText);
        return this.authedEmailText.getText();
    }

    public void exit(){
        waitElement(authedAccountButton);
        authedAccountButton.click();
        waitElement(authedExitButton);
        authedExitButton.click();
    }

    public void checkTrust(String url){
        waitElement(authedEmailText);
        scrollToElement(trustInput);
//        waitElement(trustInput);
        waitElementToBeClickable(trustInput);
        trustInput.click();
        trustInput.sendKeys(url);
        trustCheckButton.click();
    }

    public OuterLinksPage clickOuterLinks(){
        waitElement(authedEmailText);
        scrollToElement(outerLinks);
        waitElement(outerLinks);
        outerLinks.click();
        return new OuterLinksPage(driver);
    }


    public DomainsPage clickFreeDomains(){
        waitElement(authedEmailText);
        scrollToElement(freeDomains);
        waitElementToBeClickable(freeDomains);
        freeDomains.click();
        return new DomainsPage(driver);
    }

}
