package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;




public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
super(driver);
    }
    //locators
    @FindBy(css = ".playlist:nth-child(3)")
    private WebElement playlist;

    @FindBy(css = "input[name='name']")
    private WebElement nameInput;

    @FindBy(css = "div.success.show")
    private WebElement toast;

    @FindBy(css = "img.avatar")
    private WebElement userAvatar;

    /* actions */
    public HomePage openRename(){
        doubleClick(playlist);
        return this;
    }

    public void doubleClick(WebElement playlist) {
    }

    public HomePage renameTo(String text){
        nameInput.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        nameInput.sendKeys(text);
        nameInput.sendKeys(Keys.ENTER);
        return this;
    }

    public String toastMsg(){ return visible((By) toast).getText(); }

    public String playlistName(){ return visible((By) playlist).getText(); }
public WebElement getUserAvatar () { return visible((By) userAvatar);}

    }


