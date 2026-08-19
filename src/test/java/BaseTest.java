import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

<<<<<<< Updated upstream
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
}
=======
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    protected static WebDriver getDriver() { return TL.get(); }
    private static void setDriver(WebDriver d) { TL.set(d); }
    private static void unloadDriver() { TL.remove(); }

    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;
    protected Actions actions;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"BaseURL","Browser","Target"})
    public void setupBrowser(String baseURL, String browser, String target) throws Exception {
        // Let BrowserFactory / pickBrowser know where to run
        if (target != null && !target.isBlank()) System.setProperty("target", target); // "local" or "cloud"

        // If you built BrowserFactory.create(browser), use that; else call pickBrowser(browser) below
        WebDriver driver = BrowserFactory.create(browser);
        // WebDriver driver = pickBrowser(browser); // <- use this if you don’t have BrowserFactory

        setDriver(driver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));
        actions = new Actions(getDriver());

        getDriver().get(baseURL);
        // If a landing page shows first, click "Log in"
        var links = getDriver().findElements(By.linkText("Log in"));
        if (!links.isEmpty() && links.get(0).isDisplayed()) {
            links.get(0).click();
        }

// Wait for the login form's email field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
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
            if (d != null) d.quit();         // quit, not close
        } finally {
            unloadDriver();                  // clear ThreadLocal
        }
    }
}

>>>>>>> Stashed changes
