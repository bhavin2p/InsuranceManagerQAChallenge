package pages.newTabSubPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.paymentPages.PaymentMethod;

public class ConfirmationViewPage {

    WebDriver driver;

    public ConfirmationViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/label/span/span[contains(text(),'information')]/preceding::span/input")
    WebElement allInformationcheckbox;

    @FindBy(xpath = "(//div/label/span/span[contains(text(),'customer consents')]/preceding::span/input)[2]")
    WebElement customerCheckBox;

    @FindBy(xpath = "(//div/label/span/span[contains(text(),'customer has been')]/preceding::span/input)[3]")
    WebElement infoCheckBox;

    @FindBy(xpath = "(//div/label/span/span[contains(text(),'Insured product')]/preceding::span/input)[4]")
    WebElement insuredCheckBox;

    @FindBy(xpath = "//span[contains(text(),'Create insurance')]")
    WebElement createInsuranceButton;

    public PaymentMethod selectAllCheckBoxes() {
        allInformationcheckbox.click();
        customerCheckBox.click();
        infoCheckBox.click();
        insuredCheckBox.click();
        createInsuranceButton.click();
        return new PaymentMethod(driver);
    }


}
