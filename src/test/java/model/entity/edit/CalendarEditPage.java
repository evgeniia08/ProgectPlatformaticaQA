package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.CalendarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public final class CalendarEditPage extends EntityBaseEditPage<CalendarPage> {

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

    @FindBy(xpath = ("//button[text() = 'Save']"))
    private WebElement buttonSave;

    public CalendarEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CalendarPage createPage() {
        return new CalendarPage(getDriver());
    }

    public CalendarEditPage sendKeys(String STRING, String NUMBER, String NUMBER1, String DATE) {
        ProjectUtils.fill(getWait(), inputTitle, STRING);
        ProjectUtils.fill(getWait(), inputInt, NUMBER);
        ProjectUtils.fill(getWait(), inputDecimal, NUMBER1);
        ProjectUtils.fill(getWait(), inputDate, DATE);

        return this;
    }

    public CalendarEditPage fillOutCalendarForm(String STRING, String COMMENT, String INT_, String DECIMAL, String DATE, String DATE_TIME) {
        ProjectUtils.fill(getWait(), inputTitle, STRING);
        ProjectUtils.fill(getWait(), inputComments, COMMENT);
        ProjectUtils.fill(getWait(), inputInt, INT_);
        ProjectUtils.fill(getWait(), inputDecimal, DECIMAL);
        ProjectUtils.fill(getWait(), inputDate, DATE);
        ProjectUtils.fill(getWait(), inputDateTime, DATE_TIME);

        return this;
    }
}
