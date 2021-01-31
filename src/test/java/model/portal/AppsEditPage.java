package model.portal;

import model.BaseEditPage;
import org.openqa.selenium.WebDriver;

public class AppsEditPage extends BaseEditPage <AppsPage>{

    public AppsEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AppsPage createPage() {
        return null;
    }
}
