package org.example.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class InnerLinksPage extends Page{

    public InnerLinksPage (WebDriver driver) {
        super(driver, "https://xtool.ru/backlinks/iskhodyashchie-ssylki/");
    }

    public InnerLinksPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    @FindBy(xpath = "//form//input")
    public WebElement urlInput;

    @FindBy(xpath = "//form//button")
    public WebElement findButton;

    @FindBy(xpath ="//tbody/tr/td[1]")
    public List<WebElement> tableOfLinks;

    @FindBy(xpath ="//tbody/tr[1]")
    public WebElement table;

    public void checkUrl(String url) {
        waitPageLoads();
        var butt = driver.findElement(By.xpath("//form//button"));
        scroll(400);
        waitElement(butt);
        waitElementToBeClickable(butt);

        urlInput.click();
        urlInput.clear();
        urlInput.sendKeys(url);
        butt.click();

    }

    public List<String> getAllInnerLinks(){
        waitPageLoads();
        scroll(400);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return tableOfLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @FindBy(xpath = "//tbody/tr/td/div")
    WebElement errorMessage;

    public String getErrorMessageText() {
        waitPageLoads();
        waitElement(errorMessage);
        return errorMessage.getText();
    }
}
