import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePreferencesPage;
import utils.DatabaseHelper;

public class UpdatePasswordTest extends BaseTest {

    @Test
    public void verifyUserCanUpdatePassword() {
        String userEmail = System.getenv("KOEL_EMAIL");
        String currentPassword = System.getenv("KOEL_CURRENT_PASSWORD");
        String newPassword = System.getenv("KOEL_NEW_PASSWORD");

        Assert.assertNotNull(userEmail, "KOEL_EMAIL environment variable is missing.");
        Assert.assertNotNull(currentPassword, "KOEL_CURRENT_PASSWORD environment variable is missing.");
        Assert.assertNotNull(newPassword, "KOEL_NEW_PASSWORD environment variable is missing.");

        String hashBeforeUpdate =
                DatabaseHelper.getPasswordHash(userEmail);

        LoginPage loginPage = new LoginPage(getDriver());
        ProfilePreferencesPage profilePage =
                new ProfilePreferencesPage(getDriver());

        loginPage.login(userEmail, currentPassword);

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User should be logged in with current password."
        );

        profilePage.openProfilePreferences();

        profilePage.changePassword(currentPassword, newPassword);

        Assert.assertTrue(
                profilePage.isProfileUpdatedMessageDisplayed(),
                "Profile updated message should be displayed after changing to new password."
        );

        String hashAfterNewPassword =
                DatabaseHelper.getPasswordHash(userEmail);

        Assert.assertNotEquals(
                hashAfterNewPassword,
                hashBeforeUpdate,
                "Password hash should change in database after password update."
        );

        profilePage.logout();

        loginPage.login(userEmail, newPassword);

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User should be able to log in with new password."
        );

        profilePage.openProfilePreferences();

        profilePage.changePassword(newPassword, currentPassword);

        Assert.assertTrue(
                profilePage.isProfileUpdatedMessageDisplayed(),
                "Profile updated message should be displayed after changing password back."
        );

        String hashAfterChangeBack =
                DatabaseHelper.getPasswordHash(userEmail);

        Assert.assertNotEquals(
                hashAfterChangeBack,
                hashAfterNewPassword,
                "Password hash should change in database after changing password back."
        );

        profilePage.logout();

        loginPage.login(userEmail, currentPassword);

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "User should be able to log in again with original password."
        );
    }
}