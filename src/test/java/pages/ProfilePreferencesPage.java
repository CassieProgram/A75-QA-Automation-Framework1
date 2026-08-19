package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePreferencesPage {
    private final WebDriver driver;

    private final By profileLink =
            By.cssSelector("[data-testid='view-profile-link']");

    private final By logoutButton =
            By.cssSelector("[data-testid='btn-logout']");

    private final By currentPasswordField =
            By.cssSelector("[data-testid='update-profile-form'] #inputProfileCurrentPassword");

    private final By newPasswordField =
            By.cssSelector("[data-testid='update-profile-form'] #inputProfileNewPassword");

    private final By saveButton =
            By.cssSelector("[data-testid='update-profile-form'] button[type='submit']");

    private final By successMessage =
            By.xpath("//*[contains(text(), 'Profile updated.')]");

    public ProfilePreferencesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openProfilePreferences() {
        driver.findElement(profileLink).click();
    }

    public void changePassword(String currentPassword, String newPassword) {
        driver.findElement(currentPasswordField).clear();
        driver.findElement(currentPasswordField).sendKeys(currentPassword);

        driver.findElement(newPasswordField).clear();
        driver.findElement(newPasswordField).sendKeys(newPassword);

        driver.findElement(saveButton).click();
    }

    public boolean isProfileUpdatedMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(successMessage));
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}