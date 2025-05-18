package com.anirbandhara.pages;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends CommonToAllPages {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type='submit']");

    public void login(String user, String pass) throws InterruptedException {
        openOrangeHRMUrl();
        Thread.sleep(2000);
        enterInput(username,user);
        enterInput(password,pass);
        clickElement(loginBtn);
        Thread.sleep(2000);
    }
}
