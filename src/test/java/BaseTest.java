import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class BaseTest {
    public static WebDriverWait wait = null;
    public static Actions actions = null;

    private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadDriver.get();
    }


    @BeforeSuite
    static void setupClass() {
        static void setupClass() {

            @BeforeMethod
            @Parameters({"BaseUrl"})
            public void browserLaunch(String BaseURL) throws MalformedURLException{

                threadDriver.set(pickBrowser(System.getProperty("browser")));


                //Preconditions
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                getDriver().manage().window().maximize();
                url = BaseURL;
                navigatetoURL(url);
                actions = new Actions(getDriver());

            }
            public void setupBrowser(String BaseURL) throws MalformedURLException {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = pickBrowser(System.getProperty("browser"));
                //Preconditions
                //driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.manage().window().maximize();
                url = BaseURL;
                navigatetoURL(url);
                actions = new Actions(driver);
            }

            public void navigatetoURL(String url) {
                driver.get("https://qa.koel.app/");
                //driver.get("https://qa.koel.app/");
                getDriver().get(url);
            }

            @AfterMethod
            public void  quitIt(){
                threadDriver.get().close();
                threadDriver.remove();
            }

            public void tearDown() {
                driver.quit();
            }
            public static WebDriver pickBrowser(String browser) throws MalformedURLException
            WebDriverManager.safaridriver().setup();
            return driver = new SafariDriver();

            //Grid Capable
            //Grid Capable
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
           public static WebDriver pickBrowser(String browser) throws MalformedURLException
            caps.setCapability("browserName", "MicrosoftEdge");
            return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);

// Cloud Executions
            case "cloud" :
                return lambdaTest();

            default:
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return driver = new ChromeDriver(chromeOptions);

        }
    }

    public static WebDriver lambdaTest() throws MalformedURLException{
        String hubURL = "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("dev");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "wileygriffin");
        ltOptions.put("accessKey", "LT_iS7htswusuez3M0n6y5KFnTeP4wRoQhyQMksWom2C6XzgfF");
        ltOptions.put("project", "Untitled");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);

        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }


  /*  // deletePlaylist homework19
    public String deletedPlaylistMessage() {
@@ -140,4 +188,4 @@ public void doubleClick() {
        actions.doubleClick(existingPlaylist).perform();
    } */
}
}

