package com.anirbandhara.pages.Yatra;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage extends CommonToAllPages {
    WebDriver driver;
    WebDriverWait wait;

    public PaymentPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(4));
    }

    private By removeCouponButton = By.xpath("//button[contains(@class,'ApplyCoupon_remove')]");
    private By couponAppliedTotalAmount = By.xpath("//span[contains(@class,'TotalAmount')]");
    private By couponCodeRadioButton = By.id("radio-coupon-radio-1YTINTL");
    private By emailId = By.id("email");
    private By phnNo = By.id("phoneNumber");
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By panDetails = By.id("panDetails");
    private By paymentButton = By.xpath("//button[@aria-label='Proceed to Pay']");

    public void verifyCouponApplyAction() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String totalAmount = driver.findElement(couponAppliedTotalAmount).getText();
        clickElement(removeCouponButton);
        Thread.sleep(2000);
        scrollDown();
        clickElement(couponCodeRadioButton);
        scrollUp();
        Thread.sleep(2000);
        String appliedCouponTotalAmount = driver.findElement(couponAppliedTotalAmount).getText();
        System.out.println("Already Coupon Applied Amount:"+ totalAmount);
        System.out.println("After Removing & Re-applying Coupon:"+ appliedCouponTotalAmount);


    }

    public void entryPersonalDetailsAction(String firstname, String lastname, String phone, String email, String pancard){
        scrollDown();
        enterInput(emailId,email);
        enterInput(phnNo,phone);
        enterInput(firstName,firstname);
        enterInput(lastName,lastname);
        enterInput(panDetails,pancard);
    }

    public void confirmPayment() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(paymentButton));
        clickElement(paymentButton);
        Thread.sleep(2000);
    }
}
