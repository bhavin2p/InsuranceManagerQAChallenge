package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumCommonMethods {

    static WebDriver driver;

    public SeleniumCommonMethods(WebDriver driver){
        this.driver = driver;
    }

    public static void switchToFrameUsingIndex(int index, WebDriver driver){
        driver.switchTo().frame(index);
    }

    public static void switchToFrameUsingName(String name, WebDriver driver){
        WebElement usingName = driver.findElement(By.name("name"));
        driver.switchTo().frame(usingName);
    }

}
