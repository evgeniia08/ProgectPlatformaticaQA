package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.InstanceEditPage;
import model.portal.view.InstanceViewPage;
import org.openqa.selenium.WebDriver;

public class InstancePage extends PortalBaseTablePage<InstancePage, InstanceEditPage, InstanceViewPage> {

    public InstancePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InstanceEditPage createPortalEditPage() {
        return new InstanceEditPage(getDriver());
    }

    @Override
    protected InstanceViewPage createPortalViewPage() {
        return new InstanceViewPage(getDriver());
    }
}
