import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArtistsPage;
import pages.LoginPage;
import pages.PlayerBar;

import java.util.List;

public class ArtistsTests extends BaseTest {

    private ArtistsPage artistsPage;

    @BeforeMethod
    public void loginAndOpenArtists() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                requiredEnv("KOEL_EMAIL"),
                requiredEnv("KOEL_PASSWORD")
        );

        artistsPage = new ArtistsPage(driver);
        artistsPage.open();
    }

    @Test
    public void tc001ArtistsAreDisplayed() {

        Assert.assertEquals(
                artistsPage.getHeading(),
                "Artists",
                "The Artists page should be displayed"
        );

        List<String> names = artistsPage.getVisibleArtistNames();

        Assert.assertTrue(
                names.size() >= 2,
                "Multiple artists should be displayed"
        );

        Assert.assertTrue(
                names.contains("AKMV-18"),
                "AKMV-18 should be visible"
        );

        Assert.assertTrue(
                names.contains("BoxCat Games"),
                "BoxCat Games should be visible"
        );
    }

    @Test
    public void tc002SearchResultsShowMatchingArtistName() {

        String artistName = "BoxCat Games";

        artistsPage.searchForArtist(artistName);

        Assert.assertTrue(
                artistsPage.isArtistNameShownInSearchResults(artistName),
                "Search results should display the artist name: " + artistName
        );
    }

    @Test
    public void tc003SongCanBePlayedForSelectedArtist() {

        String artistName = "BoxCat Games";
        String songTitle = "Epic Song";

        // Step 1-3 are handled by BaseTest + @BeforeMethod

        // Step 4-5: locate and select the known artist
        artistsPage.selectArtist(artistName);

        // Step 6-7: verify the known song is displayed
        Assert.assertTrue(
                artistsPage.isSongAvailable(songTitle),
                songTitle + " should be listed for " + artistName
        );

        // Step 8: start playback
        artistsPage.playSong(songTitle);

        // Step 9: verify the song row shows playing state
        Assert.assertTrue(
                artistsPage.isSongRowPlaying(songTitle),
                songTitle + " should be marked as playing"
        );

        // Verify the player indicates active playback
        PlayerBar playerBar = new PlayerBar(driver);

        Assert.assertTrue(
                playerBar.indicatesPlayback(),
                "The player should indicate active playback"
        );
    }
}