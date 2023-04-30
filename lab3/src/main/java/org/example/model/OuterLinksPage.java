package org.example.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OuterLinksPage extends Page{

    public OuterLinksPage (WebDriver driver) {
        super(driver);
    }

    public OuterLinksPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }



    @FindBy(xpath = "//form//input")
    public WebElement urlInput;

    @FindBy(xpath = "//form//button")
    public WebElement findButton;

    @FindBy(xpath = "//div[@class='panel']//div//span[@rel='nofollow']")
    public WebElement siteText;

    @FindBy(xpath = "//div[@class='panel']//div//p/span[@class='mc']")
    public WebElement numberOfLinks;

    @FindBy(xpath = "//div[@class='accordion-item'][2]//li[2]")
    public WebElement innerLinksLink;

    public String getNumberOfLinks(){
        return numberOfLinks.getText();
    }
    public String getSiteText(){
        return siteText.getText();
    }

    public void checkUrl(String url){
        scrollToElement(urlInput);
        waitElement(urlInput);
        waitElementToBeClickable(urlInput);
        urlInput.click();
        urlInput.sendKeys(url);
        findButton.click();
    }

    public InnerLinksPage clickInnerLinks() {
        waitElementToBeClickable(innerLinksLink);
        innerLinksLink.click();
        return new InnerLinksPage(driver);
    }


    @FindBy(xpath ="//div[@class='panel']//b")
    WebElement errorMessage;

    public String getErrorMessageText() {
        waitPageLoads();
        waitElement(errorMessage);
        return errorMessage.getText();
    }

    public String getErrorMessageColor(){
        waitElement(errorMessage);
        return errorMessage.getCssValue("color");
    }

}
