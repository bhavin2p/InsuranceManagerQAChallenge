package pages.paymentPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InsuranceDetailsPage;
import utilities.SeleniumCommonMethods;

import java.time.Duration;

public class PaymentInformation {

    WebDriver driver;
    WebDriverWait wait;

    public PaymentInformation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@class='__PrivateStripeElement-input']")
    WebElement cardNo;

    @FindBy(xpath = "//input[@name='exp-date']")
    WebElement expDate;

    @FindBy(xpath = "//input[@name='cvv']")
    WebElement cardCvv;

    @FindBy(xpath = "//span[text()='Place order']")
    WebElement makePayment;

    @FindBy(name = "//span[contains(text(),'Back')]")
    WebElement backButton;

    public void enterCardDetails(String cardNumberVal) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs("simplesurance - Your platform for simple access to insurance"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", cardNo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@class='__PrivateStripeElement-input']"))).
                sendKeys(cardNumberVal);
        cardNo.sendKeys(cardNumberVal);

    }

    public void enterExpDate(String monthYear) {
        SeleniumCommonMethods.switchToFrameUsingIndex(1, driver);
        expDate.sendKeys(monthYear);
    }

    public void enterCvv(String cvvNumber) {
        cardCvv.sendKeys(cvvNumber);
    }

    public void clickMakePayment() {
        makePayment.click();
    }

    public InsuranceDetailsPage enterPaymentDetails(String cardNum, String expDate, String cvv) {
        enterCardDetails(cardNum);
        enterExpDate(expDate);
        enterCvv(cvv);
        clickMakePayment();
        return new InsuranceDetailsPage(driver);
    }
}
