package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InsuranceDetailsPage {

    WebDriver driver;

    public InsuranceDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h2/span")
    WebElement headerOfDetailPage;


    public String getHeader(){
        return headerOfDetailPage.getText();
    }

}
