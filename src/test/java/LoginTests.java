import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() {

        driver.get("https://qa.koel.app/");
        provideEmail("shynar.largess@testpro.io");
        providePassword("Javashynar89!");
        clickSubmitBtn();

        // TODO (for students): Review the configuration as part of HW15
// Wait for an element that shows you're logged in (like the search box)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean loggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='search']"))).isDisplayed();

        Assert.assertTrue(loggedIn, "User should be logged in and see the search box!");
    }
    }

