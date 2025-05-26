package com.anirbandhara.pages.OrangeHRM;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class DashboardPage extends CommonToAllPages {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    private By pimTab = By.xpath("//span[text() = 'PIM']");
    private By logoutDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private By logoutBtn = By.linkText("Logout");

    public void navigateToPIM() {
       clickElement(pimTab);
    }

    public void logout() throws InterruptedException {
        clickElement(logoutDropdown);
        Thread.sleep(2000);
        clickElement(logoutBtn);
    }
}
