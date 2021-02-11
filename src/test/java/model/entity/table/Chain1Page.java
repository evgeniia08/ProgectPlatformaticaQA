package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.Chain1EditPage;
import model.entity.view.Chain1ViewPage;
import org.openqa.selenium.WebDriver;


public class Chain1Page extends EntityBaseTablePage<Chain1Page, Chain1EditPage, Chain1ViewPage> {

    public Chain1Page(WebDriver driver) {
        super(driver);

    }

    @Override
    protected Chain1EditPage createEditPage() {
        return new Chain1EditPage(getDriver());
    }

    @Override
    protected Chain1ViewPage createViewPage() {
        return new Chain1ViewPage(getDriver());
    }
}
