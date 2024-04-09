package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.CommonUtils;

import java.time.Duration;
import java.util.Properties;

public class BaseSetupFile {

    static WebDriver driver;
    Properties prop;
    CommonUtils commonUtils = new CommonUtils();

    @BeforeSuite
    public void doSetup(){
        prop = commonUtils.readProp();
        String url = prop.getProperty("url");
        if(prop.getProperty("browserName").equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if(prop.getProperty("browserName").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if(prop.getProperty("browserName").equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DataProvider(name = "insuranceDetails")
    public Object[][] getDetailsFromPropertyFile() {
        prop = commonUtils.readProp();
        return new Object[][]{
                {prop.getProperty("un"), prop.getProperty("pw"),
                prop.getProperty("serialNumber"), prop.getProperty("deviceName"),
                prop.getProperty("invoiceNumber"), prop.getProperty("orderNumber"),
                prop.getProperty("firstName"), prop.getProperty("lastName"),
                prop.getProperty("email"), prop.getProperty("street"),
                prop.getProperty("houseNumber"), prop.getProperty("postalCode"),
                prop.getProperty("city"), prop.getProperty("country"),
                prop.getProperty("taxCode"), prop.getProperty("cardNumber"),
                prop.getProperty("expDate"), prop.getProperty("cvv")}
        };
    }

    @DataProvider(name = "loginDetails")
    public Object[][] loginDetail() {
        prop = commonUtils.readProp();
        return new Object[][]{
                {prop.getProperty("un"), prop.getProperty("pw")}
        };
    }

}
