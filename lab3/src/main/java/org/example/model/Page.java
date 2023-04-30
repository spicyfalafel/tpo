package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


@Getter
@Setter
public abstract class Page {

    protected WebDriver driver;
    protected String baseUrl;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Page(WebDriver driver, String baseUrl) {
        PageFactory.initElements(driver, this);
        this.baseUrl = baseUrl;
        this.driver = driver;
    }



    public String getTitle(){
        return this.driver.getTitle();
    }

    public void waitElement(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));

    }
    public void waitStale(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.stalenessOf(element));
    }
    public void wait(int sec){
        // this is dumb way to reproduce Thread.sleep for really hard ways to use Explicit waits
        try {
            new WebDriverWait(driver, Duration.ofSeconds(sec))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            // condition you are certain won't be true
                            By.xpath("//*[contains(text(),'" + "This text will always fail :)" + "')]")));
        }
        catch (TimeoutException te) {
        }
    }
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait(2);
    }
    public void scroll(int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + y + ")", "");
        wait(1);
    }

    public void waitElementToBeClickable(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));

    }

    public void waitPageLoads(){
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}

