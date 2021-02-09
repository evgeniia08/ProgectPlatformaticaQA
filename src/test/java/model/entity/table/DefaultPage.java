package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.DefaultEditPage;
import model.entity.view.DefaultViewPage;
import org.openqa.selenium.WebDriver;

public final class DefaultPage extends EntityBaseTablePage<DefaultPage, DefaultEditPage, DefaultViewPage> {

    public DefaultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected DefaultEditPage createEditPage() {
        return new DefaultEditPage(getDriver());
    }

    @Override
    protected DefaultViewPage createViewPage() {
        return new DefaultViewPage(getDriver());
    }
}
