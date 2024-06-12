import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FacebookSignupPageTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
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
    public void gendersTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);
    }

    @Test
    public void errorMessageTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@aria-label='Mobile number or email']")).click();
        WebElement errorMsgPhEm = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgPhEm);

        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        WebElement errorMsgFirstN = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgFirstN);

        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        WebElement errorMsgLastN = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgLastN);

        driver.findElement(By.id("month")).click();
        WebElement errorMsgMonthWind = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgMonthWind);

        driver.findElement(By.xpath("//*[@aria-label='Day']")).click();
        WebElement errorMsgDayWind = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgDayWind);

        driver.findElement(By.xpath("//*[@aria-label='Year']")).click();
        WebElement errorMsgYearWind = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgYearWind);

        driver.findElement(By.xpath("//*[@name='sex' and @value=-1]")).click();
        driver.findElement(By.xpath("//*[@name='preferred_pronoun']")).click();
        WebElement errorMsgNoSex = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(errorMsgNoSex);

        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).click();
        WebElement errorMsgPassword = driver.findElement(By.xpath("//*[contains(text(), 'combination')]"));
        assertNotNull(errorMsgPassword);

        Thread.sleep(3000);
    }

}
