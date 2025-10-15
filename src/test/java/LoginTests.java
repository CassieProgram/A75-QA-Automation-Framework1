import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailPassword() {
        LoginPage loginPage = new LoginPage(BaseTest.driver);
        HomePage homePage = new HomePage(BaseTest.driver);

        loginPage.provideEmail("shynar@testpro.io");
        loginPage.providePassword("Javashynar890@");
        loginPage.clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());


    }
}
