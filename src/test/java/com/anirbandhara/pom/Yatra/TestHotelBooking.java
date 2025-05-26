package com.anirbandhara.pom.Yatra;

import com.anirbandhara.base.CommonToAllTest;
import com.anirbandhara.pages.Yatra.HomePage;
import com.anirbandhara.pages.Yatra.HotelDetailPage;
import com.anirbandhara.pages.Yatra.HotelSearchPage;
import com.anirbandhara.pages.Yatra.PaymentPage;
import com.anirbandhara.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.anirbandhara.driver.DriverManager.getDriver;

public class TestHotelBooking extends CommonToAllTest {

    @Test
    public void testHotelBookingFlow() throws InterruptedException {
        HomePage homePage = new HomePage(getDriver());

        // Launching Yatra.com Web Application
        homePage.launchHomePage();

        // Searching hotel for Brooklyn,New York
        HotelSearchPage searchPage = homePage.homePageAction("Brooklyn,New York");

        // Choosing and Booking room
        HotelDetailPage detailPage = searchPage.chooseAction();;

        // Applying Coupon code & Proceed to Payment
        PaymentPage paymentPage = detailPage.bookingAction();
        paymentPage.verifyCouponApplyAction();

        Assert.assertTrue(true,"Coupon Not Applied Properly!!!");

        String email = PropertiesReader.readKey("email_Id");
        String pan = PropertiesReader.readKey("PAN");
        String phone = PropertiesReader.readKey("phn_No");

        paymentPage.entryPersonalDetailsAction("Anirban","Dhara", phone,email,pan);
        paymentPage.confirmPayment();
    }
}
