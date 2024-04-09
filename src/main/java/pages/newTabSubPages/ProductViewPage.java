package pages.newTabSubPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductViewPage {

    WebDriver driver;

    public ProductViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "country-select")
    WebElement countryDropDown;

    @FindBy(xpath = "//li[@data-value='FI']")
    WebElement countrySelect;

    @FindBy(css = "div#product-name-select")
    WebElement productNameDropDown;

    @FindBy(xpath = "//ul/li[@tabindex='0']")
    WebElement productSelect;

    @FindBy(id = "tariff-name-select")
    WebElement tariffNameDropDown;

    @FindBy(xpath = "//li[contains(@data-value,'1-34-Nokia')]")
    WebElement tariffSelect;

    @FindBy(id = "category-name-select")
    WebElement insuranceCategoryDropDown;

    @FindBy(xpath = "//li[contains(@data-value,'CATEGORY')]")
    WebElement insuranceCategorySelect;

    @FindBy(id = "duration-select")
    WebElement periodDropDown;

    @FindBy(xpath = "//li[contains(@data-value,'12 month')]")
    WebElement periodSelect;

    @FindBy(id = "frequency-select")
    WebElement paymentTypeDropDown;

    @FindBy(xpath = "//ul/li/span[contains(text(),'One-time')]")
    WebElement paymentTypeSelect;

    @FindBy(id = "class-name-select")
    WebElement classNameDropDown;

    @FindBy(xpath = "//ul/li[contains(text(),'Nokia 3/500')]")
    WebElement classSelect;

    @FindBy(name = "serialNumber")
    WebElement serialNo;

    @FindBy(id = "input-createCertificate_deviceName")
    WebElement deviceName;

    @FindBy(name = "invoiceNumber")
    WebElement invoiceNo;

    @FindBy(id = "input-createCertificate_orderNumber")
    WebElement orderNo;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    WebElement nextButtonProduct;

    public void selectCountry() {
        countryDropDown.click();
        countrySelect.click();
    }

    public void selectProduct() {
        productNameDropDown.click();
        productSelect.click();
    }

    public void selectTariff() {
        tariffNameDropDown.click();
        tariffSelect.click();
    }

    public void selectCategory() {
        insuranceCategoryDropDown.click();
        insuranceCategorySelect.click();
    }

    public void selectPeriod() {
        periodDropDown.click();
        periodSelect.click();
    }

    public void selectPayment() {
        paymentTypeDropDown.click();
        paymentTypeSelect.click();
    }

    public void selectClass() {
        classNameDropDown.click();
        classSelect.click();
    }

    public void enterSerialNumber(String serialNum) {
        serialNo.sendKeys(serialNum);
    }

    public void enterDeviceName(String device) {
        deviceName.sendKeys(device);
    }

    public void enterInvoiceNumber(String number) {
        invoiceNo.sendKeys(number);
    }

    public void enterOrderNumber(String orderNumber){
        orderNo.sendKeys(orderNumber);
    }

    public void clickOnNextTab() {
        nextButtonProduct.click();
    }

    public CustomerViewPage selectProductInfo(String serialNo, String deviceName, String invoiceNo, String orderNo) {
        selectCountry();
        selectProduct();
        selectTariff();
        selectCategory();
        selectPeriod();
        selectPayment();
        selectClass();
        enterSerialNumber(serialNo);
        enterDeviceName(deviceName);
        enterInvoiceNumber(invoiceNo);
        enterOrderNumber(orderNo);
        clickOnNextTab();
        return new CustomerViewPage(driver);
    }

}
