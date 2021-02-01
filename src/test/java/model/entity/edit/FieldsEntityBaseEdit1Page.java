package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.Fields1PageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static runner.ProjectUtils.fill;

public class FieldsEntityBaseEdit1Page extends EntityBaseEditPage<Fields1PageEntityBase> {

    @FindBy(id = "title")
    private WebElement inputTitle;

    @FindBy(id = "comments")
    private WebElement inputComments;

    @FindBy(id = "int")
    private WebElement inputInt;

    public FieldsEntityBaseEdit1Page(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Fields1PageEntityBase createPage() {
        return new Fields1PageEntityBase(getDriver());
    }

    public FieldsEntityBaseEdit1Page sendKeys(String title, String comments, String int_){
        fill(getWait(), inputTitle, title);
        fill(getWait(), inputComments, comments);
        fill(getWait(), inputInt, int_);
        return this;
    }
}
