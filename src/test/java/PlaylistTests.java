import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

public class PlaylistTests extends BaseTest{
    // fluent interface example from HW22
@Ignore
    @Test

    public void renamePlaylist() {

        String playlistName = "Test Pro Edited Playlist 2";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail("shynar@testpro.io")
   //     .providePassword("Javashynar890@")
        .clickSubmit();
homePage.hoverPlay();
      //  homePage.doubleClickPlaylist();
   //             .enterNewPlaylistName(playlistName);
        //changed the approach for assert from HW22

        Assert.assertEquals(homePage.getPlaylistName(), playlistName);
    }
}
