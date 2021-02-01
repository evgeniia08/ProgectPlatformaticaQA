package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.PortalAppsEditPage;
import model.portal.view.PortalAppsViewPage;
import org.openqa.selenium.WebDriver;

public class PortalAppsPage extends PortalBaseTablePage<PortalAppsPage, PortalAppsEditPage, PortalAppsViewPage> {

    public PortalAppsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PortalAppsEditPage createPortalEditPage() {
        return new PortalAppsEditPage(getDriver());
    }

    @Override
    protected PortalAppsViewPage createPortalViewPage() {
        return new PortalAppsViewPage(getDriver());
    }
}
