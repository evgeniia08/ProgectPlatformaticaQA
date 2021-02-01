package model.entity.edit;
import model.BaseEditPage;
import model.entity.table.ChildRecordsLoopPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import java.util.List;
import java.util.Random;

public class ChildRecordsLoopEditPage extends BaseEditPage<ChildRecordsLoopPage> {

    @FindBy(xpath = "//i[contains(text(), 'create_new_folder')]/ancestor::a")
    private WebElement createNew;

    @FindBy(xpath = "//p[contains (text(), 'Child records loop')]/parent::a")
    private static WebElement childRecordsLoop;

    @FindBy(xpath = "//button[@class='btn btn-round btn-sm btn-primary dropdown-toggle']")
    private static WebElement recordMenu;

    @FindBy(xpath = "//button[@id='pa-entity-form-save-btn']")
    private static WebElement saveBtn;

    @FindBy(xpath = "//input[@id='end_balance']")
    private static WebElement endBalance;

    @FindBy(xpath = "//tr//textarea[@id='t-68-r-9-amount']")
    private static WebElement lastLine;

    @FindBy(xpath = "//input[@id='start_balance']")
    private static WebElement startBalanceField;

    @FindBy(xpath = "//button[@data-table_id='68']")
    private static WebElement greenPlus;

    @FindBy(xpath = "//textarea[@class='pa-entity-table-textarea pa-table-field t-68-amount']")
    private static List<WebElement> tableLines;

    public ChildRecordsLoopEditPage(WebDriver driver) {
        super(driver);
    }

    public ChildRecordsLoopEditPage clickNewFolder() {
        createNew.click();
        return this;
    }

    public static ChildRecordsLoopEditPage tableLinesSendValues(int n, String value){
        tableLines.get(n).clear();
        tableLines.get(n).sendKeys(value);
        return null;
    }

    public static int randomIntGeneration(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static ChildRecordsLoopEditPage addingRowsByClickingOnGreenPlus(WebDriver driver, int n) {
        for (int i = 1; i <= n; i++) {
            ProjectUtils.click(driver, greenPlus);
        }
        return null;
     }

    public ChildRecordsLoopEditPage startSendKeys(String newValue) {
        startBalanceField.clear();
        startBalanceField.sendKeys(newValue);
        getWait().until(d -> (endBalance.getAttribute("value").equals(startBalanceField.getAttribute("value"))));
        return this;
    }

    public static String checkEndBalance() {
        return endBalance.getAttribute("value");
    }

    public static String checkStartFieldBalance() {
        return startBalanceField.getAttribute("value");
    }

    public static String checkLastElement() { return lastLine.getAttribute("value"); }

    public static int getTableLinesSize() {
        return tableLines.size();
    }

    public ChildRecordsLoopEditPage createNewChildLoopEmptyRecord(int n) {
        ProjectUtils.click(getDriver(), childRecordsLoop);
        ProjectUtils.click(getDriver(), createNew);
        addingRowsByClickingOnGreenPlus(getDriver(), n);
        return this;
    }

    public static ChildRecordsLoopEditPage fillData(WebDriver driver, String xpath, String valueSend) {
        WebElement line = driver.findElement(By.xpath(xpath));
        line.clear();
        line.sendKeys(valueSend);
        return null;
    }


    public static ChildRecordsLoopEditPage deleteRows(WebDriver driver, int rowNumber) {
        WebElement deleteLine = driver.findElement(By.xpath(String.format("//i[@data-row= '%d'  and contains(text(), 'clear')]", rowNumber)));
        ProjectUtils.click(driver, deleteLine);
        return null;
    }

    public static ChildRecordsLoopEditPage clickSaveBtn(WebDriver driver) {
        ProjectUtils.click(driver, saveBtn);
        return null;
    }

    @Override
    protected ChildRecordsLoopPage createPage() {
        return new ChildRecordsLoopPage(getDriver());
    }
}
