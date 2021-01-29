package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public final class CalendarEditPage extends BasePage {

    @FindBy(xpath = "//input[contains(@name, 'string')]")
    private WebElement inputTitle;

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

    public CalendarEditPage sendKeys(String STRING, String NUMBER, String NUMBER1, String DATE) {
        ProjectUtils.sendKeys(inputTitle, STRING);
        ProjectUtils.sendKeys(inputInt, NUMBER);
        ProjectUtils.sendKeys(inputDecimal, NUMBER1);
        ProjectUtils.sendKeys(inputDate, DATE);

        return this;
    }

    public CalendarEditPage clickDataTime() {
        inputDateTime.click();
        inputDateTime.click();
        return this;
    }
    public CalendarPage clickSaveButton() {
        ProjectUtils.click(getDriver(), buttonSave);
        return new CalendarPage(getDriver());
    }
}
