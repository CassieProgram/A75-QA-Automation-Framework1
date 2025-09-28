import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework17Test extends BaseTest {

    @Test
    public void addSongToPlaylist() {

        /* ---------- 0  Log in ---------- */
        navigateToURL();
        provideEmail("shynar.largess@testpro.io");
        providePassword("Javashynar89!");
        clickSubmitBtn();
        waitForHome();

        /* ---------- 1  Search ---------- */
        searchSong("dark");
        clickViewAllBtn();
        selectFirstSong();

        /* ---------- 2  Add to playlist ---------- */
        clickAddToBtn();
        String playlist = "checking";                 // <-- your playlist name
        choosePlaylist(playlist);

        /* ---------- 3  Verify toast ---------- */
        String toastText = getToastText();
        String expected  = "Added 1 song into " + playlist;
        Assert.assertEquals(toastText, expected);

        // ► keep the browser open for eight seconds so you can watch it
        try { Thread.sleep(8000); } catch (InterruptedException ignored) {}
    }

    /* ---------------------------------------------------------------------- */
    /*  Helper steps – only the ones that are different from BaseTest appear  */
    /* ---------------------------------------------------------------------- */

    private void waitForHome() {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(15));

        // A) login widgets disappear
        w.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("input[type='email']")));

        // B) either the Search nav link OR the search box becomes visible
        w.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search']")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='search']"))
        ));
    }

    private void searchSong(String dark) {
        // open Search view if the box is still hidden
        if (driver.findElements(By.cssSelector("input[type='search']")).isEmpty()) {
            driver.findElement(By.xpath("//*[text()='Search']")).click();
        }

        WebElement box = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[type='search']")));
        box.clear();
        box.sendKeys(dark);
    }

    private void clickViewAllBtn() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='View All']"))).click();
    }

    private void selectFirstSong() {
        By firstSong = By.xpath("//section[@id='songResultsWrapper']//li[1]");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(firstSong)).click();
    }

    private void clickAddToBtn() {
        // this is the corrected, unique locator the instructor mentioned
        By addTo = By.xpath("//button[@data-test='add-to-btn']");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addTo)).click();
    }

    private void choosePlaylist(String name) {
        By item = By.xpath("//li[normalize-space()='" + name + "']");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(item)).click();
    }

    private String getToastText() {
        By toast = By.cssSelector(".toast");
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(toast))
                .getText().trim();
    }
}

