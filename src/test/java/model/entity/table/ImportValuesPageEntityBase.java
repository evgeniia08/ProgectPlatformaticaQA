package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ImportValuesEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class ImportValuesPageEntityBase extends EntityBaseTablePage<ImportValuesPageEntityBase, ImportValuesEditPage> {

    @FindBy(xpath = "//i[contains(text(),'delete_outline')]")
    private WebElement recycleBin;

    public ImportValuesPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ImportValuesEditPage createEditPage() {
        return new ImportValuesEditPage(getDriver());
    }

}