package test.entity;
import model.entity.common.MainPage;
import model.entity.edit.ChildRecordsLoopEditPage;
import model.entity.table.ChildRecordsLoopPage;
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
    private static String[] startAndEndBalanceDisplayed;

    @Test
    public void checkStartEndBalanceBeforeSave() {
        ChildRecordsLoopEditPage childRecordsLoopEditPage = new MainPage(getDriver())
                .clickMenuChildRecordsLoop()
                .moveToElement()
                .clickNewFolder()
                .createNewChildLoopEmptyRecord(getDriver(), NUMBERS_OF_LINES)
                .startSendKeys(START_BALANCE);

        childRecordsLoopEditPage.waitForEndBalanceMatchWith(START_BALANCE);

        sumNumber += Integer.parseInt(START_BALANCE);

        for (int i = 0; i < FIRST_VALUES_PASSED.length; i++) {
            sumNumber += FIRST_VALUES_PASSED[i];
        }

        childRecordsLoopEditPage.fillWithLoop(FIRST_VALUES_PASSED, 10);

        childRecordsLoopEditPage.waitForEndBalanceMatchWith(String.valueOf((int) sumNumber));
        Assert.assertEquals(childRecordsLoopEditPage.checkLastElement(), (String.valueOf(FIRST_VALUES_PASSED[FIRST_VALUES_PASSED.length - 1])));
        Assert.assertEquals(childRecordsLoopEditPage.getTableLinesSize(), FIRST_VALUES_PASSED.length - 2);

        childRecordsLoopEditPage.deleteRows(4);
        childRecordsLoopEditPage.deleteRows(6);

        final double sum = sumNumber - FIRST_VALUES_PASSED[4] - FIRST_VALUES_PASSED[6];
        childRecordsLoopEditPage.waitForEndBalanceMatchWith(String.valueOf((int) (sum)));
        int last = childRecordsLoopEditPage.randomIntGeneration(1, 5);
        childRecordsLoopEditPage.addingRowsByClickingOnGreenPlus(getDriver(), last);

        final double endBalanceDigit = sum + Integer.parseInt(VALUE_9);
        endBalanceD = (int) endBalanceDigit;

        int countRows = NUMBERS_OF_LINES + last;

        childRecordsLoopEditPage.fillData(String.format("//textarea[@id='t-68-r-%d-amount']", countRows), VALUE_9);
        childRecordsLoopEditPage.waitForEndBalanceMatchWith(String.valueOf((int) endBalanceDigit));

        childRecordsLoopEditPage.clickSaveButton();

        startAndEndBalanceDisplayed = new String[]{START_BALANCE + ".00", endBalanceD + ".00"};

        Assert.assertEquals(childRecordsLoopEditPage.getBalance(), startAndEndBalanceDisplayed);
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInViewMode() {
        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuChildRecordsLoop()
                .viewRow()
                .getValues(), Arrays.asList(startAndEndBalanceDisplayed));
    }

    @Test(dependsOnMethods = "checkStartEndBalanceBeforeSave")
    public void checkStartEndBalanceInEditMode() {
        ChildRecordsLoopEditPage childRecordsLoopEditPage = new MainPage(getDriver())
                .clickMenuChildRecordsLoop()
                .editRow(0);

        Assert.assertEquals(childRecordsLoopEditPage.checkStartEndBalance(), startAndEndBalanceDisplayed);
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
