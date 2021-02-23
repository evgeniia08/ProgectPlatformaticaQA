package model.entity.edit;

import model.base.EntityBaseEditExtPage;
import model.entity.table.FieldsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static runner.ProjectUtils.fill;
import java.util.List;

public final class FieldsEditPage extends EntityBaseEditExtPage<FieldsPage, FieldsEditPage> {

    @FindBy(id = "title")
    private WebElement inputTitle;

    @FindBy(id = "comments")
    private WebElement inputComments;

    @FindBy(id = "int")
    private WebElement inputInt;

    @FindBy(id = "decimal")
    private WebElement inputDecimal;

    @FindBy(id = "date")
    private WebElement inputDate;

    @FindBy(id = "datetime")
    private WebElement inputDateTime;

    @FindBy(css = "select#user > option")
    private List<WebElement> selectUserAllUsers;

    @FindBy(css = "div[class$=inner-inner]")
    private WebElement selectedUser;

    @FindBy(css = "select#user")
    private WebElement selectUser;

    public FieldsEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FieldsPage createPage() {
        return new FieldsPage(getDriver());
    }

    @Override
    protected FieldsEditPage createEditPage() {
        return new FieldsEditPage(getDriver());
    }

    public FieldsEditPage fillTitle(String title) {
        fill(getWait(), inputTitle, title);
        return this;
    }

    public FieldsEditPage fillComments(String comments) {
        fill(getWait(), inputComments, comments);
        return this;
    }

    public FieldsEditPage fillInt(String int_) {
        fill(getWait(), inputInt, int_);
        return this;
    }

    public FieldsEditPage fillDecimal(String decimal) {
        fill(getWait(), inputDecimal, decimal);
        return this;
    }

    public FieldsEditPage fillDate(String date) {
        fill(getWait(), inputDate, date);
        return this;
    }

    public FieldsEditPage fillDateTime(String dateTime) {
        fill(getWait(), inputDateTime, dateTime);
        return this;
    }

    public FieldsEditPage sendKeys(String title, String comments, String int_, String decimal, String date, String datetime) {
        fill(getWait(), inputTitle, title);
        fill(getWait(), inputComments, comments);
        fill(getWait(), inputInt, int_);
        fill(getWait(), inputDecimal, decimal);
        fill(getWait(), inputDate, date);
        fill(getWait(), inputDateTime, datetime);
        return this;
    }
}
