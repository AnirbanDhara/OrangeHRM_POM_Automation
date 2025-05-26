package com.anirbandhara.pages.OrangeHRM;

import com.anirbandhara.base.CommonToAllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage extends CommonToAllPages {

        WebDriver driver;
        WebDriverWait wait;

        // Constructor
        public PIMPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        private By EmployeeTab = By.linkText("Add Employee");
        private By fstName = By.name("firstName");
        private By lstName = By.name("lastName");
        private By save = By.xpath("//button[@type='submit']");
        private By employeeList = By.xpath("//h6[text()='Personal Details']");

        // Add a single employee
        public void addEmployee(String firstNameVal, String lastNameVal) {
            // Navigate to Add Employee tab
            WebElement addEmployeeTab = wait.until(ExpectedConditions.elementToBeClickable(EmployeeTab));
            addEmployeeTab.click();

            // Fill form
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(fstName));
            WebElement lastName = driver.findElement(lstName);
            WebElement saveButton = driver.findElement(save);

            firstName.clear();  // clear field in case of cached input
            lastName.clear();
            enterInput(firstName,firstNameVal);
            enterInput(lastName,lastNameVal);
            clickElement(saveButton);

            // Wait for confirmation the employee is saved (e.g., landing on Personal Details page)
            wait.until(ExpectedConditions.visibilityOfElementLocated(employeeList));
        }

        // Add multiple employees
        public void addMultipleEmployees(String[][] employeeData) {
            for (String[] emp : employeeData) {
                addEmployee(emp[0], emp[1]);
            }
        }
}
