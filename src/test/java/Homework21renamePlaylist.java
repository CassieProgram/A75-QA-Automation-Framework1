import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore

public class Homework21renamePlaylist extends BaseTest{
   //Prerequisite - at least one user -created playlist
    String newPlaylistName = " Sample Edited Playlist";
@Ignore
    @Test

    public void renamePlaylist(){

        String updatePlaylistMsg = "Updated playlist\"Sample Edited Playlist.\"";

        provideEmail("shynar@testpro.io");
        providePassword("Javashynar890@");
        clickSubmit();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertEquals(getRenamePlaylistSuccessMsg(),updatePlaylistMsg);
    }



    public void doubleClickPlaylist(){
        WebElement playlistElement = BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();

    }
public void enterNewPlaylistName() {
        WebElement playlistInputField = BaseTest.wait.until (ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A", Keys.BACK_SPACE));

        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);

}
public String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
}
}
