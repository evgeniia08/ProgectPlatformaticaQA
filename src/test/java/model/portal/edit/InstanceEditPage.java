package model.portal.edit;

import model.base.PortalBaseEditPage;
import model.portal.table.InstancePage;
import org.openqa.selenium.WebDriver;

public class InstanceEditPage extends PortalBaseEditPage<InstancePage> {

    public InstanceEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InstancePage createPortalPage() {
        return new InstancePage(getDriver());
    }
}
