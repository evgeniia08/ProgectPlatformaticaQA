package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ChildRecordsLoopEditPage;
import model.entity.view.ChildRecordsLoopViewPage;
import org.openqa.selenium.WebDriver;


public final class ChildRecordsLoopPage extends EntityBaseTablePage <ChildRecordsLoopPage, ChildRecordsLoopEditPage, ChildRecordsLoopViewPage> {

    public ChildRecordsLoopPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ChildRecordsLoopEditPage createEditPage() {
        return new ChildRecordsLoopEditPage(getDriver());
    }

    @Override
    protected ChildRecordsLoopViewPage createViewPage() {
        return new ChildRecordsLoopViewPage(getDriver());
    }
}

