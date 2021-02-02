package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.Fields1Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static runner.ProjectUtils.fill;

public class FieldsEdit1Page extends EntityBaseEditPage<Fields1Page> {

    @FindBy(id = "title")
    private WebElement inputTitle;

    @FindBy(id = "comments")
    private WebElement inputComments;

    @FindBy(id = "int")
    private WebElement inputInt;

    public FieldsEdit1Page(WebDriver driver) {
        super(driver);
    }

    @Override
    protected Fields1Page createPage() {
        return new Fields1Page(getDriver());
    }

    public FieldsEdit1Page sendKeys(String title, String comments, String int_){
        fill(getWait(), inputTitle, title);
        fill(getWait(), inputComments, comments);
        fill(getWait(), inputInt, int_);
        return this;
    }
}
