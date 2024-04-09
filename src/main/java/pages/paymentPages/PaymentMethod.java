package pages.paymentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentMethod {

    static WebDriver driver;

    public PaymentMethod(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@value='STRIPE.CARD']")
    WebElement creditCard_debitCard_RatioButton;

    @FindBy(xpath = "//span[contains(text(),'Continue to pay')]")
    WebElement continueToPayButton;

    public PaymentInformation clickOnContinueToPayment() {
        creditCard_debitCard_RatioButton.click();
        continueToPayButton.click();
        return new PaymentInformation(driver);
    }

}
