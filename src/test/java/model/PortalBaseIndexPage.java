package model;

import model.BaseIndexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortalBaseIndexPage extends BaseIndexPage {

    @FindBy(css = "li#pa-menu-item-97")
    private WebElement menuApps;

    @FindBy(css = "li#pa-menu-item-29")
    private WebElement menuTemplates;

    @FindBy(css = "li#pa-menu-item-26")
    private WebElement menuMarketplace;

    @FindBy(css = "li#pa-menu-item-83")
    private WebElement menuContact;

    public PortalBaseIndexPage(WebDriver driver) {
        super(driver);
    }
}
