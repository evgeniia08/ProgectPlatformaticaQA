package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import java.util.List;

public class FootersEditPage extends BaseEditPage<FootersPage> {

    @FindBy(css = "tr#add-row-70 button")
    private WebElement plusSumFtrsButton;

    @FindBy(css = "tr#add-row-71 button")
    private WebElement plusMinFtrsButton;

    @FindBy(css = "tr#add-row-72 button")
    private WebElement plusMaxFtrsButton;

    @FindBy(css = "tr#add-row-73 button")
    private WebElement plusCountFtrsButton;

    @FindBy(css = "tr#add-row-74 button")
    private WebElement plusAverageFtrsButton;

    @FindBy(css = "tr[id^=row-70-]")
    private List<WebElement> sumFtrsRows;

    @FindBy(css = "#f-70-control")
    private WebElement sumFtrsControl;

    @FindBy(css = "#sum_control")
    private WebElement sumControl;

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement footersFolder;

    public FootersEditPage(WebDriver driver) {
        super(driver);
    }

    public FootersEditPage clickNewFolder() {
        footersFolder.click();
        return this;
    }

    public FootersEditPage clickPlusSumButton() {
        plusSumFtrsButton.click();
        return this;
    }

    public FootersEditPage clickPlusMinButton() {
        plusMinFtrsButton.click();
        return this;
    }

    public FootersEditPage clickPlusMaxButton() {
        plusMaxFtrsButton.click();
        return this;
    }

    public FootersEditPage clickPlusCountButton() {
        plusCountFtrsButton.click();
        return this;
    }

    public FootersEditPage clickPlusAverageButton() {
        plusAverageFtrsButton.click();
        return this;
    }

    public int getSumFtrsRowCount() {
        return sumFtrsRows.size();
    }

    public List<WebElement> getSumFtrsRows() {
        return sumFtrsRows;
    }

    public FootersEditPage fillSumFtrsRowData(int rowNumber, int int_, double decimal) {
        WebElement row = getSumFtrsRows().get(rowNumber);
        ProjectUtils.fill(getWait(), row.findElement(By.cssSelector("td > textarea[id$=int]")), Integer.toString(int_));
        ProjectUtils.fill(getWait(), row.findElement(By.cssSelector("td > textarea[id$=decimal]")), Double.toString(decimal));
        sumFtrsControl.click();
        return this;
    }

    public FootersEditPage waitSumFtrsToBe(String numbers) {
        getWait().until(d -> sumFtrsControl.getAttribute("value").contains(numbers));
        return this;
    }

    public FootersEditPage waitSumFtrsControlToBe(String numbers) {
        getWait().until(d -> sumControl.getAttribute("value").contains(numbers));
        return this;
    }

    public String getSumControl() {
        return sumControl.getAttribute("value");
    }

    public String getSumFtrsControl() {
        return sumFtrsControl.getAttribute("value");
    }

    @Override
    protected FootersPage createPage() {
        return new FootersPage(getDriver());
    }
}
