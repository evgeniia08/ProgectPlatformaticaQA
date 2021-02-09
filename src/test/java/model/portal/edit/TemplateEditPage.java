package model.portal.edit;

import model.base.PortalBaseEditPage;
import model.portal.table.InstancePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class TemplateEditPage extends PortalBaseEditPage<InstancePage> {

    @FindBy(id = "author")
    private WebElement author;

    @FindBy(id = "description")
    private WebElement description;

    public TemplateEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InstancePage createPortalPage() {
        return new InstancePage(getDriver());
    }

    public TemplateEditPage fillOutInputFields(String author, String description) {
        ProjectUtils.fill(getWait(), this.author,  author);
        ProjectUtils.fill(getWait(), this.description,  description);
        return new TemplateEditPage(getDriver());
    }

    public InstancePage saveAsTemplateClickSaveButton() {
        ProjectUtils.scroll(getDriver(), saveButton);
        ProjectUtils.click(getWait(), saveButton);
        return new InstancePage(getDriver());
    }
}
