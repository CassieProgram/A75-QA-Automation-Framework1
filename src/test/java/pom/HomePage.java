package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage (WebDriver givenDriver) {
        super(givenDriver);
    }
By playButton= By.cssSelector(".fa.fa-bars");
    By firstPlaylist = By.cssSelector(".playlist:nth-child(3)");
    By playlistNameField = By.cssSelector("[name='name']");
    By userAvatarIcon = By.cssSelector("img.avatar");
    public WebElement getUserAvatar () {
        return findElement (userAvatarIcon);
        By renamePlaylistSuccessMsg = By.cssSelector("div.success.show");
        By playButton = By.cssSelector(".fa.fa-bars");
        public void doubleClickPlaylist() {
            doubleClick(firstPlaylist);
        }

        public void enterNewPlaylistName (String playlistName) {
            findElement(playlistNameField).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.BACK_SPACE));
            findElement(playlistNameField).sendKeys(enterNewPlaylistName);
            findElement(playlistNameField).sendKeys(Keys.ENTER);
        }

        public String getRenamePlaylistSuccessMsg() {
            return findElement(renamePlaylistSuccessMsg).getText();
        }
        public void hoverPlay() {
           hover(playButton);

        }
    }
}
