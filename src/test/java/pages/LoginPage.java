package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for login form elements
    private final By emailInput = By.cssSelector("input[type='email']");
    private final By passwordInput = By.cssSelector("input[type='password']");
    private final By loginButton = By.cssSelector("button[type='submit']");

    // Sidebar menu → Favorites link after login
    // NOTE: real HTML uses "#!/favorites", not "#/favorites"
    private final By favoritesMenuItem = By.cssSelector("a[href='#!/favorites']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Logs into the Koel application and waits until the main page is ready.
     *
     * @param email user email
     * @param password user password
     * @return MainPage object
     */
    public MainPage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        // Wait for the Favorites link in the left sidebar — confirms login success
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoritesMenuItem));

        return new MainPage(driver);
    }
}

