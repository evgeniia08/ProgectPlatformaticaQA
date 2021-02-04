package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.FootersEditPage;
import model.entity.view.FootersViewPage;
import org.openqa.selenium.WebDriver;

public class FootersPage extends EntityBaseTablePage<FootersPage, FootersEditPage, FootersViewPage> {

    public FootersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FootersEditPage createEditPage(){
        return new FootersEditPage(getDriver());
    }

    @Override
    protected FootersViewPage createViewPage() {
        return new FootersViewPage(getDriver());
    }
}
