package model.entity.table;
import model.base.EntityBaseTablePage;
import model.entity.edit.ExportEditPage;
import org.openqa.selenium.WebDriver;

public final class ExportPage extends EntityBaseTablePage<ExportPage, ExportEditPage> {
    @Override
    protected ExportEditPage createEditPage() {
        return new ExportEditPage(getDriver());
    }

    public ExportPage(WebDriver driver) {
        super(driver);
    }
}
