package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SmartPlaylistPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By createPlaylistButton =
            By.cssSelector("[data-testid='sidebar-create-playlist-btn']");
    private final By newSmartPlaylistOption =
            By.cssSelector("[data-testid='playlist-context-menu-create-smart']");
    private final By form =
            By.cssSelector("form[data-testid='create-smart-playlist-form']");
    private final By nameInput =
            By.cssSelector("form[data-testid='create-smart-playlist-form'] input[name='name']");
    private final By ruleRows =
            By.cssSelector("form[data-testid='create-smart-playlist-form'] [data-test='smart-playlist-rule-row']");
    private final By saveButton =
            By.cssSelector("form[data-testid='create-smart-playlist-form'] button[type='submit']");
    private final By smartPlaylistLinks =
            By.cssSelector("li.playlist.smart a[href^='#!/playlist/']");
    private final By songRows = By.cssSelector("tr.song-item");
    private final By songTitle = By.cssSelector("td.title");
    private final By ruleGroups =
            By.cssSelector("form[data-testid='create-smart-playlist-form'] [data-test='smart-playlist-rule-group']");
    private final By addRuleButton = By.cssSelector("button.btn-add-rule");
    private final By songArtist = By.cssSelector("td.artist");

    public SmartPlaylistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public Set<String> getPlaylistLinksNamed(String name) {
        Set<String> hrefs = new HashSet<>();

        for (WebElement link : driver.findElements(smartPlaylistLinks)) {
            if (link.getText().trim().equals(name)) {
                hrefs.add(link.getAttribute("href"));
            }
        }

        return hrefs;
    }

    public void openNewSmartPlaylistDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(createPlaylistButton))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(newSmartPlaylistOption))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(form));
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput))
                .sendKeys(name);
    }

    public void setFirstRule(String field, String operator, String value) {
        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ruleRows)
        );

        new Select(row.findElement(By.cssSelector("select[name='model[]']")))
                .selectByVisibleText(field);
        new Select(row.findElement(By.cssSelector("select[name='operator[]']")))
                .selectByVisibleText(operator);

        WebElement valueInput =
                row.findElement(By.cssSelector("input[name='value[]']"));
        valueInput.clear();
        valueInput.sendKeys(value);
    }

    public int getRuleCount() {
        return driver.findElements(ruleRows).size();
    }

    public boolean firstRuleMatches(String field, String operator, String value) {
        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ruleRows)
        );

        String actualField = new Select(
                row.findElement(By.cssSelector("select[name='model[]']"))
        ).getFirstSelectedOption().getText().trim();

        String actualOperator = new Select(
                row.findElement(By.cssSelector("select[name='operator[]']"))
        ).getFirstSelectedOption().getText().trim();

        String actualValue = row.findElement(
                By.cssSelector("input[name='value[]']")
        ).getAttribute("value");

        return field.equals(actualField)
                && operator.equals(actualOperator)
                && value.equals(actualValue);
    }

    public void save() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton))
                .click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(form));
    }

    public String openNewPlaylist(String name, Set<String> previousLinks) {
        String newHref = wait.until(d -> {
            for (WebElement link : d.findElements(smartPlaylistLinks)) {
                String href = link.getAttribute("href");

                if (link.getText().trim().equals(name)
                        && href != null
                        && !previousLinks.contains(href)) {
                    return href;
                }
            }
            return null;
        });

        WebElement newLink = wait.until(d -> {
            for (WebElement link : d.findElements(smartPlaylistLinks)) {
                if (newHref.equals(link.getAttribute("href"))) {
                    return link;
                }
            }
            return null;
        });

        wait.until(ExpectedConditions.elementToBeClickable(newLink)).click();
        return newHref;
    }

    public boolean isPlaylistOpen(String href) {
        try {
            wait.until(d -> {
                for (WebElement link : d.findElements(smartPlaylistLinks)) {
                    if (href.equals(link.getAttribute("href"))) {
                        String classes = link.getAttribute("class");
                        return classes != null
                                && (" " + classes + " ").contains(" active ");
                    }
                }
                return false;
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<String> getDisplayedSongTitles() {
        return wait.until(d -> {
            List<String> titles = d.findElements(songRows).stream()
                    .filter(WebElement::isDisplayed)
                    .map(row -> row.findElement(songTitle).getText().trim())
                    .collect(Collectors.toList());

            return titles.isEmpty() ? null : titles;
        });
    }
}
