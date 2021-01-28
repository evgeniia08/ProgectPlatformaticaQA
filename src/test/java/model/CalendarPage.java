package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;
import java.util.List;
import java.util.stream.Collectors;

public final class CalendarPage extends BaseTablePage<CalendarPage, CalendarEditPage> {

    @FindBy(xpath = ("//div[2]/div[1]//div[1]/div/ul/li[2]/a"))
    private WebElement clickList;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> rowList;

    @FindBy(xpath = "//table[@id = 'pa-all-entities-table']/tbody")
    private WebElement table;

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected CalendarEditPage createEditPage() {
        return new CalendarEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 10);
    }

    public CalendarPage clickThisList() {
        clickList.click();
        ProjectUtils.click(getDriver(), getWait().until(ExpectedConditions.elementToBeClickable(clickList)));
        clickList.click();
        return new CalendarPage(getDriver());
    }

    public int getRowCount(){
        return rowList.size();
    }

    public String getTitleText(){
        return table.findElement(By.xpath("//tr/td[2]/a/div")).getText();
    }

    public String getNumberText(){
        return table.findElement(By.xpath("//tr/td[4]/a/div")).getText();
    }

    public String getNumber1Text(){
        return table.findElement(By.xpath("//tr/td[5]/a/div")).getText();
    }

    public String getDataText(){
        return table.findElement(By.xpath("//tr/td[6]/a/div")).getText();
    }
}

