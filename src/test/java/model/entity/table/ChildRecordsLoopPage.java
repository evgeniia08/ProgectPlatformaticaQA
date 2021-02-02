package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ChildRecordsLoopEditPage;
import org.openqa.selenium.WebDriver;


public final class ChildRecordsLoopPage extends EntityBaseTablePage <ChildRecordsLoopPage, ChildRecordsLoopEditPage> {

    public ChildRecordsLoopPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ChildRecordsLoopEditPage createEditPage() {
        return new ChildRecordsLoopEditPage(getDriver());
    }

}

