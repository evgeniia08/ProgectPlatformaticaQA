package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ExportEditPage;
import model.entity.view.ExportViewPage;
import org.openqa.selenium.WebDriver;

public final class ExportPage extends EntityBaseTablePage<ExportPage, ExportEditPage, ExportViewPage> {

    @Override
    protected ExportEditPage createEditPage() {
        return new ExportEditPage(getDriver());
    }

    @Override
    protected ExportViewPage createViewPage() {
        return new ExportViewPage(getDriver());
    }

    public ExportPage(WebDriver driver) {
        super(driver);
    }
}
