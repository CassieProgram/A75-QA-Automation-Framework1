package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlaylistPage {
    private final WebDriver driver;

    private final By createPlaylistButton =
            By.cssSelector("[data-testid='sidebar-create-playlist-btn']");

    private final By newPlaylistOption =
            By.cssSelector("[data-testid='playlist-context-menu-create-simple']");

    private final By playlistNameField =
            By.cssSelector("form[name='create-simple-playlist-form'] input[name='name']");

    public PlaylistPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createPlaylist(String playlistName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(createPlaylistButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(newPlaylistOption)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(playlistNameField))
                .sendKeys(playlistName + Keys.ENTER);
    }

    public boolean isPlaylistDisplayed(String playlistName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By playlistNameInSidebar = By.xpath(
                "//section[@id='playlists']//li[normalize-space()='" + playlistName + "']"
        );

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(playlistNameInSidebar));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
