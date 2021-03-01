package model.entity.edit;

import model.base.EntityBaseEditExtPage;
import model.entity.table.AssignPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AssignEditPage extends EntityBaseEditExtPage<AssignPage, AssignEditPage> {

    public AssignEditPage(WebDriver driver) {
        super(driver);
    }

    public AssignEditPage fillOutForm(String string, String text, String int_, String decimal) {
        inputString.sendKeys(string);
        inputText.sendKeys(text);
        inputInt.sendKeys(int_);
        inputDecimal.sendKeys(decimal);
        inputDate.click();
        inputDateTime.click();
        inputDateTime.sendKeys(Keys.ENTER);

        return this;
    }

    public String getDate() {
        return inputDate.getAttribute("value");
    }

    public String getDateTime() {
        return inputDateTime.getAttribute("value");
    }

    @Override
    protected AssignPage createPage() {
        return new AssignPage(getDriver());
    }

    @Override
    protected AssignEditPage createEditPage() {
        return new AssignEditPage(getDriver());
    }
}

