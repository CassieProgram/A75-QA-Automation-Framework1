import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;
@Test
public class Homework23RenamePlaylist extends BaseTest {

    public  void  renamePlaylist ()  {
        String newName = "Sample Edited Playlist";
        String expected = "Updated playlist \"" + newName +".\"";

        //page factory and fluent interface

        HomePage home = new LoginPage(driver)
                .email("shynar@testpro.io")
                .password("Javashynar890@")
                .submit();

        home.openRename()
                .renameTo(newName);

        Assert.assertEquals(home.toastMsg(),expected);
        Assert.assertEquals(home.playlistName(),newName);
    }
}
