package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.Chain1EntityBaseEditPage;
import org.openqa.selenium.WebDriver;


public class Chain1PageEntityBase extends EntityBaseTablePage<Chain1PageEntityBase, Chain1EntityBaseEditPage> {

    public Chain1PageEntityBase(WebDriver driver) {
        super(driver);

    }

    @Override
    protected Chain1EntityBaseEditPage createEditPage() {
        return new Chain1EntityBaseEditPage(getDriver());
    }

}