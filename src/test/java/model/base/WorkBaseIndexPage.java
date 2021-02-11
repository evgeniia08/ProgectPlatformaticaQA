package model.base;

import model.work.ConfigurationPage;
import model.work.ConstructionPage;
import model.work.EntityPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

public abstract class WorkBaseIndexPage extends BaseIndexPage{

    @FindBy(xpath = "//a[text()='Configuration']")
    private WebElement configuration;

    @FindBy(xpath = "//i[contains(text(), 'construction')]")
    private WebElement construction;

    @FindBy(xpath = "//p[contains(text(), 'Home')]")
    private WebElement home;

    @FindBy(xpath = "//p[contains(text(), 'Entities')]")
    private WebElement entities;

    @FindBy(xpath = "//p[contains(text(), 'Project')]")
    private WebElement nameOfEntity;

    public WorkBaseIndexPage(WebDriver driver) {
        super(driver);
    }

    public EntityPage clickProject() {
        clickMainMenu(entities);
        clickMainMenu(nameOfEntity);
        return new EntityPage(getDriver());
    }

    public ConfigurationPage clickConfiguration() {
        userProfileButton.click();
        getWait().until(TestUtils.movingIsFinished(configuration)).click();
        return new ConfigurationPage(getDriver());
    }

    public ConstructionPage clickConstruction() {
        clickMainMenu(construction);
        return new ConstructionPage(getDriver());
    }
}