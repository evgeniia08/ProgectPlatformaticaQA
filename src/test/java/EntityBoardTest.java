import model.BoardListPage;
import model.BoardPage;
import model.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityBoardTest extends BaseTest {

    private static final String TEXT = UUID.randomUUID().toString();
    private static final String NUMBER = Integer.toString((int) (Math.random() * 100));
    private static final String DECIMAL = Double.toString(35.06);
    private static final String TEXT_EDIT = "My values are changed";
    private static final String NUMBER_EDIT = "1975";
    private static final String DECIMAL_EDIT = "112.38";
    private static final String PENDING = "Pending";
    private static final String DONE = "Done";
    private static final String ON_TRACK = "On track";
    private static final String APP_USER = "apptester1@tester.com";
    private static final LocalDate TODAY = LocalDate.now();
    private static final String OUTPUT = TODAY.toString();
    private static final String[] ARR_OF_DATA = OUTPUT.split("-", 3);
    private static final String CURRENT_YEAR = ARR_OF_DATA[0];
    private static final String CURRENT_MONTH = ARR_OF_DATA[1];
    Random generator = new Random();
    private final String RANDOM_DAY = String.format("%02d", generator.nextInt(27) + 1);

    @Test
    public void inputValidationTest() {

        List<String> expectedValues = Arrays.asList(PENDING, TEXT, NUMBER, DECIMAL, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickSaveDraftButton()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = "inputValidationTest")

    public void viewRecords() {

        BoardPage boardPage = new MainPage(getDriver())
                .clickMenuBoard();

        Assert.assertEquals(boardPage.getPendingItemsCount(), 1);
        Assert.assertEquals(boardPage.getPendingText(), String.format("%s %s %s %s 8", PENDING, TEXT, NUMBER, DECIMAL));
    }

    @Test(dependsOnMethods = {"viewRecords"})
    public void manipulateTest1() {

        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT, NUMBER, DECIMAL, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromPedingToOntrack()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);

    }

    @Test(dependsOnMethods = {"manipulateTest1"})
    public void manipulateTest2() {

        List<String> expectedValues = Arrays.asList(DONE, TEXT, NUMBER, DECIMAL, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromOntrackToDone()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest2"})
    public void manipulateTest3() {

        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT, NUMBER, DECIMAL, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromDoneToOnTrack()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest3"})
    public void manipulateTest4() {

        List<String> expectedValues = Arrays.asList(PENDING, TEXT, NUMBER, DECIMAL, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromOnTrackToPending()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }

    @Test(dependsOnMethods = {"manipulateTest4"})
    public void editBoard() {

        List<String> expectedValues = Arrays.asList(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, "", "", "", APP_USER);

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .editRow()
                .fillform(ON_TRACK, TEXT_EDIT, NUMBER_EDIT, DECIMAL_EDIT, APP_USER)
                .clickSaveButton()
                .clickListButton();

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);
    }
}
