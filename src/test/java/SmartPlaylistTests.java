import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SmartPlaylistPage;

import java.util.Collections;
import java.util.Set;

public class SmartPlaylistTests extends BaseTest {
    private SmartPlaylistPage smartPlaylistPage;

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                requiredEnv("KOEL_EMAIL"),
                requiredEnv("KOEL_PASSWORD")
        );

        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "The QA user should be logged in"
        );

        smartPlaylistPage = new SmartPlaylistPage(driver);
    }

    @Test
    public void tc001SmartPlaylistCanBeCreatedWithOneMatchingRule() {
        String playlistName = "Smart Playlist One Rule";
        String songTitle = "Pluto";

        Set<String> existingLinks =
                smartPlaylistPage.getPlaylistLinksNamed(playlistName);

        smartPlaylistPage.openNewSmartPlaylistDialog();
        smartPlaylistPage.enterName(playlistName);
        smartPlaylistPage.setFirstRule("Title", "is", songTitle);

        Assert.assertEquals(
                smartPlaylistPage.getRuleCount(),
                1,
                "The form should contain exactly one rule"
        );
        Assert.assertTrue(
                smartPlaylistPage.firstRuleMatches("Title", "is", songTitle),
                "The single rule should be Title is Pluto"
        );

        smartPlaylistPage.save();

        String newPlaylistLink =
                smartPlaylistPage.openNewPlaylist(playlistName, existingLinks);

        Assert.assertTrue(
                smartPlaylistPage.isPlaylistOpen(newPlaylistLink),
                "The newly created Smart Playlist should be open"
        );
        Assert.assertEquals(
                smartPlaylistPage.getDisplayedSongTitles(),
                Collections.singletonList(songTitle),
                "The new Smart Playlist should display only Pluto"
        );
    }
}
