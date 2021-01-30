package model.entity.table;
import model.BaseTablePage;
import model.entity.edit.ExportEditPage;
import org.openqa.selenium.WebDriver;

public final class ExportPage extends BaseTablePage<ExportPage, ExportEditPage> {
    @Override
    protected ExportEditPage createEditPage() {
        return new ExportEditPage(getDriver());
    }

    public ExportPage(WebDriver driver) {
        super(driver);
    }
}
