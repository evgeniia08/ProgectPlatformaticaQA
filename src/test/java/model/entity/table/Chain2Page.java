package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.Chain2EditPage;
import model.entity.view.Chain2ViewPage;
import org.openqa.selenium.WebDriver;

public class Chain2Page extends EntityBaseTablePage<Chain2Page, Chain2EditPage, Chain2ViewPage> {

    public Chain2Page(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Chain2EditPage createEditPage() {
        return new Chain2EditPage(getDriver());
    }

    @Override
    protected Chain2ViewPage createViewPage() {
        return new Chain2ViewPage(getDriver());
    }

    public static Chain2Page getPage(WebDriver driver) {
        driver.get("https://ref.eteam.work/index.php?action=action_list&entity_id=62");
        return new Chain2Page(driver);
    }
}
