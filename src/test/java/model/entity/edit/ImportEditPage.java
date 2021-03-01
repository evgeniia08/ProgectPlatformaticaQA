package model.entity.edit;

import model.base.EntityBaseEditExtPage;
import model.entity.table.ImportPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class ImportEditPage extends EntityBaseEditExtPage<ImportPage, ImportEditPage> {

    @FindBy(xpath = "//input[@value='Do import']")
    private WebElement doImportButton;

    @FindBy(xpath = "//input[@value='Custom Import']")
    private WebElement customImportButton;

    @FindBy(xpath = "//i[text()='done_all']")
    private WebElement importButton;

    public ImportEditPage(WebDriver driver) {
        super(driver);
    }

    public ImportEditPage clickDoImportButton() {
        doImportButton.click();
        return this;
    }

    public ImportEditPage clickCustomImportButton() {
        customImportButton.click();
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

    @Override
    protected ImportEditPage createEditPage() {
        return new ImportEditPage(getDriver());
    }
}
