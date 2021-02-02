package model.portal.common;

import model.portal.table.InstancePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AfterInstallPage extends InstancePage {

    @FindBy(xpath="//h4[contains(text(), 'Username')]/b")
    private WebElement username;

    @FindBy(xpath="//h4[contains(text(), 'Password')]/b")
    private WebElement password;

    public AfterInstallPage(WebDriver driver) {
        super(driver);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }
}
