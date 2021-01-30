package model.entity.edit;

import model.BaseEditPage;
import model.entity.table.ParentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public final class ParentEditPage extends BaseEditPage<ParentPage> {

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

    public ParentEditPage sendKeys(String STRING, String COMMENT, String INT_, String DECIMAL, String DATE) {
        ProjectUtils.sendKeys(inputTitle, STRING);
        ProjectUtils.sendKeys(inputComments, COMMENT);
        ProjectUtils.sendKeys(inputInt, INT_);
        ProjectUtils.sendKeys(inputDecimal, DECIMAL);
        ProjectUtils.sendKeys(inputDate, DATE);

        return this;
    }

    public ParentEditPage clickDataTime() {
        inputDateTime.click();
        inputDateTime.click();

        return this;
    }

    public ParentPage clickSaveButton() {
        ProjectUtils.click(getDriver(), buttonSave);

        return new ParentPage(getDriver());
    }

    public ParentEditPage sendKeysForEdit(String STRING, String COMMENTS, String INT_, String DECIMAL, String DATE){
        inputTitle.clear();
        inputComments.clear();
        inputInt.clear();
        inputDecimal.clear();
        inputDate.clear();
        inputDateTime.clear();

        ProjectUtils.sendKeys(inputTitle, STRING);
        ProjectUtils.sendKeys(inputComments, COMMENTS);
        ProjectUtils.sendKeys(inputInt, INT_);
        ProjectUtils.sendKeys(inputDecimal, DECIMAL);
        ProjectUtils.sendKeys(inputDate, DATE);

        return new ParentEditPage(getDriver());
    }
}
