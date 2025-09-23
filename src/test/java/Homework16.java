import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework16 extends BaseTest{
    @Test
    public void registration(){

        ChromeOptions opts= new ChromeOptions();
        opts.addArguments("--remote-allow-origins=*");
        WebDriver driver= new ChromeDriver(opts);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://qa.koel.app/";
        driver.get(url);
        WebElement registrationLink= driver.findElement(By.cssSelector("[href='registration']"));
        registrationLink.click();
        String regURL= "https://qa.koel.app/registration";
        Assert.assertEquals(driver.getCurrentUrl(),regURL);
        driver.quit();




    }
}
