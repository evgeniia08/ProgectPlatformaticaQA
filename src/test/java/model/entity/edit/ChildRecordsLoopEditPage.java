package model.entity.edit;
import model.base.EntityBaseEditPage;
import model.entity.table.ChildRecordsLoopPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import java.util.List;
import java.util.Random;

public class ChildRecordsLoopEditPage extends EntityBaseEditPage<ChildRecordsLoopPage> {

    @FindBy(xpath = "//i[contains(text(), 'create_new_folder')]/ancestor::a")
    private WebElement createNew;

    @FindBy(xpath = "//p[contains (text(), 'Child records loop')]/parent::a")
    private WebElement childRecordsLoop;

    @FindBy(xpath = "//button[@class='btn btn-round btn-sm btn-primary dropdown-toggle']")
    private WebElement recordMenu;

    @FindBy(xpath = "//input[@id='end_balance']")
    private WebElement endBalance;

    @FindBy(xpath = "//tr//textarea[@id='t-68-r-9-amount']")
    private WebElement lastLine;

    @FindBy(xpath = "//input[@id='start_balance']")
    private WebElement startBalanceField;

    @FindBy(xpath = "//button[@data-table_id='68']")
    private WebElement greenPlus;

    @FindBy(xpath = "//textarea[@class='pa-entity-table-textarea pa-table-field t-68-amount valid']")
    private List<WebElement> tableLines;

    @FindBy(xpath = "//td/a")
    private List<WebElement> balances;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> all;

    public ChildRecordsLoopEditPage(WebDriver driver) {
        super(driver);
    }

   public ChildRecordsLoopEditPage tableLinesSendValues(int n, String value) {
        tableLines.get(n).clear();
        tableLines.get(n).sendKeys(value);
        return this;
    }

    public String[] getBalance() {
        String[] balance = {balances.get(0).getText(), balances.get(1).getText()};
        return balance;
    }

    public int randomIntGeneration(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public ChildRecordsLoopEditPage addingRowsByClickingOnGreenPlus(WebDriver driver, int n) {
        for (int i = 1; i <= n; i++) {
            ProjectUtils.click(driver, greenPlus);
        }
        return this;
    }

    public ChildRecordsLoopEditPage startSendKeys(String newValue) {
        startBalanceField.clear();
        startBalanceField.sendKeys(newValue);
        getWait().until(d -> (endBalance.getAttribute("value").equals(startBalanceField.getAttribute("value"))));
        return this;
    }

    public String[] checkStartEndBalance() {
        String[] startAndEndBalance = {startBalanceField.getAttribute("value"), endBalance.getAttribute("value")};

        return startAndEndBalance;
    }

    public String checkEndBalance() {
        return endBalance.getAttribute("value");
    }

    public String checkLastElement() {
        return lastLine.getAttribute("value");
    }

    public int getTableLinesSize() {
        return tableLines.size();
    }

    public ChildRecordsLoopEditPage createNewChildLoopEmptyRecord(WebDriver driver, int n) {
        ProjectUtils.click(driver, childRecordsLoop);
        ProjectUtils.click(driver, createNew);
        addingRowsByClickingOnGreenPlus(driver, n);
        return this;
    }

    public ChildRecordsLoopEditPage fillData(String xpath, String valueSend) {
        WebElement line = getDriver().findElement(By.xpath(xpath));
        line.clear();
        line.sendKeys(valueSend);
        return this;
    }

    public ChildRecordsLoopEditPage fillWithLoop(double[] valuePassed, int max) {
        for (int i = 1; i < max; i++) {
            fillData(String.format("//tr//textarea[@id='t-68-r-%d-amount']", i), String.valueOf(valuePassed[i]));
        }
        return this;
    }

    public ChildRecordsLoopEditPage deleteRows(int rowNumber) {
        WebElement deleteLine = getDriver().findElement(By.xpath(String.format("//i[@data-row= '%d'  and contains(text(), 'clear')]", rowNumber)));
        ProjectUtils.click(getDriver(), deleteLine);
        return this;
    }

    public ChildRecordsLoopEditPage waitForEndBalanceMatchWith(String startBalance) {
        getWait().until(d -> (checkEndBalance().equals(startBalance)));
        return this;
    }

    @Override
    protected ChildRecordsLoopPage createPage() {
        return new ChildRecordsLoopPage(getDriver());
    }
}
