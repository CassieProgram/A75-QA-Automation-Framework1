import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailPassword() {

        HomePage homePage = new LoginPage(driver)

        .email("shynar@testpro.io")
                .password("Javashynar890@")
                .submit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());


    }
}
