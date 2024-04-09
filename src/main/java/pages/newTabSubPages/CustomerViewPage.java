package pages.newTabSubPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerViewPage {

    WebDriver driver;

    public CustomerViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[value='male']")
    WebElement salutation;

    @FindBy(id = "input-createCertificate_firstName")
    WebElement fName;

    @FindBy(name = "lastName")
    WebElement lName;

    @FindBy(css = "input#input-createCertificate_email")
    WebElement email;

    @FindBy(name = "streetName")
    WebElement streetName;

    @FindBy(xpath = "//input[@id='input-createCertificate_streetNumber']")
    WebElement houseNumber;

    @FindBy(name = "zip")
    WebElement postalCode;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityName;

    @FindBy(id = "input-createCertificate_country")
    WebElement countryName;

    @FindBy(xpath = "//input[@name='taxCode']")
    WebElement taxCode;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    WebElement nextButtonCustomer;

    public void selectSalutation(){
        salutation.click();
    }

    public void enterFName(String fname) {
        fName.sendKeys(fname);
    }

    public void enterLName(String lname) {
        lName.sendKeys(lname);
    }

    public void enterEmail(String uEmail) {
        email.sendKeys(uEmail);
    }

    public void enterStreetName(String uStreetName) {
        streetName.sendKeys(uStreetName);
    }

    public void enterStreetNumber(String uStreetNum) {
        houseNumber.sendKeys(uStreetNum);
    }

    public void enterZipCode(String zipCode) {
        postalCode.sendKeys(zipCode);
    }

    public void enterCityName(String uCityName) {
        cityName.sendKeys(uCityName);
    }

    public void enterCountryName(String uCountryName) {
        countryName.sendKeys(uCountryName);
    }

    public void enterTaxCode(String uCode) {
        taxCode.sendKeys(uCode);
    }

    public void clickOnNextTab() {
        nextButtonCustomer.click();
    }

    public ConfirmationViewPage enterCustomerInfo(String fName, String lName, String emailId, String street, String house, String pin, String city, String country, String taxCode) {
        selectSalutation();
        enterFName(fName);
        enterLName(lName);
        enterEmail(emailId);
        enterStreetName(street);
        enterStreetNumber(house);
        enterZipCode(pin);
        enterCityName(city);
        enterCountryName(country);
        enterTaxCode(taxCode);
        clickOnNextTab();
        return new ConfirmationViewPage(driver);
    }
}
