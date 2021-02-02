package model.portal.edit;


import model.base.EntityBaseEditPage;
import model.portal.common.AppsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

public class AppsEditPage extends EntityBaseEditPage<AppsPage> {

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "subdomain")
    private WebElement inputSubdomain;

    @FindBy(id = "primary_language")
    private WebElement inputPrimaryLanguage;

    public AppsEditPage(WebDriver driver) {
        super(driver);
    }

    public AppsEditPage createInstance(WebDriver driver, String name, String subDomain, String primaryLanguage) {

        getWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("name")));
        ProjectUtils.fill(getWait(), inputName, name);
        ProjectUtils.fill(getWait(), inputSubdomain, subDomain);

        Select drop = new Select(inputPrimaryLanguage);
        drop.selectByVisibleText(primaryLanguage);
        return this;
    }

    @Override
    protected AppsPage createPage() {
        return new AppsPage(getDriver());
    }
}
