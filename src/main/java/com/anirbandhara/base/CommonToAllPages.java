package com.anirbandhara.base;

import com.anirbandhara.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.anirbandhara.driver.DriverManager.getDriver;

public class CommonToAllPages {

    public void openOrangeHRMUrl(){
        getDriver().get(PropertiesReader.readKey("url"));
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

}
