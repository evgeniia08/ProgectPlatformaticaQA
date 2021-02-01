package test.entity;

import model.*;


import model.entity.common.BoardPage;
import model.entity.common.CalendarEntityPage;
import model.entity.common.MainPage;
import model.entity.common.RecycleBinPage;
import model.entity.edit.BoardEditPage;
import model.entity.table.BoardListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.util.Arrays;
import java.util.List;
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
    private String dateForValidation;
    private String dateTimeForValidation;
    private String time;
    CalendarEntityPage calendar = new CalendarEntityPage(getDriver());
    private RecycleBinPage boardListPage;
    private static final String SAFEASDRAFT = "fa fa-pencil";

    @Test
    public void inputValidationTest() {

        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickSaveDraftButton()
                .clickListButton();

        time = new BoardEditPage(getDriver()).getCreatedTime()[1];
        dateForValidation = String.format("%1$s%4$s%3$s%4$s%2$s", calendar.getRandomDay() , calendar.getCurrentYear(), calendar.getCurrentMonth(), '/');
        dateTimeForValidation= String.format("%1$s %2$s", dateForValidation, time);
        List<String> expectedValues = Arrays.asList(PENDING, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation,"",  APP_USER);

        Assert.assertEquals(boardListPage.getRowCount(), 1);
        Assert.assertEquals(boardListPage.getRow(0), expectedValues);


    }

    @Test(dependsOnMethods = "inputValidationTest")
    public void kanbanValidationRecord() {

        BoardPage boardPage = new MainPage(getDriver())
                .clickMenuBoard();

        dateForValidation = String.format("%2$s%4$s%3$s%4$s%1$s", calendar.getRandomDay() , calendar.getCurrentYear(), calendar.getCurrentMonth(), '-');
        dateTimeForValidation = String.format("%1$s %2$s", dateForValidation, time);

        Assert.assertEquals(boardPage.getPendingItemsCount(), 1);
        Assert.assertEquals(boardPage.getPendingText(), String.format("%s %s %s %s %5$s %6$s 8", PENDING, TEXT, NUMBER, DECIMAL, dateForValidation, dateTimeForValidation));
    }

    @Test(dependsOnMethods = {"kanbanValidationRecord"})
    public void manipulateTest1() {



        BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .moveFromPedingToOntrack()
                .clickListButton();

        dateForValidation =String.format("%1$s%4$s%3$s%4$s%2$s", calendar.getRandomDay() , calendar.getCurrentYear(), calendar.getCurrentMonth(), '/');
        dateTimeForValidation= String.format("%1$s %2$s", dateForValidation, time);
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

        BaseViewPage baseViewPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickListButton()
                .viewRow();

        actualValues = baseViewPage.getValues();
        actualValues.add(baseViewPage.getUser());
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

        WebDriver driver = getDriver();

        WebElement tab = driver.findElement(By.xpath("//p[contains(text(),'Board')]"));
        ProjectUtils.click(driver, tab);

        WebElement recycleBin = driver.findElement(By.xpath("//li/a/i[text()='delete_outline']"));
        ProjectUtils.click(driver, recycleBin);

        WebElement restoreAsDraft = driver.findElement(By.xpath("//a[normalize-space()='restore as draft']"));
        ProjectUtils.click(driver, restoreAsDraft);

        WebElement emptyRecycleBin = driver.findElement(By.xpath(
                "//div[contains(text(), 'Good job with housekeeping! Recycle bin is currently empty!')]"));
        Assert.assertNotNull(emptyRecycleBin);
        System.out.println(emptyRecycleBin.getText());

        WebElement tab1 = driver.findElement(By.xpath("//p[contains(text(),'Board')]"));
        ProjectUtils.click(driver, tab1);

        WebElement list = driver.findElement(By.xpath("//a[contains(@href, '31')]/i[text()='list']"));
        ProjectUtils.click(driver, list);

        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertEquals(rows.get(0).findElement(By.xpath("//td[1]/i")).getAttribute("class"), AppConstant.DRAFT_ICON_CLASS);

        }

        /*BoardListPage boardListPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickRecycleBin()
                .clickRestoreAsDraft();
    }
*/
    @Test(dependsOnMethods = {"restoreAsDraftTest"})
    public void recordDeletionRecBin() {

        Assert.assertEquals(new MainPage(getDriver()).clickRecycleBin().clickDeletePermanently(0).getRowCount(), 0);
    }

    @Test(dependsOnMethods = {"recordDeletionRecBin"})
    public void cancelInputTest() {

        BoardPage boardPage = new MainPage(getDriver())
                .clickMenuBoard()
                .clickNewFolder()
                .fillform(PENDING, TEXT, NUMBER, DECIMAL, APP_USER)
                .clickCancelButton();

        Assert.assertEquals(boardPage.getPendingItemsCount(), 0);
    }
}
