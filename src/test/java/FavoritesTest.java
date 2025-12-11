import factory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FavoritesTest extends BaseTest {

    @Test
    public void userCanManageFavoritesPlaylist() {
        WebDriver driver = getDriver();

        // ---------- 1. LOGIN ----------
        driver.findElement(By.cssSelector("input[type='email']"))
                .sendKeys("shynar.largess+koel1@testpro.io");
        driver.findElement(By.cssSelector("input[type='password']"))
                .sendKeys("TestPassword!123Q1");
        driver.findElement(By.cssSelector("button[type='submit']"))
                .click();

        // ---------- 2. GO TO ALL SONGS ----------
        By allSongsLink = By.cssSelector("a[href='#!/songs']");
        wait.until(ExpectedConditions.elementToBeClickable(allSongsLink)).click();

        // rows with songs on All Songs page
        By allSongsRows = By.cssSelector("section#songsWrapper tr.song-item");

        // wait until at least one row appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(allSongsRows));

        List<WebElement> songs = driver.findElements(allSongsRows);
        System.out.println("Songs found on All Songs page = " + songs.size());
        Assert.assertTrue(songs.size() > 0, "No songs found on All Songs page");

        // ---------- 3. LIKE 1–2 SONGS (PRECONDITION) ----------
        By likeButtonInRow = By.cssSelector("button[data-test='like-btn']");

        // like first song
        WebElement firstRow = songs.get(0);
        firstRow.findElement(likeButtonInRow).click();

        // like second song if present
        if (songs.size() > 1) {
            WebElement secondRow = songs.get(1);
            secondRow.findElement(likeButtonInRow).click();
        }

        // ---------- 4. GO TO FAVORITES PLAYLIST ----------
        By favoritesLink = By.cssSelector("a[href='#!/favorites']");
        wait.until(ExpectedConditions.elementToBeClickable(favoritesLink)).click();

        By favoritesRows = By.cssSelector("section#favoritesWrapper tr.song-item");
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoritesRows));

        List<WebElement> favorites = driver.findElements(favoritesRows);
        Assert.assertTrue(favorites.size() > 0, "Favorites list is empty");

        // verify we have a non-empty title in the first favorite row
        WebElement firstFavRow = favorites.get(0);
        String favoriteTitle = firstFavRow.findElement(By.cssSelector("td.title")).getText();
        Assert.assertFalse(favoriteTitle.isEmpty(), "Favorite song title is empty");

        // ---------- 5. RIGHT-CLICK FIRST FAVORITE AND CLICK DOWNLOAD ----------
        Actions actions = new Actions(driver);
        actions.contextClick(firstFavRow).perform();

        By downloadOption = By.xpath("//li[normalize-space()='Download']");
        wait.until(ExpectedConditions.elementToBeClickable(downloadOption)).click();

        // (No filesystem check needed for the homework.)
    }
}





