package model.portal;

import model.BaseTablePage;
import org.openqa.selenium.WebDriver;

public class AppsPage extends BaseTablePage {

    @Override
    protected AppsEditPage createEditPage() {
        return new AppsEditPage(getDriver());
    }

    public AppsPage(WebDriver driver) {
        super(driver);
    }


}
