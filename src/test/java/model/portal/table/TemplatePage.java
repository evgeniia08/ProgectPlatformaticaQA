package model.portal.table;

import model.base.PortalBaseTablePage;
import org.openqa.selenium.WebDriver;

public class TemplatePage extends PortalBaseTablePage {

    public TemplatePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Object createPortalEditPage() {
        return null;
    }

    @Override
    protected Object createPortalViewPage() {
        return null;
    }
}
