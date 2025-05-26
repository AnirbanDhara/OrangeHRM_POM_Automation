package com.anirbandhara.pages.Yatra;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HotelSearchPage extends CommonToAllPages {
    WebDriver driver;
    WebDriverWait wait;

    public HotelSearchPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By chooseButton = By.xpath("//button[@aria-label='Choose Room']");

    public HotelDetailPage chooseAction() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(chooseButton));
        clickElement(chooseButton);
        return new HotelDetailPage(driver);
    }
}
