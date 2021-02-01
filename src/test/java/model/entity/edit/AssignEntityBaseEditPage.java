package model.entity.edit;

import model.entity.table.AssignPageEntityBase;
import model.base.EntityBaseEditPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssignEntityBaseEditPage extends EntityBaseEditPage<AssignPageEntityBase> {

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

    public AssignEntityBaseEditPage(WebDriver driver) {
        super(driver);
    }

    public AssignEntityBaseEditPage fillOutForm(String string, String text, String int_, String decimal) {
        inputString.sendKeys(string);
        inputText.sendKeys(text);
        inputInt.sendKeys(int_);
        inputDecimal.sendKeys(decimal);
        inputDate.click();
        inputDateTime.click();
        inputDateTime.sendKeys(Keys.ENTER);

        return this;
    }

    @Override
    protected AssignPageEntityBase createPage() {
        return new AssignPageEntityBase(getDriver());
    }
}

