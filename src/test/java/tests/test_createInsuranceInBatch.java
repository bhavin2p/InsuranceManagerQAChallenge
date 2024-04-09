package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ImportInsurancesPage;
import pages.HomePage;
import pages.LoginPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class test_createInsuranceInBatch extends BaseSetupFile {

    static Logger log = Logger.getLogger(test_createInsuranceInBatch.class);

    @Test(dataProvider = "loginDetails")
    public void createInsuranceUsingCsvFile(String uName, String uPass) {
        String errorMsg;
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully for Create Insurance Using CSV File Test");
        ImportInsurancesPage importFile = home.clickOnImportOption();
        importFile.importFile();
        errorMsg = importFile.getErrorNotificationMessage();
        log.info("On Create Insurance using file upload getting error..");
        Assert.assertTrue(errorMsg.contains("solve the partner assignment for the payment schema id"));

    }

}
