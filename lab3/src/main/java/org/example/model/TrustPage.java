package org.example.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrustPage extends Page{

    public TrustPage(WebDriver driver) {
        super(driver);
    }

    public TrustPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }


    @FindBy(xpath = "//div[@class='panel']/div[2]//span[contains(@class, 'fw-bold')]")
    public WebElement trustIndexText;

    @FindBy(xpath = "//div[@class='panel']//div[@class='h1']/span[@rel='nofollow']")
    public WebElement trustSiteText;

    public Float getTrustIndex(){
        waitElement(trustIndexText);
        var index =trustIndexText.getText();
        return Float.parseFloat(index);
    }

    public String getTrustSiteText(){
        waitElement(trustSiteText);
        return trustSiteText.getText();
    }


}
