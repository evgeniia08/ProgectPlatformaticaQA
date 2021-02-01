package model.entity.table;
import model.base.EntityBaseTablePage;
import model.entity.edit.ExportEntityBaseEditPage;
import org.openqa.selenium.WebDriver;

public final class ExportPageEntityBase extends EntityBaseTablePage<ExportPageEntityBase, ExportEntityBaseEditPage> {
    @Override
    protected ExportEntityBaseEditPage createEditPage() {
        return new ExportEntityBaseEditPage(getDriver());
    }

    public ExportPageEntityBase(WebDriver driver) {
        super(driver);
    }
}
