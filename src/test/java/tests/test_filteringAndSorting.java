package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class test_filteringAndSorting extends BaseSetupFile{

    static Logger log = Logger.getLogger(test_filteringAndSorting.class);
    String searchFilterValue = "200000417918";
    String searchFilterValueByEmail = "test@gmail.com";

    @Test(dataProvider = "loginDetails")
    public void verifyFilteringByPolicyNumber(String uName, String uPass) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully to validate Filtering");
        home.clickOnSearchForDropdown();
        home.selectSearchBy("policy");
        log.info("Filtered by policy selected.");
        home.enterValueForSearchFilter(searchFilterValue);
        List<WebElement> policies = home.getPoliciesDetails();
        String policyNumber = home.getPolicyNumberDetails(policies.get(1));
        Assert.assertEquals(searchFilterValue, policyNumber);
        log.info("policy verification successful.");
    }

    @Test(dataProvider = "loginDetails")
    public void shouldVerifyFilteringByEmail(String uName, String uPass) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully to validate Filtering");
        home.clickOnSearchForDropdown();
        home.selectSearchBy("email");
        log.info("Filtered by email selected.");
        home.enterValueForSearchFilter(searchFilterValueByEmail);
        List<WebElement> policies = home.getPoliciesDetails();
        for (int i = 1; i < policies.size(); i++) {
            String policyEmail = home.getPolicyEmailDetails(policies.get(i));
            Assert.assertEquals(searchFilterValue, policyEmail);
            log.info("email filtering verification successful.");
        }
    }

    @Test(dataProvider = "loginDetails")
    public void shouldVerifyFilteringByNewerThanDate(String uName, String uPass) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully to validate Filtering");
        home.clickOnSearchForDropdown();
        home.selectSearchBy("newer");
        log.info("Filtered by Newer Records selected.");
        String searchFilterValue = "2024-04-10";
        home.enterValueForSearchFilter(searchFilterValue);
        List<WebElement> policies = home.getPoliciesDetails();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ENGLISH);
        LocalDate filterDate = LocalDate.parse("10/04/2024", formatter);
        for (int i = 1; i < policies.size(); i++) {
            List<WebElement> cells = policies.get(i).findElements(By.tagName("th"));
            WebElement activationDate = cells.get(3).findElement(By.tagName("span"));
            LocalDate localDate = LocalDate.parse(activationDate.getText(), formatter);
            Assert.assertTrue(localDate.isAfter(filterDate));
            log.info("newer record filter verification successful.");
        }
    }

    @Test(dataProvider = "loginDetails")
    public void shouldVerifyFilteringByOlderThanDate(String uName, String uPass) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully to validate Filtering");
        home.clickOnSearchForDropdown();
        home.selectSearchBy("older");
        log.info("Filtered by Older Records selected.");
        String searchFilterValue = "2024-04-10";
        home.enterValueForSearchFilter(searchFilterValue);
        List<WebElement> policies = home.getPoliciesDetails();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ENGLISH);
        LocalDate filterDate = LocalDate.parse("10. April 2024", formatter);
        for (int i = 1; i < policies.size(); i++) {
            List<WebElement> cells = policies.get(i).findElements(By.tagName("th"));
            WebElement activationDate = cells.get(4).findElement(By.xpath("span"));
            LocalDate localDate = LocalDate.parse(activationDate.getText(), formatter);
            Assert.assertTrue(localDate.isBefore(filterDate));
            log.info("older records verification successful.");
        }
    }

    @Test(dataProvider = "loginDetails")
    public void shouldVerifySortingByPolicyNumber(String uName, String uPass) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully to validate sorting");
        WebElement policyNumberSort = home.filteredTable;
        policyNumberSort.click();
        log.info("Policy Number Column Sort clicked..");
        List<WebElement> rows = policyNumberSort.findElements(By.tagName("tr"));
        WebElement headerRow = rows.get(0);
        List<WebElement> firstRowCellsBefore = rows.get(1).findElements(By.tagName("th"));
        String policyNumberBeforeSorting = firstRowCellsBefore.get(0).getText();
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        WebElement policyNumberHeader = headerCells.get(0);
        WebElement sortIcon = policyNumberHeader.findElement(By.tagName("svg"));
        Actions actions = new Actions(driver);
        actions.moveToElement(sortIcon).click().perform();
        WebElement table = home.filteredTable;
        List<WebElement> rowsAfter = table.findElements(By.tagName("tr"));
        List<WebElement> firstRowCellsAfter = rowsAfter.get(1).findElements(By.tagName("th"));
        String policeNumberAfterSorting = firstRowCellsAfter.get(0).getText();

        Assert.assertTrue(Long.parseLong(policeNumberAfterSorting) < Long.parseLong(policyNumberBeforeSorting));
        log.info("Sorting Assertion validated successful.");
    }

    @Test(dataProvider = "loginDetails")
    public void shouldVerifyActivationDateSorting(String uName, String uPass) {
        LoginPage login = new LoginPage(driver);
        HomePage home = login.loginPortal(uName, uPass);
        log.info("Login Successfully to validate sorting");
        WebElement policyTable = home.filteredTable;
        policyTable.click();
        log.info("Policy Number Column Sort clicked..");
        List<WebElement> rows = policyTable.findElements(By.tagName("tr"));
        WebElement headerRow = rows.get(0);

        List<WebElement> firstRowCellsBefore = rows.get(1).findElements(By.tagName("th"));
        String activationDateBeforeSorting = firstRowCellsBefore.get(3).getText();
        System.out.println(activationDateBeforeSorting);

        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        WebElement activationDateHeader = headerCells.get(3);
        WebElement sortIcon = activationDateHeader.findElement(By.tagName("svg")).findElement(By.tagName("path"));
        Actions actions = new Actions(driver);
        actions.moveToElement(sortIcon).click().perform();

        WebElement table = home.filteredTable;

        List<WebElement> rowsAfter = table.findElements(By.tagName("tr"));
        List<WebElement> firstRowCellsAfter = rowsAfter.get(1).findElements(By.tagName("th"));
        String activationDateAfterSorting = firstRowCellsAfter.get(3).getText();
        System.out.println(activationDateAfterSorting);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ENGLISH);
        LocalDate before = LocalDate.parse(activationDateBeforeSorting, formatter);
        LocalDate after = LocalDate.parse(activationDateAfterSorting, formatter);

        Assert.assertTrue(after.isEqual(before)  || after.isAfter(before));
        log.info("Sorting Assertion validated successful.");
    }

}
