package com.anirbandhara.base;

import com.anirbandhara.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.anirbandhara.driver.DriverManager.getDriver;

public class CommonToAllPages {
//
//    public void openOrangeHRMUrl(){
//        getDriver().get(PropertiesReader.readKey("url"));
//    }
    Actions actions;

    public void openYatraUrl(){
        getDriver().get(PropertiesReader.readKey("url_HotelApp"));
    }

    public void clickElement(By by) {
        getDriver().findElement(by).click();
    }

    public void clickElement(WebElement ele) {
        ele.click();
    }

    public void enterInput(By by, String key) {
        getDriver().findElement(by).sendKeys(key);
    }

    public void enterInput(WebElement ele, String key) {
        ele.sendKeys(key);
    }

    public void scrollDown(){
        actions = new Actions(getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public void scrollUp(){
        actions = new Actions(getDriver());
        actions.sendKeys(Keys.PAGE_UP).build().perform();
    }


}
