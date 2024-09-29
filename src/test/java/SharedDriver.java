import org.openqa.selenium.WebDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

//import java.time.Duration;
//import java.util.concurrent.TimeUnit;

public class SharedDriver {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
//            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("lang=en-US");
//            options.setBrowserVersion("stable");
//            ChromeDriverService service =
//                    new ChromeDriverService.Builder().withBuildCheckDisabled(true).build();
            webDriver = new ChromeDriver(options);
//            webDriver = new ChromeDriver();
//            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        return webDriver;
    }

    public static void closerDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
