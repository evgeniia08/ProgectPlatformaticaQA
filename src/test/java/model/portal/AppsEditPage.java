package model.portal;

import model.BaseEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

public class AppsEditPage extends BaseEditPage<AppsPage>{

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "subdomain")
    private WebElement inputSubdomain;

    @FindBy(id = "primary_language")
    private WebElement inputPrimaryLanguage;

    public AppsEditPage(WebDriver driver) {
        super(driver);
    }

    public AppsEditPage createInstance(WebDriver driver, String name, String subDomain, String primaryLanguage) throws InterruptedException {

        getWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.id("name")));
        ProjectUtils.fill(getWait(), driver.findElement(By.id("name")), name);
        ProjectUtils.fill(getWait(), driver.findElement(By.id("subdomain")), name);

        Select drop = new Select(driver.findElement(By.id("primary_language")));
        drop.selectByVisibleText(primaryLanguage);
        return this;
    }

    @Override
    protected AppsPage createPage() {
        return new AppsPage(getDriver());
    }
}
