package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.FootersPageEntityBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import java.util.List;

public class FootersEntityBaseEditPage extends EntityBaseEditPage<FootersPageEntityBase> {

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

    public FootersEntityBaseEditPage(WebDriver driver) {
        super(driver);
    }

    public FootersEntityBaseEditPage clickNewFolder() {
        footersFolder.click();
        return this;
    }

    public FootersEntityBaseEditPage clickPlusSumButton() {
        plusSumFtrsButton.click();
        return this;
    }

    public FootersEntityBaseEditPage clickPlusMinButton() {
        plusMinFtrsButton.click();
        return this;
    }

    public FootersEntityBaseEditPage clickPlusMaxButton() {
        plusMaxFtrsButton.click();
        return this;
    }

    public FootersEntityBaseEditPage clickPlusCountButton() {
        plusCountFtrsButton.click();
        return this;
    }

    public FootersEntityBaseEditPage clickPlusAverageButton() {
        plusAverageFtrsButton.click();
        return this;
    }

    public int getSumFtrsRowCount() {
        return sumFtrsRows.size();
    }

    public List<WebElement> getSumFtrsRows() {
        return sumFtrsRows;
    }

    public FootersEntityBaseEditPage fillSumFtrsRowData(int rowNumber, int int_, double decimal) {
        WebElement row = getSumFtrsRows().get(rowNumber);
        ProjectUtils.fill(getWait(), row.findElement(By.cssSelector("td > textarea[id$=int]")), Integer.toString(int_));
        ProjectUtils.fill(getWait(), row.findElement(By.cssSelector("td > textarea[id$=decimal]")), Double.toString(decimal));
        sumFtrsControl.click();
        return this;
    }

    public FootersEntityBaseEditPage waitSumFtrsToBe(String numbers) {
        getWait().until(d -> sumFtrsControl.getAttribute("value").contains(numbers));
        return this;
    }

    public FootersEntityBaseEditPage waitSumFtrsControlToBe(String numbers) {
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
    protected FootersPageEntityBase createPage() {
        return new FootersPageEntityBase(getDriver());
    }
}
