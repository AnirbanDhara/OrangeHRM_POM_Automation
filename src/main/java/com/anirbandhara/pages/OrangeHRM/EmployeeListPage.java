package com.anirbandhara.pages.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor jsExecutor;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // Assuming these locators are defined in your class based on the UI
    private By employeeListTab = By.linkText("Employee List");
    private By addEmployeeButton = By.linkText("Add Employee");
    private By tableRows = By.xpath("//div[@class='oxd-table orangehrm-employee-list']");
    private By firstNameColumn = By.xpath("//div[contains(@class, 'card-item card-body-slot')]/div[1]//following-sibling::div[@class='data']");
    private By lastNameColumn = By.xpath("//div[contains(@class, 'card-item card-body-slot')]/div[2]//following-sibling::div[@class='data']");
    private By paginationButtons = By.xpath("//ul[contains(@class, 'oxd-pagination__ul')]//button[contains(@class, 'oxd-pagination-page-item')]");
    private By nextPageButton = By.xpath("//button[contains(@class, 'oxd-pagination-page-item--previous-next') and .//i[contains(@class, 'bi-chevron-right')]]");
    private By tableContainer = By.xpath("//div[@class='orangehrm-container']");

    public void verifyEmployee(String firstNameVal, String lastNameVal) {
        try {
            String fullName = firstNameVal + " " + lastNameVal;

            // Step 1: Click Add Employee -> then back to Employee List
            WebElement addEmployeeBtn = wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));
            addEmployeeBtn.click();

            WebElement employeeListBtn = wait.until(ExpectedConditions.elementToBeClickable(employeeListTab));
            employeeListBtn.click();

            boolean employeeFound = false;

            while (!employeeFound) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
                List<WebElement> rows = driver.findElements(tableRows);

                System.out.println("Checking rows on current page... Total rows: " + rows.size());

                for (WebElement row : rows) {
                    List<WebElement> cols = row.findElements(By.tagName("td"));
                    if (cols.size() >= 3) {
                        String firstName = cols.get(1).getText().trim();  // Adjust indexes based on actual column layout
                        String lastName = cols.get(2).getText().trim();
                        System.out.println("Found row - FirstName: " + firstName + ", LastName: " + lastName);

                        if (firstName.equalsIgnoreCase(firstNameVal) && lastName.equalsIgnoreCase(lastNameVal)) {
                            System.out.println("Name Verified - " + fullName);
                            employeeFound = true;
                            break;
                        }
                    }
                }

                if (employeeFound) break;

                // Scroll block
                boolean canScrollMore = false;
                try {
                    WebElement table = driver.findElement(tableContainer);
                    long scrollTop = ((Number) jsExecutor.executeScript("return arguments[0].scrollTop", table)).longValue();
                    long clientHeight = ((Number) jsExecutor.executeScript("return arguments[0].clientHeight", table)).longValue();
                    long scrollHeight = ((Number) jsExecutor.executeScript("return arguments[0].scrollHeight", table)).longValue();

                    if (scrollTop + clientHeight < scrollHeight) {
                        jsExecutor.executeScript("arguments[0].scrollTop += arguments[0].clientHeight", table);
                        System.out.println("Scrolled down for more rows...");
                        Thread.sleep(1000); // Replace with wait for new rows ideally
                        canScrollMore = true;
                    }
                } catch (Exception e) {
                    System.out.println("Scroll not possible or not required.");
                }

                // Pagination block
                if (!canScrollMore) {
                    List<WebElement> pageButtons = driver.findElements(paginationButtons);
                    boolean clickedNext = false;

                    for (WebElement button : pageButtons) {
                        String text = button.getText().trim();
                        if (text.matches("\\d+") && !button.getAttribute("class").contains("page-selected")) {
                            System.out.println("Moving to page: " + text);
                            button.click();
                            wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
                            clickedNext = true;
                            break;
                        }
                    }

                    if (!clickedNext) {
                        try {
                            WebElement nextBtn = driver.findElement(nextPageButton);
                            if (nextBtn.isEnabled()) {
                                nextBtn.click();
                                System.out.println("Clicked Next Page button.");
                                wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
                                continue;
                            }
                        } catch (Exception ex) {
                            System.out.println("Next button not found or disabled.");
                        }

                        System.out.println("Name not found in the list after checking all pages - " + fullName);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
