package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField =
            By.cssSelector("input[type='email']");

    private final By passwordField =
            By.cssSelector("input[type='password']");

    private final By submitButton =
            By.cssSelector("button[type='submit']");

    private final By profileLink =
            By.cssSelector("[data-testid='view-profile-link']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));

        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public boolean isLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(profileLink));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
