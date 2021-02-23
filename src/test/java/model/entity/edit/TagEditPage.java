package model.entity.edit;

import model.base.EntityBaseEditExtPage;
import model.entity.table.TagListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class TagEditPage extends EntityBaseEditExtPage<TagListPage, TagEditPage> {

    @FindBy(id = "string")
    private WebElement inputString;

    @FindBy(id = "text")
    private WebElement inputText;

    @FindBy(id = "int")
    private WebElement inputInt;

    @FindBy(id = "decimal")
    private WebElement inputDecimal;

    @FindBy(id = "date")
    private WebElement inputDate;

    @FindBy(id = "datetime")
    private WebElement inputDateTime;

    public TagEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected TagListPage createPage() {
        return new TagListPage(getDriver());
    }

    @Override
    protected TagEditPage createEditPage() {
        return new TagEditPage(getDriver());
    }

    public TagEditPage fillOutTagEditForm(String string, String text, String int_, String decimal, String date,
                                          String datetime, String user) {
        ProjectUtils.fill(getWait(), inputString, string);
        ProjectUtils.fill(getWait(), inputText, text);
        ProjectUtils.fill(getWait(), inputInt, int_);
        ProjectUtils.fill(getWait(), inputDecimal, decimal);
        ProjectUtils.fill(getWait(), inputDate, date);
        ProjectUtils.fill(getWait(), inputDateTime, datetime);
        selectUser(user);
        return this;
    }
}
