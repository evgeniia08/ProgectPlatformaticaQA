package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class LoginPage extends BasePage {

    @FindBy(name = "login_name")
    private WebElement inputUsername;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(css = "button[type=submit]")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        ProjectUtils.fill(getWait(), inputUsername, username);
        ProjectUtils.fill(getWait(), inputPassword, password);
        submitButton.click();
    }
}
