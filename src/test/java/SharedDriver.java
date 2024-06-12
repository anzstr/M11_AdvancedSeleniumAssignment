import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class SharedDriver {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("lang=en-US");
//            options.setBrowserVersion("stable");
//            ChromeDriverService service =
//                    new ChromeDriverService.Builder().withBuildCheckDisabled(true).build();
            webDriver = new ChromeDriver(options);
//            webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    public static void closerDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
