package model.portal.edit;

import model.base.PortalBaseEditPage;
import org.openqa.selenium.WebDriver;

public class ContactEditPage extends PortalBaseEditPage {

    public ContactEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Object createPortalPage() {
        return null;
    }
}
