package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// you can add FirefoxDriver/EdgeDriver later if needed

public class BrowserFactory {

    // SUPER simple: only local browsers, no grid
    public static WebDriver create(String browser) {

        if (browser == null) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "chrome":
            default:
                // WebDriverManager downloads/sets up chromedriver automatically
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
}
