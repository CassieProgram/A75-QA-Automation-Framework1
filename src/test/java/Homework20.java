import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;

public class Homework20 extends BaseTest{

    private Object navigateToURL;

    @Test
    public void deletePlaylist() throws InterruptedException {
        navigateToURL();

        String expectedPlaylistDeletedMessage = "Deleted playlist \"checking.\"";

        provideEmail("shynar.largess@testpro.io");
        providePassword ("Javashynar89!");
        clickSubmitBtn();
        openPlaylist();
        clickDeletePlaylistBtn();
        Assert.assertEquals(getDeletedPlaylistMsg(), expectedPlaylistDeletedMessage);

    }

    public String  getDeletedPlaylistMsg() {
        WebElement notificationMsg = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMsg.getText();

    }

    public void clickDeletePlaylistBtn() throws InterruptedException {
        WebElement deletePlaylist = driver.findElement(By.cssSelector(".btn-delete-playlist"));
        deletePlaylist.click();
        //Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));

    }

    public void openPlaylist() {

        WebElement emptyPlaylist = driver.findElement(By.cssSelector(".playlist:nth-child(3)")) ;
        emptyPlaylist.click();
    }
/*
    private void clickSubmit() {
    }

    private void provideEmail(String mail) {
    }

    private void providePassword(String s) {
    }

 */
}
