package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ChildRecordsLoopEditPage;
import model.entity.view.ChildRecordsLoopViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public final class ChildRecordsLoopPage extends EntityBaseTablePage <ChildRecordsLoopPage, ChildRecordsLoopEditPage, ChildRecordsLoopViewPage> {

    public ChildRecordsLoopPage(WebDriver driver) {
        super(driver);
    }

    public ChildRecordsLoopPage moveToElement(){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(By.xpath("//a[@id='navbarDropdownProfile']")));
        return this;
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

