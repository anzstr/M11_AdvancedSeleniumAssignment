import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
//import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DavikSiteTest {
    private static final String HOME_PAGE_URL = "https://www.daviktapes.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.manage().window().maximize();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closerDriver();
    }

    @AfterEach
    public void testTeardown() {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void companyTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Company']")));

        WebElement element = driver.findElement(By.xpath("//a[text()='Company']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void productsTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Products']")));

        WebElement element = driver.findElement(By.xpath("//a[text()='Products']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void industriesTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Industries']")));

        WebElement element = driver.findElement(By.xpath("//a[text()='Industries']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void knowledgeCenterTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Knowledge Center']")));

        WebElement element = driver.findElement(By.xpath("//a[text()='Knowledge Center']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void contactTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='CONTACT']")));

        WebElement element = driver.findElement(By.xpath("//a[text()='CONTACT']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

}
