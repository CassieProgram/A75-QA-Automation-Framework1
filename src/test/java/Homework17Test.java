import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class Homework17Test extends BaseTest {
    @Test
    public void addSongTOPlaylist() throws InterruptedException {
        String ExpectedString = "Added 1 song into \"checking.\"";

        //Navigate to the login page
        navigatetoURL();
        //loign to the application
        provideEmail("shynar.largess@testpro.io");
        providePassword("Javashynar89!");
        clickSubmitBtn();
        Thread.sleep(2000);
        //search for the song
        searchSong("dark");
        Thread.sleep(2000);
        //click view all button
        clickViewALLBtn();
        Thread.sleep(2000);
        //Select the first song
        selectFirstSong();
        Thread.sleep(2000);
        //Click on add to playlist
        clickAddToBtn();
        Thread.sleep(2000);
        //Select the playlist
        choosePlaylist();
        Thread.sleep(2000);
        //Assertion for added to the playlist
        Assert.assertEquals(getAddToPlaylistSuccessMsg(), ExpectedString);
    }



public void providePassword(String password) {
    WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
    passwordField.clear();
    passwordField.sendKeys(password);
}

public void provideEmail(String email) {
    WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
    emailField.clear();
    emailField.sendKeys(email);
}


public void navigatetoURL() {
    url= "https://qa.koel.app/";
    driver.get(url);
}

public void clickSubmitBtn() {
    WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));
    submitBtn.click();

}

public void clickProfileIcon(){
    WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
    avatarIcon.click();
}

public void provideCurrentPassword(String password){
    WebElement currentpassword= driver.findElement(By.cssSelector("input[name='current_password']"));
    currentpassword.clear();
    currentpassword.sendKeys(password);
}
public String randomName(){
    return UUID.randomUUID().toString().replace("-","");
}
public void changeName(String randomname){
    WebElement currentName = driver.findElement(By.cssSelector("[name='name']"));
    currentName.clear();
    currentName.sendKeys(randomname);
}
public void clickSave(){
    WebElement clickSave = driver.findElement(By.cssSelector("button.btn-submit"));
    clickSave.click();
}

public String getAddToPlaylistSuccessMsg() {
    WebElement notification = driver.findElement(By.cssSelector("div.success.show"));
    return  notification.getText();

}

public void choosePlaylist() {
    WebElement playlist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'checking')]"));
    playlist.click();
}

public void clickAddToBtn() {
    WebElement addToBtn = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//button[@class='btn-add-to']"));
    addToBtn.click();
}

public void selectFirstSong() {
    WebElement Firstsong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//tr[@class='song-item'][1]"));
    Firstsong.click();
}

public void clickViewALLBtn() {
    WebElement viewAll = driver.findElement(By.xpath("//button[@data-test='view-all-songs-btn']"));
    viewAll.click();
}

public void searchSong(String song) {
    WebElement searchField = driver.findElement(By.cssSelector("input[type='search']"));
    searchField.clear();
    searchField.sendKeys(song);
}
}