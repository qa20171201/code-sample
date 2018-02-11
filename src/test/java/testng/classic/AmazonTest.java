package testng.classic;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Pattern;
        import java.util.concurrent.TimeUnit;
        import org.testng.annotations.*;
        import static org.testng.Assert.*;
        import org.openqa.selenium.*;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.support.ui.Select;

public class AmazonTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        String os = System.getProperty("os.name");
        if (os.equalsIgnoreCase("Mac OS X"))
            System.setProperty("webdriver.chome.driver", "src/test/resources/drivers/chromedriver");
        else System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        //driver = new FirefoxDriver();
        baseUrl = "https://www.amazon.ca/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTcTestngAmazon2() throws Exception {
        driver.get(baseUrl + "/");
        driver.manage().window().maximize();
        driver.findElement(By.id("twotabsearchtextbox")).click();
        driver.findElement(By.id("searchDropdownBox")).click();
        new Select(driver.findElement(By.id("searchDropdownBox"))).selectByVisibleText("Books");
        driver.findElement(By.cssSelector("option[value=\"search-alias=stripbooks\"]")).click();
        driver.findElement(By.id("twotabsearchtextbox")).clear();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("automation");
        //driver.findElement(By.cssSelector("input.nav-input.firepath-matching-node")).click();
        driver.findElement(By.xpath(".//input[@value='Go']")).click();
        assertEquals(driver.getTitle(), "Amazon.ca: automation: Books");
        assertEquals(driver.findElement(By.xpath(".//*[@id='bcKwText']/span")).getText(), "\"automation\"");
        assertTrue(isElementPresent(By.id("bcNewKw")));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
