package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.FootersEditPage;
import org.openqa.selenium.WebDriver;

public class FootersPage extends EntityBaseTablePage<FootersPage, FootersEditPage> {

    public FootersPage(WebDriver driver){
        super(driver);
    }

    @Override
    protected FootersEditPage createEditPage(){
        return new FootersEditPage(getDriver());
    }
}
