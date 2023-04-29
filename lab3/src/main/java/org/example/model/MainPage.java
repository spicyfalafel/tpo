package org.example.model;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends Page{

    public static final String BASE_URL = "https://xtool.ru/";

    public final String XPATH_NAV_ANALYZE="//a[@class=\"nav-link\"][@href=\"//xtool.ru/analyze/\"]";
    public final String XPATH_ACTUAL_UPDATE_BUTTON="//div[contains(@class, 'container-fluid')]//button[contains(@class, 'btn-outline-warning')]";

    @FindBy(xpath = "//nav//div[@class='container-fluid']//div[2]//a[2]")
    public WebElement loginButton;

    @FindBy(xpath = "//div//form//div[@class='modal-body']//input[1]")
    public WebElement loginEmailInput;

    @FindBy(xpath = "//div//form//div[@class='modal-body']//input[2]")
    public WebElement loginPasswordInput;

    @FindBy(xpath = "//div//form//div[@class='modal-footer']//button[@type='submit']")
    public WebElement loginSubmitButton;


    @FindBy(xpath = "//nav//div[2]//div[1]/div[1]//div")
    public WebElement authedEmailText;


    @FindBy(xpath = "//nav//div[2]//div[1]//a")
    public WebElement authedAccountButton;

    @FindBy(xpath = "//nav//div[2]//div[1]//a")
    public WebElement authedExitButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void goToMainPage(){
        this.driver.get(BASE_URL);
        new WebDriverWait(this.driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
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

}
