package com.anirbandhara.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DashBoard {
    WebDriver driver;
    By pimMenu = By.xpath("//span[text()='PIM']");

    public DashBoard(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPIM() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(pimMenu)).click().perform();
    }

    public void logout() {
        driver.findElement(By.className("oxd-userdropdown-name")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
}
