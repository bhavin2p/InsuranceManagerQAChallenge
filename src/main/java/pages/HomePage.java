package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.newTabSubPages.ProductViewPage;

import java.util.List;

public class HomePage {
    static WebDriver driver;
    Actions actions;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'List')]")
    WebElement listLink;

    @FindBy(xpath = "//span[contains(text(),'New')]")
    WebElement newLink;

    @FindBy(xpath = "//span[contains(text(),'Import')]")
    WebElement importLink;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[3]/div[1]/div/div/div/input")
    WebElement searchForDropdown;

    @FindBy(xpath = "//ul/li/span[contains(text(),'Policy')]")
    WebElement selectSearchByPolicyDropdown;

    @FindBy(xpath = "//ul/li/span[contains(text(),'E-Mail')]")
    WebElement selectSearchByEMailDropdown;

    @FindBy(xpath = "//ul/li/span[contains(text(),'Newer')]")
    WebElement selectSearchByNewerDropdown;

    @FindBy(xpath = "//ul/li/span[contains(text(),'Older')]")
    WebElement selectSearchByOlderDropdown;

    @FindBy(xpath = "/html/body/div[1]/div/div/div[3]/div[2]/div/table")
    public WebElement filteredTable;

    public ProductViewPage clickNewOption() {
        newLink.click();
        return new ProductViewPage(driver);
    }

    public ImportInsurancesPage clickOnImportOption() {
        importLink.click();
        return new ImportInsurancesPage(driver);
    }

    public void clickOnSearchForDropdown() {
        try {
            actions = new Actions(driver);
            actions.moveToElement(searchForDropdown).click().perform();
        } catch (StaleElementReferenceException e) {
            actions = new Actions(driver);
            actions.moveToElement(searchForDropdown).click().perform();
        }
    }

    public void selectSearchBy(String value){
        if(value.equalsIgnoreCase("policy")){
            selectSearchByPolicyDropdown.click();
        } else if(value.equalsIgnoreCase("email")){
            selectSearchByEMailDropdown.click();
        } else if(value.equalsIgnoreCase("newer")){
            selectSearchByNewerDropdown.click();
        } else if(value.equalsIgnoreCase("older")){
            selectSearchByOlderDropdown.click();
        }
    }

    public void enterValueForSearchFilter(String searchFilterValue) {
        actions = new Actions(driver);
        actions.sendKeys(searchForDropdown, searchFilterValue).sendKeys(Keys.ENTER).perform();
    }

    public List<WebElement> getPoliciesDetails() {
        return filteredTable.findElements(By.tagName("tr"));
    }

    public String getPolicyNumberDetails(WebElement policyRow) {
        return policyRow.findElements(By.tagName("th")).get(0).getText();
    }

    public String getPolicyEmailDetails(WebElement policyElement) {
        return policyElement.findElements(By.tagName("th")).get(2).getText();
    }

}
