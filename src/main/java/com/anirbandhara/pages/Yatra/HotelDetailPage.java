package com.anirbandhara.pages.Yatra;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class HotelDetailPage extends CommonToAllPages {

    WebDriver driver;
    WebDriverWait wait;

    public HotelDetailPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //button[@aria-label='Book this room']
    //div[contains(@class,'HotelOverview_buttonWrapper')]/button[2]
    private By bookingButton = By.xpath("//button[contains(text(),'Book this room')]");

    public PaymentPage bookingAction(){
        // Save current window handle
        String originalWindow = driver.getWindowHandle();

        // Wait for new window to appear
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        // Loop through all window handles
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(bookingButton));
        scrollUp();
//        WebElement button = driver.findElement(bookingButton);
//        System.out.println("Button Text: " + button.getText());

        wait.until(ExpectedConditions.elementToBeClickable(bookingButton));
        clickElement(bookingButton);

        return new PaymentPage(driver);
    }
}
