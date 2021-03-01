package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ImportEditPage;
import model.entity.view.ImportViewPage;
import org.openqa.selenium.WebDriver;

public class ImportPage extends EntityBaseTablePage<ImportPage, ImportEditPage, ImportViewPage> {

    public ImportPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ImportViewPage createViewPage() {
        return new ImportViewPage(getDriver());
    }

    @Override
    protected ImportEditPage createEditPage() {
        return new ImportEditPage(getDriver());
    }
}
