package test.entity;
import model.entity.common.MainPage;
import model.entity.edit.ChildRecordsLoopEditPage;
import model.entity.table.ChildRecordsLoopPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import java.util.Arrays;


@Run(run = RunType.Multiple)
public class EntityChildRecordsLoopTest extends BaseTest {

    private static final String START_BALANCE = "1";
    private static final String VALUE_9 = "6";
    private static int endBalanceD;
    private static final int NUMBERS_OF_LINES = 9;
    private static double sumNumber = 0;
    private static final double[] FIRST_VALUES_PASSED = {0.00, 10.50, 11.00, 12.00, 13.00, 14.00, 1.00, 1.00, 2.50, 0.0};

    @Test
    public void checkStartEndBalanceBeforeSave() {
        MainPage mainPage = new MainPage(getDriver());
        ChildRecordsLoopEditPage childRecordsLoopEditPage = mainPage
                .clickMenuChildRecordsLoop()
                .clickNewFolder()
                .createNewChildLoopEmptyRecord(NUMBERS_OF_LINES)
                .startSendKeys(START_BALANCE);

        getWebDriverWait().until(d -> ChildRecordsLoopEditPage.checkEndBalance().equals(START_BALANCE));

        sumNumber += Integer.parseInt(START_BALANCE);

        for (int i = 0; i < FIRST_VALUES_PASSED.length; i++) {
            sumNumber += FIRST_VALUES_PASSED[i];
        }

        for (int i = 1; i < 10; i++) {
            ChildRecordsLoopEditPage.fillData(getDriver(), String.format("//tr//textarea[@id='t-68-r-%d-amount']", i), String.valueOf(FIRST_VALUES_PASSED[i]));
        }

        getWebDriverWait().until(d -> ChildRecordsLoopEditPage.checkEndBalance().equals(String.valueOf((int) sumNumber)));
        Assert.assertEquals(ChildRecordsLoopEditPage.checkLastElement(), (String.valueOf(FIRST_VALUES_PASSED[FIRST_VALUES_PASSED.length-1])));
        Assert.assertEquals(ChildRecordsLoopEditPage.getTableLinesSize(), FIRST_VALUES_PASSED.length - 1);

        ChildRecordsLoopEditPage.deleteRows(getDriver(), 4);
        ChildRecordsLoopEditPage.deleteRows(getDriver(), 6);

        final double sum = sumNumber - FIRST_VALUES_PASSED[4] - FIRST_VALUES_PASSED[6];
        getWebDriverWait().until(d -> ChildRecordsLoopEditPage.checkEndBalance().equals(String.valueOf((int) (sum))));

        ChildRecordsLoopEditPage.addingRowsByClickingOnGreenPlus(getDriver(), ChildRecordsLoopEditPage.randomIntGeneration(1, 5));

        final double endBalanceDigit = sum + Integer.parseInt(VALUE_9);
        endBalanceD = (int) endBalanceDigit;

        ChildRecordsLoopEditPage.tableLinesSendValues(FIRST_VALUES_PASSED.length - 2, VALUE_9);

        getWebDriverWait().until(d -> ChildRecordsLoopEditPage.checkEndBalance().equals(String.valueOf((int) endBalanceDigit)));

        ChildRecordsLoopEditPage.clickSaveBtn(getDriver());

        String partOfXpath = Integer.toString((int) endBalanceDigit);

        getWebDriverWait().until(d -> getDriver().findElement(By.xpath("//div[contains(text(),'" + partOfXpath + "')]")).isDisplayed());
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInViewMode() {
        String[] valuesArr = {START_BALANCE + ".00", endBalanceD + ".00"};

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuChildRecordsLoop()
                .viewRow()
                .getValues(), Arrays.asList(valuesArr));
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInEditMode() {
        new MainPage(getDriver())
                .clickMenuChildRecordsLoop()
                .editRow(0);

        Assert.assertEquals(ChildRecordsLoopEditPage.checkStartFieldBalance(), START_BALANCE);
        Assert.assertEquals(ChildRecordsLoopEditPage.checkEndBalance(), String.valueOf(endBalanceD));
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void deleteRecord() {
        ChildRecordsLoopPage childRecordsLoopPage = new ChildRecordsLoopPage(getDriver());

        Assert.assertEquals(childRecordsLoopPage
                .clickMenuChildRecordsLoop()
                .deleteRow()
                .getRowCount(), 0);
    }
}
