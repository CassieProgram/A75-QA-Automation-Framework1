package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    @FindBy(css = "input [type='email']");
    WebElement emailField;
    @FindBy ( css = "input[type='password']");
    WebElement passwordField;
    @FindBy (css = "button[type='submit']");
    WebElement submitBtn;
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //fluent interface
    public LoginPage provideEmail (String email) {
        emailField.sendKeys(email);
        return this;
    }
    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPage  clickSubmit() {
        submitBtn.click();
        return this;
    }
    public LoginPage  login() {
        provideEmail("shynar.largess@tespro.io");
        providePassword("Javashynar89");
        clickSubmit();
        return this;
    }

}