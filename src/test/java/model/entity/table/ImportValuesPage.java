package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ImportValuesEditPage;
import model.entity.view.ImportValuesViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class ImportValuesPage extends EntityBaseTablePage<ImportValuesPage, ImportValuesEditPage, ImportValuesViewPage> {

    @FindBy(xpath = "//i[contains(text(),'delete_outline')]")
    private WebElement recycleBin;

    public ImportValuesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ImportValuesEditPage createEditPage() {
        return new ImportValuesEditPage(getDriver());
    }

    @Override
    protected ImportValuesViewPage createViewPage() {
        return new ImportValuesViewPage(getDriver());
    }
}