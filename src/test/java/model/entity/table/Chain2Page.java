package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.Chain2EditPage;
import org.openqa.selenium.WebDriver;

public class Chain2Page extends EntityBaseTablePage<Chain2Page, Chain2EditPage> {

    public Chain2Page(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Chain2EditPage createEditPage() {
        return new Chain2EditPage(getDriver());
    }

    public static Chain2Page getPage(WebDriver driver) {
        driver.get("https://ref.eteam.work/index.php?action=action_list&entity_id=62");
        return new Chain2Page(driver);
    }
}