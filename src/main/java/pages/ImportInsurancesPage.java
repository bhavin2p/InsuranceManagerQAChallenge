package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class ImportInsurancesPage {

    WebDriver driver;
    WebDriverWait wait;
    public ImportInsurancesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[3]/div/input")
    WebElement browseButton;

    @FindBy(xpath = "//span[contains(text(),'Import')]")
    WebElement importBtn;

    @FindBy(xpath = "//ul/li")
    WebElement getErrorMessage;

    @FindBy(xpath = "//span[contains(text(),'Create insurance')]")
    WebElement createInsuranceButton;


    public void importCSV() {
        String relativeFilePath = "src/test/resources/testdata.csv";
        Path absolutePath = Paths.get(System.getProperty("user.dir"), relativeFilePath);
        String absoluteFilePath = absolutePath.toAbsolutePath().toString();
        browseButton.sendKeys(absoluteFilePath);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        By fileListLocator = By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div[2]/table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileListLocator));
    }

    public void importFile() {
        importCSV();
        createInsuranceButton.click();
    }

    public String getErrorNotificationMessage(){
        return getErrorMessage.getText();
    }

}
