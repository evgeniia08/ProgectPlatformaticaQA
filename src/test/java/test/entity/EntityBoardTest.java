package test.entity;

import model.entity.common.BoardPageEntityBase;
import model.entity.common.CalendarEntityPage;
import model.entity.common.MainPage;
import model.entity.common.RecycleBinPage;
import model.entity.edit.BoardEditPage;
import model.entity.table.BoardListPage;
import model.entity.view.BoardViewPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityBoardTest extends BaseTest {

    private static final String TEXT = UUID.randomUUID().toString();
    private static final String NUMBER = Integer.toString((int) (Math.random() * 100));
    private static final String DECIMAL = new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2).toString();
    private static final String TEXT_EDIT = "My values are changed";
    private static final String NUMBER_EDIT = "1975";
    private static final String DECIMAL_EDIT = "112.38";
    private static final String PENDING = "Pending";
    private static final String DONE = "Done";
    private static final String ON_TRACK = "On track";
    private static final String APP_USER = "apptester1@tester.com";
    private String dateForValidation;
    private String dateTimeForValidation;
    private String time;

    CalendarEntityPage calendar = new CalendarEntityPage(getDriver());

    @Test
    public void inputValidationTest() {

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickSaveDraftButton()
                .clickListButton();

        time = new BoardEditPage(getDriver()).getCreatedTime()[1];

        dateForValidation = String.format("%1$s%4$s%2$s%4$s%3$s", calendar.getRandomDay(), calendar.getCurrentMonth(), calendar.getCurrentYear(), '/');
        dateTimeForValidation = String.format("%1s %2s", dateForValidation, time);
        List<String> expectedValues = Arrays.asList(PENDING, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation, "", APP_USER);

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = "inputValidationTest")
    public void kanbanValidationRecord() {

        BoardPageEntityBase boardPage = new MainPage(getDriver())
                .clickMenuBoard();

        dateForValidation = String.format("%1$s%4$s%2$s%4$s%3$s", calendar.getRandomDay(), calendar.getCurrentMonth(), calendar.getCurrentYear(), '/');
        dateTimeForValidation = String.format("%1s %2s", dateForValidation, time);

        Assert.assertEquals(boardPage.getPendingItemsCount(), 1);
        Assert.assertEquals(boardPage.getPendingText(), String.format("%s %s %s %s %5$s %6$s apptester1@tester.com", PENDING, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation));
    }

    @Test(dependsOnMethods = {"kanbanValidationRecord"})
    public void manipulateTest1() {

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromPedingToOntrack()
                .clickListButton();

        dateForValidation = String.format("%1$s%4$s%2$s%4$s%3$s", calendar.getRandomDay(), calendar.getCurrentMonth(), calendar.getCurrentYear(), '/');
        dateTimeForValidation = String.format("%1s %2s", dateForValidation, time);
        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation, "", APP_USER);

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest1"})
    public void manipulateTest2() {

        List<String> expectedValues = Arrays.asList(DONE, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation, "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromOntrackToDone()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest2"})
    public void manipulateTest3() {

        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation, "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromDoneToOnTrack()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest3"})
    public void manipulateTest4() {

        List<String> expectedValues = Arrays.asList(PENDING, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation, "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromOnTrackToPending()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest4"})
    public void editBoard() {

        List<String> editedValues = Arrays.asList(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, dateForValidation, dateTimeForValidation, "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .editRow()
                .fillform(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, APP_USER)
                .clickSaveButton()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), editedValues);
    }

    @Test(dependsOnMethods = {"editBoard"})
    public void viewRecord() {

        List<String> editedValues = Arrays.asList(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, dateForValidation, dateTimeForValidation, APP_USER);
        List<String> actualValues;

        BoardViewPage entityBaseViewPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .viewRow();

        actualValues = entityBaseViewPage.getValues();
        actualValues.add(entityBaseViewPage.getUser());
        Assert.assertEquals(actualValues, editedValues);
    }

    @Test(dependsOnMethods = {"viewRecord"})
    public void deleteRecord() {

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .deleteRow();

        Assert.assertEquals(boardListPage.getRowCount(), 0);
    }

    @Test(dependsOnMethods = {"deleteRecord"})
    public void restoreAsDraftTest() {

        RecycleBinPage recycleBinPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickRecycleBin()
                .clickRestoreAsDraft();

        Assert.assertEquals(recycleBinPage.getNotification(), AppConstant.EMPTY_RECYCLE_BIN_TEXT);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowIconClass(0), AppConstant.DRAFT_ICON_CLASS);
    }

    @Test(dependsOnMethods = {"restoreAsDraftTest"})
    public void recordDeletionRecBin() {

        RecycleBinPage recycleBinPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .deleteRow()
                .clickRecycleBin()
                .clickDeletePermanently(0);

        Assert.assertEquals(recycleBinPage.getRowCount(), 0);
    }

    @Test(dependsOnMethods = {"recordDeletionRecBin"})
    public void cancelInputTest() {

        BoardPageEntityBase boardPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickCancelButton();

        Assert.assertEquals(boardPage.getPendingItemsCount(), 0);
    }

    @Test(dependsOnMethods = {"cancelInputTest"})
    public void searchRecordByString() {

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickSaveButton()
                .clickNewFolder()
                .fillform(DONE, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, APP_USER)
                .clickSaveButton()
                .clickListButton();

        List<String> expectedValues = boardListPage.getRow(1);

        boardListPage.fillSearchBox(expectedValues.get(0)).waitRowCountToBe(1);

        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
        Assert.assertEquals(boardListPage.getPaginationInfo(), "Showing 1 to 1 of 1 rows");
    }
}
