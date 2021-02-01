package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.DefaultEntityBaseEditPage;
import org.openqa.selenium.WebDriver;

public final class DefaultPageEntityBase extends EntityBaseTablePage<DefaultPageEntityBase, DefaultEntityBaseEditPage> {

    public DefaultPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected DefaultEntityBaseEditPage createEditPage() {
        return new DefaultEntityBaseEditPage(getDriver());
    }
}
