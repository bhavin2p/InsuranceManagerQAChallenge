package tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.InsuranceDetailsPage;
import pages.LoginPage;
import pages.newTabSubPages.ConfirmationViewPage;
import pages.newTabSubPages.CustomerViewPage;
import pages.newTabSubPages.ProductViewPage;
import pages.paymentPages.PaymentInformation;
import pages.paymentPages.PaymentMethod;

public class test_createInsuranceSingle extends BaseSetupFile{

    static Logger log = Logger.getLogger(test_createInsuranceSingle.class);

    @Test(dataProvider = "insuranceDetails")
    public void createSingleInsurancePolicy(String username, String password, String serialNo, String deviceName, String invoiceNo, String orderNo,
           String fName, String lName, String email, String street, String house, String pin, String city, String country, String taxCode,
           String cardNo, String expDate, String cvvNo) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(username, password);
        log.info("Login Successfully for Create Insurance for Single Policy");
        ProductViewPage product = home.clickNewOption();
        log.info("Selected new menu link..");
        CustomerViewPage customer = product.selectProductInfo(serialNo, deviceName, invoiceNo, orderNo);
        log.info("Entered the mandatory details on Product Info Page");
        ConfirmationViewPage confirm = customer.enterCustomerInfo(fName, lName, email, street, house, pin, city, country, taxCode);
        log.info("Entered the mandatory details on Customer View Page");
        PaymentMethod paymentMethod =  confirm.selectAllCheckBoxes();
        log.info("Verify the details and select all the checkboxes");
        PaymentInformation paymentInformation = paymentMethod.clickOnContinueToPayment();
        log.info("Click on continue payment..");
        InsuranceDetailsPage insuranceDetailsPage = paymentInformation.enterPaymentDetails(cardNo, expDate, cvvNo);
        log.info("Entered the payment details. ");
        Assert.assertEquals(insuranceDetailsPage.getHeader(), "Insurance details");
    }

}
