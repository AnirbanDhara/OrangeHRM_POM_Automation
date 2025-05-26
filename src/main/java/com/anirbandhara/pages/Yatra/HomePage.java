package com.anirbandhara.pages.Yatra;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class HomePage extends CommonToAllPages {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private By searchCity = By.xpath("//div[contains(@class,'SearchInputField_infoWrapper')]");
    private By inputCity = By.xpath("//input[@type='text']");
    private By cityFromList = By.xpath("//ul/div[1]");
    private By check_In_Button = By.xpath("//div[contains(@class,'CustomDateField_search')]");
    private By check_In_Date = By.xpath("//div[@aria-label='Choose Tuesday, June 10th, 2025']");
    private By check_Out_Date = By.xpath("//div[@aria-label='Choose Sunday, June 15th, 2025']");
    private By guest = By.xpath("//div[contains(@class,'SearchInputField_search')]/p/strong[2]");
    private By numberOfGuest = By.xpath("//button[@aria-label='1']");
    private By applyButton = By.xpath("//button[@aria-label='Apply']");
    private By searchButton = By.xpath("//button[@aria-label='Search']");

    public void launchHomePage(){
        openYatraUrl();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public HotelSearchPage homePageAction(String city) throws InterruptedException {

        // Searching for New York
        clickElement(searchCity);
        enterInput(inputCity,city);
        Thread.sleep(1000);
        clickElement(cityFromList);
        Thread.sleep(1000);

        // Setting checkIn and checkOut
        clickElement(check_In_Button);
        clickElement(check_In_Date);
        clickElement(check_Out_Date);

        // Setting No. of guest
        clickElement(guest);
        clickElement(numberOfGuest);
        clickElement(applyButton);

        // Click SearchButton and go to Next Page
        clickElement(searchButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return new HotelSearchPage(driver);
    }

}
