import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class UpdatePasswordTest extends BaseTest {

    // Login page locators
    private final By emailField    = By.cssSelector("input[placeholder='Email Address']");
    private final By passwordField = By.cssSelector("input[placeholder='Password']");
    private final By submitButton  = By.cssSelector("button[type='submit']");

    // Top bar - profile link
    private final By profileLink = By.cssSelector("[data-testid='view-profile-link']");

    // Profile page - password fields
    private final By currentPasswordField = By.cssSelector("input[name='current_password']");
    private final By newPasswordField     = By.cssSelector("input[name='new_password']");

    // Profile page - save button (the one you confirmed!)
    private final By saveButton = By.cssSelector(
            "section#profileWrapper form[data-testid='update-profile-form'] button[type='submit']"
    );

    @Test
    public void updatePasswordValidData() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        // 🔐 your real credentials here:
        String userEmail = "shynar.largess+koel1@testpro.io";   // <-- change if needed
        String oldPass   = "TESTPASSWORD123!QQ";                // <-- current password
        String newPass   = "NewPassword123!qq";                 // <-- new password to set

        // 1️⃣ LOGIN
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField))
                .sendKeys(userEmail);

        getDriver().findElement(passwordField).sendKeys(oldPass);
        getDriver().findElement(submitButton).click();

        // 2️⃣ CLICK PROFILE (avatar / 'student' link)
        WebElement profile = wait.until(
                ExpectedConditions.elementToBeClickable(profileLink)
        );
        profile.click();

        // 3️⃣ FILL OLD + NEW PASSWORD
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPasswordField))
                .sendKeys(oldPass);

        getDriver().findElement(newPasswordField).sendKeys(newPass);

        // 4️⃣ CLICK SAVE
        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );
        save.click();


    }
}


