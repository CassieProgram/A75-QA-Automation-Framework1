package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage (WebDriver driver) {
        super(driver);

    }
    @FindBy(css = "input[type='email']")
    WebElement emailField;
    @FindBy ( css = "input[type='password']")
    WebElement passwordField;
    @FindBy (css = "button[type='submit']")
    WebElement submitBtn;




    //fluent interface
    public LoginPage email (String value) {
        emailField.sendKeys(value);
        return this;
    }
    public LoginPage password(String password) {
        passwordField.sendKeys(password);
        return this;
    }
    public HomePage  submit() {
        submitBtn.click();
        return new HomePage(driver);


    }

    }




