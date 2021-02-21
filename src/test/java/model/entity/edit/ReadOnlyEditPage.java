package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.ReadOnlyPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReadOnlyEditPage extends EntityBaseEditPage<ReadOnlyPage> {

    @FindBy(xpath = "//input[@id='string']")
    private WebElement string;

    @FindBy(xpath = "//textarea[@id='text']")
    private WebElement text;

    @FindBy(xpath = "//input[@id='decimal']")
    private WebElement decimal;

    @FindBy(xpath = "//input[@id='int']")
    private WebElement _int;

    @FindBy(xpath = "//input[@id='date']")
    private WebElement date;

    @FindBy(xpath = "//input[@id='datetime']")
    private WebElement dateTime;

    @FindBy(xpath = "//button[contains(text(),'+')]")
    private WebElement buttonAddEmbedRec;

    @Override
    protected ReadOnlyPage createPage() {
        return new ReadOnlyPage(getDriver());
    }

    public ReadOnlyEditPage(WebDriver driver) {
        super(driver);
    }

    public boolean isStringReadOnly() {
        return Boolean.parseBoolean(string.getAttribute("readonly"));
    }

    public boolean getStringIsReadOnly() {
        return Boolean.parseBoolean(string.getAttribute("readonly"));
    }

    public boolean getTextIsReadOnly() {
        return Boolean.parseBoolean(text.getAttribute("readonly"));
    }

    public boolean getDecimalIsReadOnly() {
        return Boolean.parseBoolean(decimal.getAttribute("readonly"));
    }

    public boolean getIntIsReadOnly() {
        return Boolean.parseBoolean(_int.getAttribute("readonly"));
    }

    public boolean getDateIsReadOnly() {
        return Boolean.parseBoolean(date.getAttribute("readonly"));
    }

    public boolean getDateTimeIsReadOnly() {
        return Boolean.parseBoolean(dateTime.getAttribute("readonly"));
    }

    public boolean isButtonAddEmbededDisabled() {
        return Boolean.parseBoolean(buttonAddEmbedRec.getAttribute("disabled"));
    }
}
