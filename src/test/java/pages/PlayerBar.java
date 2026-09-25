package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlayerBar {

    private final WebDriverWait wait;

    private final By pauseButton =
            By.cssSelector(
                    "span[data-testid='pause-btn'][title='Pause']"
            );

    public PlayerBar(WebDriver driver) {

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public boolean indicatesPlayback() {

        try {

            WebElement pause =
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    pauseButton
                            )
                    );

            return pause != null;

        } catch (Exception e) {

            return false;
        }
    }
}