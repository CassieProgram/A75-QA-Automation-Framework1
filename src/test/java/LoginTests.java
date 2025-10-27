import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.sql.DriverManager.getDriver;

public class LoginTests extends BaseTest  {

    private final By email = By.id("email");
    private final By password = By.id("password");
    private final By submit = By.cssSelector("button[type='submit']");
    private final By errorBanner = By.cssSelector("[data-test='login-error'], .error, .alert-danger");
    private final By dashboardMarker = By.cssSelector("[data-test='dashboard'], .dashboard, .user-avatar");

    @Test
    public void loginPositive_correctCreds () {
        getDriver().findElement(email).sendKeys("shynar@testpro.io");
        getDriver().findElement(password).sendKeys("Javashynar890@");
        getDriver().findElement(submit).click();
        Assert.assertTrue(getDriver().findElement(dashboardMarker).isDisplayed(), "Dashboard should appear.");
    }


}

