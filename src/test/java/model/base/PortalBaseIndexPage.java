package model.base;

import model.entity.table.FieldsPage;
import model.portal.common.MarketplacePage;
import model.portal.table.ContactPage;
import model.portal.table.InstancePage;
import model.portal.table.TemplatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class PortalBaseIndexPage extends BaseIndexPage {

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

    public InstancePage clickMenuApps() {
        clickMainMenu(menuApps);
        return new InstancePage(getDriver());
    }

    public TemplatePage clickMenuTemplates() {
        clickMainMenu(menuTemplates);
        return new TemplatePage(getDriver());
    }

    public MarketplacePage clickMenuMarketplace() {
        clickMainMenu(menuMarketplace);
        return new MarketplacePage(getDriver());
    }

    public ContactPage clickMenuContact() {
        clickMainMenu(menuContact);
        return new ContactPage(getDriver());
    }
}
