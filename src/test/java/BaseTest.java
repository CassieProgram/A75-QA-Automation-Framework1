import factory.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    protected static WebDriver getDriver() {
        return TL.get();
    }

    private static void setDriver(WebDriver d) {
        TL.set(d);
    }

    private static void unloadDriver() {
        TL.remove();
    }

    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;
    protected Actions actions;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"BaseURL", "Browser", "Target"})
    public void setupBrowser(String baseURL, String browser, String target) throws Exception {
        if (target != null && !target.isBlank()) {
            System.setProperty("target", target);
        }

        WebDriver driver = BrowserFactory.create(browser);
        setDriver(driver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));
        actions = new Actions(getDriver());

        getDriver().get(baseURL);

        var links = getDriver().findElements(By.linkText("Log in"));
        if (!links.isEmpty() && links.get(0).isDisplayed()) {
            links.get(0).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='email']")
        ));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        try {
            WebDriver d = getDriver();

            if ("cloud".equalsIgnoreCase(System.getProperty("target"))) {
                if (d instanceof JavascriptExecutor js) {
                    js.executeScript("lambda-status=" + (result.isSuccess() ? "passed" : "failed"));
                }
            }

            if (d != null) {
                d.quit();
            }
        } finally {
            unloadDriver();
        }
    }
}