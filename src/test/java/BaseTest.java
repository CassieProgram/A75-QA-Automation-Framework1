import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BaseTest {
    public WebDriver driver;
    public String url;
    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Preconditions
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        navigatetoURL();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    public void submitBtn() throws InterruptedException {
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
        Thread.sleep(2000);
    }
    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.sendKeys(password);
    }
    public void provideEmail(String  email) {
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void navigatetoURL() {
        url = "https://qa.koel.app";
        driver.get(url);
    }
    public String successShow() {
        WebElement notification = driver.findElement(By.xpath("//div[@class= 'success show']"));
        return   notification.getText();
    }
    public void addToPlaylist() {
        WebElement addToPlaylist = driver.findElement(By.xpath("//section[@id= 'songResultsWrapper']//li[contains(text() , 'Wiles Playlist')]"));
        addToPlaylist.click();
    }
    public void addToBtn() {
        WebElement addTo = driver.findElement(By.cssSelector("button.btn-add-to"));
        addTo.click();
    }
    public void clickSong() {
        WebElement clickSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//td[@class='title']"));
        clickSong.click();
    }
    public void viewAll() {
        WebElement viewAll = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewAll.click();
    }
    public void searchSong(String song) {
        WebElement search = driver.findElement(By.xpath("//input[@type= 'search']"));
        search.clear();
        search.sendKeys(song);
    }

    public boolean pauseBtn() {
        WebElement pauseButton = driver.findElement(By.xpath("//footer[@id= 'mainFooter']//span[@class= 'pause']"));
        return true;
    }

    public void playBtn() {
        WebElement playButton = driver.findElement(By.xpath("//footer[@id= 'mainFooter']//span[@class= 'play']"));
        playButton.click();
    }

    public void nextBtn() {
        WebElement nextSong = driver.findElement(By.xpath("//footer[@id= 'mainFooter']//i[@title= 'Play next song']"));
        nextSong.click();
    }
}
