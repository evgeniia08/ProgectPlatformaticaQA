package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.PlaceholderPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;
import static runner.ProjectUtils.fill;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public final class PlaceholderEditPage extends EntityBaseEditPage<PlaceholderPage> {

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

    @FindBy(css = "select#user > option")
    private List<WebElement> selectUserAllUsers;

    @FindBy(css = "div[class$=inner-inner]")
    private WebElement selectedUser;

    @FindBy(css = "select#user")
    private WebElement selectUser;

    @FindBy(xpath = "//div[contains(text(),'Demo')]")
    private WebElement userSelection;

    @FindBy(xpath = "//span[text()='User 2']")
    private WebElement newUser;

    public PlaceholderEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PlaceholderPage createPage() {
        return new PlaceholderPage(getDriver());
    }

    public PlaceholderEditPage fillTitle(String title) {
        fill(getWait(), inputString, title);
        return this;
    }

    public PlaceholderEditPage fillComments(String comments) {
        fill(getWait(), inputText, comments);
        return this;
    }

    public PlaceholderEditPage fillInt(String int_) {
        fill(getWait(), inputInt, int_);
        return this;
    }

    public PlaceholderEditPage fillDecimal(String decimal) {
        fill(getWait(), inputDecimal, decimal);
        return this;
    }

    public PlaceholderEditPage fillDate(String date) {
        fill(getWait(), inputDate, date);
        return this;
    }

    public PlaceholderEditPage fillDateTime(String dateTime) {
        fill(getWait(), inputDateTime, dateTime);
        return this;
    }

    public PlaceholderEditPage fillOutForm(String title, String comments, String int_, String decimal, String date, String datetime) {
        fill(getWait(), inputString, title);
        fill(getWait(), inputText, comments);
        fill(getWait(), inputInt, int_);
        fill(getWait(), inputDecimal, decimal);
        fill(getWait(), inputDate, date);
        fill(getWait(), inputDateTime, datetime);
        return this;
    }

    public PlaceholderEditPage sendKeys(String title,String comments,String int_,String decimal){
        fill(getWait(), inputString, title);
        fill(getWait(), inputText, comments);
        fill(getWait(), inputInt, int_);
        fill(getWait(), inputDecimal, decimal);
        return this;
    }

    public String getRandomUser() {
        List<WebElement> userList = selectUserAllUsers;
        String randomUser = userList.get(ThreadLocalRandom.current().nextInt(1, userList.size())).getText();

        return randomUser;
    }

    public PlaceholderEditPage selectUser(String user) {
        WebElement userText = getWait().until(ExpectedConditions.visibilityOf(selectedUser));
        getActions().moveToElement(userText).perform();
        new Select(selectUser).selectByVisibleText(user);
        return this;
    }

    public PlaceholderEditPage selectUser(){
        ProjectUtils.click(getDriver(),userSelection);
        ProjectUtils.click(getDriver(),newUser);
        return this;
    }

    public List<String> getDefaultValues() {
        List<String> defaultPlaceholderValues = new ArrayList<>();
        defaultPlaceholderValues.add(inputString.getAttribute("placeholder"));
        defaultPlaceholderValues.add(inputText.getAttribute("placeholder"));
        defaultPlaceholderValues.add(inputInt.getAttribute("placeholder"));
        defaultPlaceholderValues.add(inputDecimal.getAttribute("placeholder"));
        defaultPlaceholderValues.add(inputDate.getAttribute("placeholder"));
        defaultPlaceholderValues.add(inputDateTime.getAttribute("placeholder"));
        defaultPlaceholderValues.add("");
        defaultPlaceholderValues.add("");
        defaultPlaceholderValues.add("User 1 Demo");
        return defaultPlaceholderValues;
    }

    public PlaceholderEditPage fillOutFormDefaultValues() {
        fill(getWait(), inputString, getDefaultValues().get(0));
        fill(getWait(), inputText, getDefaultValues().get(1));
        fill(getWait(), inputInt, getDefaultValues().get(2));
        fill(getWait(), inputDecimal, getDefaultValues().get(3));
        fill(getWait(), inputDate, getDefaultValues().get(4));
        fill(getWait(), inputDateTime, getDefaultValues().get(5));
        return this;
    }
}
