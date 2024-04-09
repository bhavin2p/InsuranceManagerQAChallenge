package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    static WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div/button/img")
    WebElement checkLang;

    @FindBy(xpath = "//div/button/img/preceding-sibling::span")
    WebElement selectLangDropdown;

    @FindBy(xpath = "//div/ul/li/span[contains(text(),'Englisch')]")
    WebElement selectLangEnglish;

    @FindBy(id = "login_username")
    WebElement un;

    @FindBy(name = "_password")
    WebElement pw;

    @FindBy(xpath = "//input[@value='rememberMe']")
    WebElement rememberMe;

    @FindBy(xpath = "//button/span[contains(text(),'Next')]")
    WebElement nxtBtn;

    public void enterUsername(String username){
        un.sendKeys(username);
    }

    public void enterPassword(String password){
        pw.sendKeys(password);
    }

    public void checkRememberMe(){
        rememberMe.click();
    }

    public void clickOnNextButton(){
        nxtBtn.click();
    }

    public void selectEngLang(){
        if(!checkLang.getAttribute("alt").equalsIgnoreCase("English")){
            selectLangDropdown.click();
            selectLangEnglish.click();
        }
    }

    public HomePage loginPortal(String username, String password){
        selectEngLang();
        enterUsername(username);
        enterPassword(password);
        checkRememberMe();
        clickOnNextButton();
        return new HomePage(driver);
    }







}
