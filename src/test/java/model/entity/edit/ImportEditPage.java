package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.ImportPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class ImportEditPage extends EntityBaseEditPage<ImportPage> {

    @FindBy(xpath = "//input[@value='Do import']")
    private WebElement doImportButton;

    @FindBy(xpath = "//i[text()='done_all']")
    private WebElement importButton;

    public ImportEditPage(WebDriver driver) {
        super(driver);
    }

    public ImportEditPage clickDoImportButton() {
        doImportButton.click();
        return this;
    }

    public ImportEditPage clickImportButton() {
        ProjectUtils.click(getWait(), importButton);
        return this;
    }

    @Override
    protected ImportPage createPage() {
        return new ImportPage(getDriver());
    }
}
