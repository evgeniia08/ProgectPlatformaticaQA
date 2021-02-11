package model.work;

import model.base.WorkBaseIndexPage;
import model.base.WorkHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigurationPage extends WorkBaseIndexPage {

    @FindBy(xpath = "//a[contains(text(), 'HOME')]")
    private WebElement homeButton;

    @FindBy(className = "visible-on-sidebar-mini")
    private WebElement viewList;

    @FindBy(xpath = "//li//p[contains(text(), 'Entities')]")
    private WebElement entities;

    @FindBy(xpath = "//div[@id='formsEntities']//span[contains(text(), 'New')]")
    private WebElement newEntity;

    public ConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public ConfigurationPage clickNewEntity() {
        entities.click();
        newEntity.click();

        return this;
    }

    public WorkHomePage goHomePage() {
        viewList.click();
        homeButton.click();

        return new WorkHomePage(getDriver());
    }
}