package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.FieldsEntityBaseEdit1Page;
import org.openqa.selenium.WebDriver;

public class Fields1PageEntityBase extends EntityBaseTablePage<Fields1PageEntityBase, FieldsEntityBaseEdit1Page> {

    public Fields1PageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FieldsEntityBaseEdit1Page createEditPage() {
        return new FieldsEntityBaseEdit1Page(getDriver());
    }

    public String getTitleText(){
        return getRow(0).get(0);
    }

    public String getCommentsText(){
        return getRow(0).get(1);
    }

    public String getInt_Text(){
        return getRow(0).get(2);
    }

}
