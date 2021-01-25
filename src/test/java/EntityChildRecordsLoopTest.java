import com.beust.jcommander.Strings;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import org.openqa.selenium.*;
import runner.type.Run;
import runner.type.RunType;
import runner.ProjectUtils;

import java.util.List;
import java.util.Random;


@Run(run = RunType.Multiple)
public class EntityChildRecordsLoopTest extends BaseTest {

    private static final By BY_CREATE_NEW = By.xpath("//i[contains(text(), 'create_new_folder')]/ancestor::a");
    private static final By CHILD_RECORD_LOOP = By.xpath("//p[contains (text(), 'Child records loop')]/parent::a");
    private static final By CHILD_RECORD = By.xpath("//p[contains(text(), 'Child records loop')]/ancestor::a");
    private static final By RECORD_MENU = By.xpath("//button[@class='btn btn-round btn-sm btn-primary dropdown-toggle']");
    private static final By SAVE_BTN = By.xpath("//button[@id='pa-entity-form-save-btn']");
    private static final By END_BALANCE = By.xpath("//input[@id='end_balance']");
    private static final By LAST_LINE = By.xpath("//tr//textarea[@id='t-68-r-9-amount']");
    private static final By VIEW_FUNCTION = By.xpath("//a[text() = 'view']");
    private static final By EDIT_FUNCTION = By.xpath("//a[text() = 'edit']");
    private static final By START_BALANCE_FIELD = By.xpath("//input[@id='start_balance']");
    private static final By END_BALANCE_FIELD = By.xpath("//input[@id='end_balance']");
    private static final By GREEN_PLUS = By.xpath("//button[@data-table_id='68']");

    private static final String START_BALANCE = "1";
    private static final String VALUE_9 = "6";
    private static int endBalanceD;

    private static final int NUMBERS_OF_LINES = 9;
    private static double sumNumber = 0;
    private static final double[] FIRST_VALUES_PASSED = {0.00, 10.50, 11.00, 12.00, 13.00, 14.00, 1.00, 1.00, 2.50, 0.0};

    private void addingRowsByClickingOnGreenPlus(int n) {
        for (int i = 1; i <= n; i++) {
            ProjectUtils.click(getDriver(), getDriver().findElement(GREEN_PLUS));
        }
    }

    private int randomIntGeneration(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    private void createNewChildLoopEmptyRecord() {
        ProjectUtils.click(getDriver(), getDriver().findElement(CHILD_RECORD_LOOP));
        ProjectUtils.click(getDriver(), getDriver().findElement(BY_CREATE_NEW));
        addingRowsByClickingOnGreenPlus(NUMBERS_OF_LINES);
    }

    private void fillData(String xpath, String valueSend) {
        WebElement line = getDriver().findElement(By.xpath(xpath));
        line.clear();
        line.sendKeys(valueSend);
        getWebDriverWait().until(d -> !Strings.isStringEmpty(line.getAttribute("value"))
                && !line.getAttribute("value").equals("0"));
    }

    private void deleteRows(int rowNumber) {
        WebElement deleteLine = getDriver().findElement(By.xpath(String.format("//i[@data-row= '%d'  and contains(text(), 'clear')]", rowNumber)));
        ProjectUtils.click(getDriver(), deleteLine);
        getWebDriverWait().until(d -> !deleteLine.isDisplayed());
    }

    private void goToChildLoop() {
        ProjectUtils.click(getDriver(), getDriver().findElement(CHILD_RECORD));
        ProjectUtils.click(getDriver(), getDriver().findElement(RECORD_MENU));
    }

    @Test
    public void checkStartEndBalanceBeforeSave() {

        createNewChildLoopEmptyRecord();

        getDriver().findElement(START_BALANCE_FIELD).sendKeys(START_BALANCE);

        getWebDriverWait().until(d -> getDriver().findElement(END_BALANCE).getAttribute("value").equals(START_BALANCE));

        sumNumber += Integer.parseInt(START_BALANCE);

        for (int i = 0; i < FIRST_VALUES_PASSED.length; i++) {
            sumNumber += FIRST_VALUES_PASSED[i];
        }

        for (int i = 1; i < 10; i++) {
            fillData(String.format("//tr//textarea[@id='t-68-r-%d-amount']", i), String.valueOf(FIRST_VALUES_PASSED[i]));
        }

        getWebDriverWait().until(d -> getDriver().findElement(LAST_LINE).getAttribute("value").equals(String.valueOf(FIRST_VALUES_PASSED[9])));

        List<WebElement> tableLines = getDriver().findElements(By.xpath("//textarea[@class='pa-entity-table-textarea pa-table-field t-68-amount']"));
        Assert.assertEquals(tableLines.size(), FIRST_VALUES_PASSED.length - 1);

        getWebDriverWait().until(d -> getDriver().findElement(END_BALANCE).getAttribute("value").equals(String.valueOf((int) sumNumber)));

        deleteRows(4);
        deleteRows(6);

        final double sum = sumNumber - FIRST_VALUES_PASSED[4] - FIRST_VALUES_PASSED[6];
        getWebDriverWait().until(d -> getDriver().findElement(END_BALANCE).getAttribute("value").equals(String.valueOf((int) (sum))));

        addingRowsByClickingOnGreenPlus(randomIntGeneration(1, 5));

        final double endBalanceDigit = sum + Integer.parseInt(VALUE_9);
        endBalanceD = (int) endBalanceDigit;

        tableLines.get(FIRST_VALUES_PASSED.length - 2).clear();
        tableLines.get(FIRST_VALUES_PASSED.length - 2).sendKeys(VALUE_9);

        getWebDriverWait().until(d -> (getDriver().findElement(END_BALANCE).getAttribute("value").equals(String.valueOf((int) endBalanceDigit))));

        ProjectUtils.click(getDriver(), getDriver().findElement(SAVE_BTN));

        String partOfXpath = Integer.toString((int) endBalanceDigit);

        getWebDriverWait().until(d -> getDriver().findElement(By.xpath("//div[contains(text(),'" + partOfXpath + "')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInViewMode() {

        goToChildLoop();
        ProjectUtils.click(getDriver(), getDriver().findElement(VIEW_FUNCTION));

        String[] valuesArr = {START_BALANCE + ".00", endBalanceD + ".00"};

        List<WebElement> startEndBalance = getDriver().findElements(By.xpath("//div/span[@class='pa-view-field']"));
        for (int i = 0; i < startEndBalance.size(); i++) {
            Assert.assertEquals(startEndBalance.get(i).getText(), valuesArr[i]);
        }
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInEditMode() {

        goToChildLoop();
        ProjectUtils.click(getDriver(), getDriver().findElement(EDIT_FUNCTION));
        Assert.assertEquals(getDriver().findElement(START_BALANCE_FIELD).getAttribute("value"), START_BALANCE);
        Assert.assertEquals(getDriver().findElement(END_BALANCE_FIELD).getAttribute("value"), String.valueOf(endBalanceD));
    }
}
