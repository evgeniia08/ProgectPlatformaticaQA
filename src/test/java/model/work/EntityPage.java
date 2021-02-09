package model.work;

import model.base.WorkBaseTablePage;
import model.portal.edit.InstanceEditPage;
import org.openqa.selenium.WebDriver;

public class EntityPage extends WorkBaseTablePage<EntityPage, EntityEditPage, EntityViewPage> {


    public EntityPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected EntityEditPage createWorkEditPage() {
        return new EntityEditPage(getDriver());
    }

    @Override
    protected EntityViewPage createWorkViewPage() {
        return new EntityViewPage(getDriver());
    }
}
