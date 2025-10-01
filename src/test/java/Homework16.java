import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.html.HTMLInputElement;

import java.time.Duration;

public class Homework16 extends BaseTest{
    private Object registrationLink;

    @Test
    public void registration(){


        String url = "https://qa.koel.app/";
        driver.get(url);
        driver.findElement(By.cssSelector("[href='registration']")).click();
        String regURL= "https://qa.koel.app/registration";
        Assert.assertEquals(driver.getCurrentUrl(),regURL);
        




    }
}
