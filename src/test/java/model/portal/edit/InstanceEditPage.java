package model.portal.edit;

import model.base.PortalBaseEditPage;
import model.portal.table.InstancePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

public class InstanceEditPage extends PortalBaseEditPage<InstancePage> {

    @FindBy(id = "name")
    protected WebElement inputName;

    @FindBy(id = "subdomain")
    private WebElement inputSubdomain;

    @FindBy(id = "primary_language")
    private WebElement inputPrimaryLanguage;

    public InstanceEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InstancePage createPortalPage() {
        return new InstancePage(getDriver());
    }

    public InstanceEditPage fillOutInstanceForm(String name, String subDomain, String primaryLanguage) {

        ProjectUtils.fill(getWait(), inputName, name);
        ProjectUtils.fill(getWait(), inputSubdomain, subDomain);

        Select drop = new Select(inputPrimaryLanguage);
        drop.selectByVisibleText(primaryLanguage);

        return this;
    }

    public InstanceEditPage inputName(String name) {
        ProjectUtils.fill(getWait(), inputName, name);
        return this;
    }
}
