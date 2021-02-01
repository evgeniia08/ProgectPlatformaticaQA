package model.portal.table;

import model.base.PortalBaseTablePage;
import org.openqa.selenium.WebDriver;

public class PortalAppsPage extends PortalBaseTablePage<PortalAppsPage, PortalAppsEditPage> {

    public PortalAppsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PortalAppsEditPage createPortalEditPage() {
        return new PortalAppsEditPage(getDriver());
    }
}
