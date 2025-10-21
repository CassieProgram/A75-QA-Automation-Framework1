package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ConnectionBuilder;
import java.time.Duration;

public class BasePage {

        WebDriver driver;
        WebDriverWait wait;
        Actions actions;

        public BasePage(WebDriver givenDriver){
            this.driver = givenDriver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            this.actions = new Actions(driver);
            PageFactory.initElements(givenDriver, this);
        }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

        public WebElement findElement(By locator){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        }
        public void doubleClick (By locator) {
            actions.doubleClick(findElement(locator)).perform();
        }


    }



