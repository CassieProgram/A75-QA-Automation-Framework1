// TODO: put into the right package (e.g. package pages;)
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class FavoritesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ── locators ──────────────────────────────────────────────────────────────
    // table rows with songs on Favorites page
    private final By favoriteRows = By.cssSelector("section#favoritesWrapper tr.song-item");
    // song title cell in the row
    private final By titleCell   = By.cssSelector("td.title");

    public FavoritesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // wait until favorites table is visible
    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoriteRows));
    }

    private List<WebElement> getRows() {
        waitUntilLoaded();
        return driver.findElements(favoriteRows);
    }

    public int getSongsCount() {
        return getRows().size();
    }

    public String getSongTitleByIndex(int index) {
        WebElement row = getRows().get(index);
        return row.findElement(titleCell).getText().trim();
    }

    public List<String> getAllSongTitles() {
        return getRows().stream()
                .map(r -> r.findElement(titleCell).getText().trim())
                .collect(Collectors.toList());
    }

    /** Click heart on given index and wait for row to disappear (un-favorite). */
    public void unlikeSongByIndex(int index) {
        List<WebElement> rows = getRows();
        WebElement row = rows.get(index);

        // heart icon inside the row
        WebElement heart = row.findElement(By.cssSelector("i.fa-heart"));
        heart.click();

        // after unliking, this row disappears from Favorites
        wait.until(ExpectedConditions.stalenessOf(row));
    }

    /** Right-click row and choose "Download" in context menu. */
    public void downloadSongByIndex(int index) {
        WebElement row = getRows().get(index);

        new Actions(driver).contextClick(row).perform();

        By downloadItem = By.xpath("//li[normalize-space()='Download']");
        wait.until(ExpectedConditions.elementToBeClickable(downloadItem)).click();
    }
}

