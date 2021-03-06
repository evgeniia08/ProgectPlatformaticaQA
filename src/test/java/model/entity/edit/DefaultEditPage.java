package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.DefaultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultEditPage extends EntityBaseEditPage<DefaultPage> {

    @FindBy(id = "string")
    private WebElement fieldString;

    @FindBy(id = "text")
    private WebElement fieldText;

    @FindBy(id = "int")
    private WebElement fieldInt;

    @FindBy(id = "decimal")
    private WebElement fieldDecimal;

    @FindBy(id = "date")
    private WebElement fieldDate;

    @FindBy(id = "datetime")
    private WebElement fieldDateTime;

    @FindBy(xpath = "//button[@data-id = 'user']")
    private WebElement fieldUser;

    @FindBy(xpath = "//select[@id = 'user']")
    private WebElement buttonUser;

    private DefaultEmbeddedEditPage embededTable;

    private void sendKeys(WebElement element, String newValue){
        element.clear();
        element.sendKeys(newValue);
        element.sendKeys("\t");
    }

    @Override
    protected DefaultPage createPage() {
        return new DefaultPage(getDriver());
    }

    public DefaultEditPage(WebDriver driver) {
        super(driver);
        embededTable = new DefaultEmbeddedEditPage(driver);
    }

    @Override
    public DefaultPage clickSaveButton() {
        ProjectUtils.click(getDriver(), saveButton);
        return createPage();
    }

    public DefaultEditPage sendKeys(String string, String text, String int_, String decimal, String date,
                         String dateTime, String user) {
        sendKeys(fieldString, string);
        sendKeys(fieldText, text);
        sendKeys(fieldInt, int_);
        sendKeys(fieldDecimal, decimal);
        sendKeys(fieldDate, date);
        sendKeys(fieldDateTime, dateTime);
        Select userSelect = new Select(buttonUser);
        userSelect.selectByVisibleText(user);
        return this;
    }

    public List<String> toList() {

        List<String> result = new ArrayList<>();
        result.add(fieldString.getAttribute("value"));
        result.add(fieldText.getText());
        result.add(fieldInt.getAttribute("value"));
        result.add(fieldDecimal.getAttribute("value"));
        result.add(fieldDate.getAttribute("value"));
        result.add(fieldDateTime.getAttribute("value"));
        result.add(fieldUser.getText());
        return result;
    }

    public DefaultEmbeddedEditPage getEmbededTable() {
        return embededTable;
    }
}
