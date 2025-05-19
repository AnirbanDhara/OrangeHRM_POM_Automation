package com.anirbandhara.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrangeHRMLoginTest {

    WebDriver driver;
    String baseUrl = "https://opensource-demo.orangehrmlive.com/";

    // Page Object elements
    private WebElement getUsernameField() {
        return driver.findElement(By.name("username"));
    }

    private WebElement getPasswordField() {
        return driver.findElement(By.name("password"));
    }

    private WebElement getLoginButton() {
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }

    private WebElement getErrorMessage() {
        return driver.findElement(By.xpath("//div[@class='oxd-alert-content oxd-alert-content--error']"));
    }

    private WebElement getDashboardElement() {
        return driver.findElement(By.xpath("//a[@href='/web/index.php/dashboard/index']"));
    }

    private WebElement getLogoutDropDown(){
        return driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']"));
    }

    private WebElement getLogOutButton(){
        return driver.findElement(By.linkText("Logout"));
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {
        getUsernameField().clear();
        Thread.sleep(2000);
        getUsernameField().sendKeys("Admin");
        getPasswordField().clear();
        getPasswordField().sendKeys("admin123");
        getLoginButton().click();
        Thread.sleep(2000);

        // Assert successful login by checking for Dashboard element
        Assert.assertTrue(getDashboardElement().isDisplayed(),
                "Dashboard should be displayed after successful login");
        getLogoutDropDown().click();
        Thread.sleep(2000);
        getLogOutButton().click();

    }

    @Test(priority = 2)
    public void testInvalidUsernameValidPassword() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(2000);
        getUsernameField().clear();
        getUsernameField().sendKeys("InvalidUser");
        getPasswordField().clear();
        getPasswordField().sendKeys("admin123");
        getLoginButton().click();
        Thread.sleep(2000);

        // Assert error message for invalid credentials
        String expectedError = "Invalid credentials";
        String actualError = getErrorMessage().getText();
        Assert.assertEquals(actualError, expectedError,
                "Error message should indicate invalid credentials");

    }

    @Test(priority = 3)
    public void testValidUsernameInvalidPassword() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(2000);
        getUsernameField().clear();
        getUsernameField().sendKeys("Admin");
        getPasswordField().clear();
        getPasswordField().sendKeys("invalid123");
        getLoginButton().click();
        Thread.sleep(2000);

        // Assert error message for invalid credentials
        String expectedError = "Invalid credentials";
        String actualError = getErrorMessage().getText();
        Assert.assertEquals(actualError, expectedError,
                "Error message should indicate invalid credentials");

    }

    @Test(priority = 4)
    public void testEmptyCredentials() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(2000);
        getUsernameField().clear();
        getPasswordField().clear();
        getLoginButton().click();
        Thread.sleep(2000);

        // Assert error message for required fields
        String expectedError = "Required";
        String actualError = driver.findElement(By.xpath("//span[text()='Required']")).getText();
        Assert.assertEquals(actualError, expectedError,
                "Error message should indicate required fields");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
