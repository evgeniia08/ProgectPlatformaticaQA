package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.FootersEntityBaseEditPage;
import org.openqa.selenium.WebDriver;

public class FootersPageEntityBase extends EntityBaseTablePage<FootersPageEntityBase, FootersEntityBaseEditPage> {

    public FootersPageEntityBase(WebDriver driver){
        super(driver);
    }

    @Override
    protected FootersEntityBaseEditPage createEditPage(){
        return new FootersEntityBaseEditPage(getDriver());
    }
}
