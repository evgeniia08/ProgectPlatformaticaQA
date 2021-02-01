package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.Chain2EntityBaseEditPage;
import org.openqa.selenium.WebDriver;

public class Chain2PageEntityBase extends EntityBaseTablePage<Chain2PageEntityBase, Chain2EntityBaseEditPage> {

    public Chain2PageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Chain2EntityBaseEditPage createEditPage() {
        return new Chain2EntityBaseEditPage(getDriver());
    }

    public static Chain2PageEntityBase getPage(WebDriver driver) {
        driver.get("https://ref.eteam.work/index.php?action=action_list&entity_id=62");
        return new Chain2PageEntityBase(driver);
    }
}