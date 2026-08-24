import org.testng.annotations.Test;
import pages.PlaylistPage;
import org.testng.Assert;
import utils.DatabaseHelper;
import pages.LoginPage;



public class CreatePlaylistTest extends BaseTest {
    src\test\java\CreatePlaylistTest.java

    @Test
    public void verifyUserCanCreatePlaylistWithValidName() {
        LoginPage loginPage = new LoginPage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());

        String playlistName = "PL" + System.currentTimeMillis() % 100000000;

        String userEmail = System.getenv("KOEL_EMAIL");
        String currentPassword = System.getenv("KOEL_CURRENT_PASSWORD");

        Assert.assertNotNull(userEmail, "KOEL_EMAIL environment variable is missing.");
        Assert.assertNotNull(currentPassword, "KOEL_CURRENT_PASSWORD environment variable is missing.");
        loginPage.login(userEmail, currentPassword);
        Assert.assertTrue(loginPage.isLoggedIn(), "User should be logged in before creating playlist.");
        playlistPage.createPlaylist(playlistName);
        Assert.assertTrue(
                playlistPage.isPlaylistDisplayed(playlistName),
                "Playlist should be displayed in the sidebar after creation"
        );
        Assert.assertTrue(
                DatabaseHelper.isPlaylistSaved(playlistName, userEmail),
                "Playlist should be saved in the database."
        );
    }
}

