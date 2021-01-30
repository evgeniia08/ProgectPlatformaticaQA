package model.entity.table;

import model.BaseTablePage;
import model.entity.edit.FootersEditPage;
import org.openqa.selenium.WebDriver;

public class FootersPage extends BaseTablePage<FootersPage, FootersEditPage> {

    public FootersPage(WebDriver driver){
        super(driver);
    }

    @Override
    protected FootersEditPage createEditPage(){
        return new FootersEditPage(getDriver());
    }
}
