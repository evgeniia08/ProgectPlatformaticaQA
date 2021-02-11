package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.FieldsEdit1Page;
import model.entity.view.Fields1ViewPage;
import org.openqa.selenium.WebDriver;

public class Fields1Page extends EntityBaseTablePage<Fields1Page, FieldsEdit1Page, Fields1ViewPage> {

    public Fields1Page(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FieldsEdit1Page createEditPage() {
        return new FieldsEdit1Page(getDriver());
    }

    @Override
    protected Fields1ViewPage createViewPage() {
        return new Fields1ViewPage(getDriver());
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
