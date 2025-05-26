package com.anirbandhara.pom.OrangeHRM;

import com.anirbandhara.base.CommonToAllTest;
import com.anirbandhara.pages.OrangeHRM.DashboardPage;
import com.anirbandhara.pages.OrangeHRM.EmployeeListPage;
import com.anirbandhara.pages.OrangeHRM.LoginPage;
import com.anirbandhara.pages.OrangeHRM.PIMPage;
import com.anirbandhara.utils.PropertiesReader;
import org.testng.annotations.Test;

import static com.anirbandhara.driver.DriverManager.getDriver;

public class TestOrangeHRM extends CommonToAllTest {

    @Test
    public void testOrangeHRMFlow() throws InterruptedException {
        // 1. Login Functionality
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(PropertiesReader.readKey("Username"),PropertiesReader.readKey("Password"));

        // 2. Navigate From Dashboard to PIM
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateToPIM();

        // 3. On the PIM page, click on "Add Employee" and add 3-4 employees.
        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.addEmployee("Avik","Sen");
        pimPage.addEmployee("Anik", "Pal");
        pimPage.addEmployee("Arnab","Basu");


        // 4. Verify Employees in the EmployeeList
        EmployeeListPage listPage = new EmployeeListPage(getDriver());
        listPage.verifyEmployee("Avik","Sen");
        listPage.verifyEmployee("Anik","Pal");
        listPage.verifyEmployee("Arnab","Basu");

        // 5. Logout from Dashboard Page
        dashboardPage.logout();

    }

}
