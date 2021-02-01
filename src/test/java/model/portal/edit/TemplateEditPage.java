package model.portal.edit;

import model.base.PortalBaseEditPage;
import org.openqa.selenium.WebDriver;

public class TemplateEditPage extends PortalBaseEditPage {

    public TemplateEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Object createPortalPage() {
        return null;
    }
}
