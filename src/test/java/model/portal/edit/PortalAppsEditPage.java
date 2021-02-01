package model.portal.edit;

import model.base.PortalBaseEditPage;
import model.portal.table.PortalAppsPage;
import org.openqa.selenium.WebDriver;

public class PortalAppsEditPage extends PortalBaseEditPage<PortalAppsPage> {

    public PortalAppsEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PortalAppsPage createPortalPage() {
        return new PortalAppsPage(getDriver());
    }
}
