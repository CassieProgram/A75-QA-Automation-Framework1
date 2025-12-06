package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    public static WebDriver create(String browser) {

        if (browser == null || browser.isBlank()) {
            browser = "chrome";
        }

        browser = browser.toLowerCase();

        switch (browser) {
            case "chrome":
                return createChrome();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver createChrome() {
        // EXACTLY like your working debug test:
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }
}



