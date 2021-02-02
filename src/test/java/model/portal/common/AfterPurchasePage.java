package model.portal.common;

import model.base.PortalBaseIndexPage;
import model.portal.edit.InstanceEditPage;
import model.portal.table.TemplatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AfterPurchasePage extends PortalBaseIndexPage {

    @FindBy(xpath="//button[contains(text(), 'Now')]")
    private WebElement installNowButton;

    @FindBy(xpath="//button[contains(text(), 'later')]")
    private WebElement installLaterButton;

    public AfterPurchasePage(WebDriver driver) {
        super(driver);
    }

    public InstanceEditPage clickInstallNowButton() {
        installNowButton.click();
        return new InstanceEditPage(getDriver());
    }

    public TemplatePage clickInstallLaterButton() {
        installLaterButton.click();
        return new TemplatePage(getDriver());
    }
}
