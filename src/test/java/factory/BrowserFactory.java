package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class BrowserFactory {

    private static final String GRID_URL = "http://192.168.1.152:4444";

    public static WebDriver createChrome() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            WebDriver driver = new RemoteWebDriver(
                    new URL(GRID_URL), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            return driver;

        } catch (Exception exception) {
            throw new RuntimeException("Could not start remote Chrome", exception);
        }
        }
    }

