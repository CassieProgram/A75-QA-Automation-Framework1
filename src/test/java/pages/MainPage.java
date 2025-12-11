package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // All song “cards” on the page (Most Played, Recently Played, etc.)
    // Uses stable data-test attribute provided by the app.
    private final By songRows = By.cssSelector("article[data-test='song-card']");

    // Heart icon inside a song card – works for both liked and unliked states.
    private final By heartIconSelector =
            By.cssSelector("i[data-test='btn-like-unliked'], i[data-test='btn-like-liked']");

    // Left sidebar link for Favorites playlist.
    private final By favoritesMenuItem = By.cssSelector("a[href='#!/favorites']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifies that there are at least two songs visible on the main page.
     * This is used as a precondition before marking songs as favorites.
     */
    public void assertAtLeastTwoSongsVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(songRows));
        int count = driver.findElements(songRows).size();
        Assert.assertTrue(count >= 2, "Expected at least 2 songs but found " + count);
    }

    /**
     * Marks a song as favorite by its index in the list of song cards.
     *
     * @param index 0-based index of the song card (0 = first song, 1 = second, etc.)
     */
    public void markSongAsFavorite(int index) {
        List<WebElement> rows = driver.findElements(songRows);
        WebElement row = rows.get(index);

        WebElement heartIcon = row.findElement(heartIconSelector);
        heartIcon.click();
    }

    /**
     * Navigates to the Favorites playlist via the sidebar.
     *
     * @return a new FavoritesPage object
     */
    public FavoritesPage goToFavorites() {
        wait.until(ExpectedConditions.elementToBeClickable(favoritesMenuItem)).click();
        return new FavoritesPage(driver);
    }
}

