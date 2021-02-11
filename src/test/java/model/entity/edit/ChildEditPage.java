package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.view.ParentViewPage;
import org.openqa.selenium.WebDriver;

public class ChildEditPage extends EntityBaseEditPage<ParentViewPage> {

    public ChildEditPage (WebDriver driver) {
        super(driver);
    }

    @Override
    protected ParentViewPage createPage() {
        return new ParentViewPage(getDriver());
    }

}
