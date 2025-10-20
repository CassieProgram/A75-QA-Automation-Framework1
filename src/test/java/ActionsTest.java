import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.LoginPage;


public class ActionsTest extends  BaseTest {

    @Test

    public void playSong() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongs = new AllSongsPage(driver);

        loginPage.provideEmail("shynar@testpro.io");
        loginPage.providePassword("Javashynar89!");
        loginPage.clickSubmit();
        homePage.chooseAllSongsList();
        allSongs.contextClickFirstSong();
        allSongs.choosePlayOption();
        Assert.assertTrue(allSongs.isSongPlaying());
    }
}
