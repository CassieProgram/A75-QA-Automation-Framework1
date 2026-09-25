package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By artistsLink =
            By.cssSelector("a[href='#!/artists']");

    private final By heading =
            By.xpath("//h1[normalize-space()='Artists']");

    private final By artistNames =
            By.cssSelector("a.name[href^='#!/artist/']");

    private final By searchInput =
            By.cssSelector("#searchForm input[type='search'][name='q']");

    private final By songRows =
            By.cssSelector("tr.song-item");

    private final By songTitleCell =
            By.cssSelector("td.title");

    public ArtistsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void open() {

        WebElement link =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                artistsLink
                        )
                );

        link.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        heading
                )
        );
    }

    public String getHeading() {

        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                heading
                        )
                )
                .getText()
                .trim();
    }

    public List<String> getVisibleArtistNames() {

        List<WebElement> artists =
                wait.until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                artistNames
                        )
                );

        return artists.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
    }

    public void searchForArtist(String artistName) {

        WebElement search =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                searchInput
                        )
                );

        search.clear();
        search.sendKeys(artistName);
    }

    public boolean isArtistNameShownInSearchResults(String artistName) {

        try {

            wait.until(driver -> {

                List<WebElement> artists =
                        driver.findElements(artistNames);

                return artists.stream()
                        .filter(WebElement::isDisplayed)
                        .anyMatch(
                                artist ->
                                        artist.getText()
                                                .trim()
                                                .equals(artistName)
                        );
            });

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }

    public void selectArtist(String artistName) {

        By artistCard =
                By.cssSelector(
                        "article[data-test='artist-card'].full[title='" +
                                artistName +
                                "']"
                );

        WebElement card =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                artistCard
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        card
                );

        card =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                artistCard
                        )
                );

        card.click();

        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        songRows
                )
        );
    }

    public boolean isSongAvailable(String songTitle) {

        try {

            wait.until(driver -> {

                List<WebElement> rows =
                        driver.findElements(songRows);

                return rows.stream()
                        .anyMatch(
                                row ->
                                        getSongTitle(row)
                                                .equals(songTitle)
                        );
            });

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }

    public void playSong(String songTitle) {

        WebElement songRow =
                findSongRow(songTitle);

        WebElement title =
                songRow.findElement(songTitleCell);

        title.click();
        title.click();

        wait.until(driver -> {

            WebElement currentRow =
                    findSongRow(songTitle);

            String classes =
                    currentRow.getAttribute("class");

            return classes != null
                    && classes.contains("playing");
        });
    }

    public boolean isSongRowPlaying(String songTitle) {

        try {

            WebElement row =
                    findSongRow(songTitle);

            String classes =
                    row.getAttribute("class");

            return classes != null
                    && classes.contains("playing");

        } catch (Exception e) {

            return false;
        }
    }

    private WebElement findSongRow(String songTitle) {

        By songRow =
                By.xpath(
                        "//tr[contains(@class,'song-item')]" +
                                "[.//td[contains(@class,'title') " +
                                "and normalize-space()='" +
                                songTitle +
                                "']]"
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        songRow
                )
        );
    }

    private String getSongTitle(WebElement row) {

        return row.findElement(songTitleCell)
                .getText()
                .trim();
    }
}