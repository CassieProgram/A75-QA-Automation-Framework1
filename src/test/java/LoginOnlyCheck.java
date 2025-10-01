

    import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

    public class LoginOnlyCheck extends BaseTest {

        @Test
        public void canLogIn() {

            navigateToURL();                 // 1 open login page

            provideEmail("shynar.largess@testpro.io");   // 2 type creds
            providePassword("Javashynar89!");

            clickSubmitBtn();                // 3 now the button is enabled → click

            waitForHome();                   // 4 verify that we really reached home
        }

        private void clickSubmitBtn() {
        }

        private void providePassword(String s) {
        }

        private void provideEmail(String mail) {
        }

        private void navigateToURL() {
        }

        private void waitForHome() {


        }
    }
