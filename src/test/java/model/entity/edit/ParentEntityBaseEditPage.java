package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.ParentPageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public final class ParentEntityBaseEditPage extends EntityBaseEditPage<ParentPageEntityBase> {

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

    public ParentEntityBaseEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ParentPageEntityBase createPage() {
        return new ParentPageEntityBase(getDriver());
    }

    public ParentEntityBaseEditPage sendKeys(String STRING, String COMMENT, String INT_, String DECIMAL, String DATE) {
        ProjectUtils.fill(getWait(), inputTitle, STRING);
        ProjectUtils.fill(getWait(), inputComments, COMMENT);
        ProjectUtils.fill(getWait(), inputInt, INT_);
        ProjectUtils.fill(getWait(), inputDecimal, DECIMAL);
        ProjectUtils.fill(getWait(), inputDate, DATE);

        return this;
    }

    public ParentEntityBaseEditPage clickDataTime() {
        inputDateTime.click();
        inputDateTime.click();

        return this;
    }

    public ParentPageEntityBase clickSaveButton() {
        ProjectUtils.click(getDriver(), buttonSave);

        return new ParentPageEntityBase(getDriver());
    }
}
