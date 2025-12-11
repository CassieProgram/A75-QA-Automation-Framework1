package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    // Thread-safe driver holder
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    protected static WebDriver getDriver() {
        return TL.get();
    }

    private static void setDriver(WebDriver driver) {
        TL.set(driver);
    }

    private static void unloadDriver() {
        TL.remove();
    }

    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;
    protected Actions actions;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"BaseURL", "Browser"})
    public void setupBrowser(
            @Optional("https://qa.koel.app/") String baseURL,
            @Optional("chrome") String browser
    ) {
        System.out.println(">>> setupBrowser");
        System.out.println("BaseURL = " + baseURL);
        System.out.println("Browser = " + browser);

        // 🔧 Workaround for Selenium 4.5 + new Chrome
        //System.setProperty("webdriver.http.factory", "jdk-http-client");

        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
        }

        setDriver(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));
        actions = new Actions(driver);

        // 🚀 Actually navigate to Koel
        driver.get(baseURL);
        System.out.println("Current URL after get() = " + driver.getCurrentUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        try {
            WebDriver d = getDriver();
            // if (d != null) {
            // if (d instanceof JavascriptExecutor js) {
            // for LambdaTest / cloud, harmless locally
            //    js.executeScript("lambda-status=" + (result.isSuccess() ? "passed" : "failed"));

            if (d != null) {
                d.quit();
            }

        } finally {
            unloadDriver();
        }
    }
}