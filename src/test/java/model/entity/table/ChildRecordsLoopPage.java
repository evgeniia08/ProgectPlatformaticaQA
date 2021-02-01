package model.entity.table;
import model.BaseTablePage;
import model.entity.edit.ChildRecordsLoopEditPage;
import org.openqa.selenium.WebDriver;


public final class ChildRecordsLoopPage extends BaseTablePage<ChildRecordsLoopPage, ChildRecordsLoopEditPage> {

    public ChildRecordsLoopPage(WebDriver driver) {
        super(driver);
    }

      @Override
    protected ChildRecordsLoopEditPage createEditPage() {
        return new ChildRecordsLoopEditPage(getDriver());
    }

}
