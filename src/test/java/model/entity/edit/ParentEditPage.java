package model.entity.edit;


import model.base.EntityBaseEditPage;
import model.entity.table.ParentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import static runner.ProjectUtils.fill;

public final class ParentEditPage extends EntityBaseEditPage<ParentPage> {

    @FindBy(xpath = "//input[contains(@name, 'string')]")
    private WebElement inputTitle;

    @FindBy(xpath = "//span//textarea[@id='text']")
    private WebElement inputComments;

    @FindBy(xpath = "//input[contains(@name, 'int')]")
    private WebElement inputInt;

    @FindBy(xpath = "//input[contains(@name, 'decimal')]")
    private WebElement inputDecimal;

    @FindBy(xpath = "//input[contains(@name, 'date')]")
    private WebElement inputDate;

    @FindBy(xpath = "//input[contains(@name, 'datetime')]")
    private WebElement inputDateTime;

    @FindBy(xpath = "//button[@data-id='user']/div/div")
    private WebElement inputUser;

    @FindBy(xpath = ("//button[text() = 'Save']"))
    private WebElement buttonSave;

    public ParentEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ParentPage createPage() {
        return new ParentPage(getDriver());
    }

    public ParentEditPage fillOutParentForm(String STRING, String COMMENT, String INT_, String DECIMAL, String DATE, String DATE_TIME) {
        ProjectUtils.fill(getWait(), inputTitle, STRING);
        ProjectUtils.fill(getWait(), inputComments, COMMENT);
        ProjectUtils.fill(getWait(), inputInt, INT_);
        ProjectUtils.fill(getWait(), inputDecimal, DECIMAL);
        ProjectUtils.fill(getWait(), inputDate, DATE);
        ProjectUtils.fill(getWait(), inputDateTime, DATE_TIME);
        return this;
    }

    public ParentEditPage clickDataTime() {
        inputDateTime.click();
        inputDateTime.click();
        return this;
    }

    public ParentEditPage sendKeys1(String DRAG_THE_ROW_UP) {
        fill(getWait(), inputTitle, DRAG_THE_ROW_UP);
        return this;
    }

    public ParentEditPage sendKeys2(String DRAG_THE_ROW_DOWN) {
        fill(getWait(), inputTitle, DRAG_THE_ROW_DOWN);
        return this;
    }

    public ParentPage clickSaveButton() {
        ProjectUtils.click(getDriver(), buttonSave);
        return new ParentPage(getDriver());
    }
}

