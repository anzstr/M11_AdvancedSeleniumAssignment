import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import java.util.List;
import java.util.stream.Stream;

import java.time.Duration;
//import java.util.concurrent.TimeUnit;

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
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void monthTest(String monthInputName, String monthInputValue) {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title='Month']")));

        driver.findElement(By.xpath("//*[@title='Month']")).click();
        driver.findElement(By.xpath("//*[text() = '" + monthInputName + "']")).click();
        String monthValue = driver.findElement(By.xpath("//*[@title='Month']")).getAttribute("value");

        assertEquals(monthInputValue, monthValue);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("Jan", "1"),
                Arguments.of("Feb", "2"),
                Arguments.of("Mar", "3"),
                Arguments.of("Apr", "4"),
                Arguments.of("May", "5"),
                Arguments.of("Jun", "6"),
                Arguments.of("Jul", "7"),
                Arguments.of("Aug", "8"),
                Arguments.of("Sep", "9"),
                Arguments.of("Oct", "10"),
                Arguments.of("Nov", "11"),
                Arguments.of("Dec", "12")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020", "2024"})
    public void yearTestParametrized(String yearInput) {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title='Year']")));

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() = '" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals(yearInput, yearValue);
    }

    @Test
    public void gendersTest() {
        String femaleXpath = "//*[text()='Female']//following-sibling::*[@type='radio']";
        String maleXpath = "//*[text()='Male']//following-sibling::*[@type='radio']";
        String customXpath = "//*[text()='Custom']//following-sibling::*[@type='radio']";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(femaleXpath)));

        //verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        //verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);

        //verify custom gender is checked
        WebElement customButton = driver.findElement(By.xpath(customXpath));
        customButton.click();
        String isCustomChecked = driver.findElement(By.xpath(customXpath)).getAttribute("checked");
        assertNotNull(isCustomChecked);
        assertEquals("true", isCustomChecked);
    }

    @Test
    public void termsLinkTest() {
        String termsXPath = "//a[text()='Terms' and @id='terms-link']";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(termsXPath))).click();

        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        assertEquals("https://www.facebook.com/legal/terms/update", driver.getCurrentUrl());
    }

    @Test
    public void policyLinksTest() {
        String policyXPath = "//a[text()='Privacy Policy' and @id='privacy-link']";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(policyXPath))).click();

        for (String str : driver.getWindowHandles()) {
            driver.switchTo().window(str);
        }
        assertEquals("https://www.facebook.com/privacy/policy/?entry_point=data_policy_redirect&entry=0", driver.getCurrentUrl());
    }
}
