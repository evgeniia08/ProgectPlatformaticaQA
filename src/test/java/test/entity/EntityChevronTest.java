package test.entity;

import model.entity.common.MainPage;
import model.entity.table.ChevronPage;
import model.entity.view.ChevronViewPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import runner.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import runner.type.Run;
import runner.type.RunType;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityChevronTest extends BaseTest {

    private static final String COMMENTS = "TEST3";
    private static final String INT = "11";
    private static final String DECIMAL = "0.11";

    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    private static final String DATE_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

    List<String> expectedResults = Arrays.asList("Fulfillment", "TEST3", "11", "0.11", DATE, DATE_TIME);

    @DataProvider(name = "testData")
    private Object[][] testData1() {
        return new Object[][]{
                {"Fulfillment","TEST4", "20", "0.11", DATE, DATE_TIME},
                {"Pending", "TEST1", "30", "0.11", DATE, DATE_TIME},
                {"Sent", "TEST2", "30", "0.11", DATE, DATE_TIME},
        };
    }

    @Test(dataProvider = "testData")
    public void createMultipleEntities(String status, String title, String int_, String decimal, String data, String time) {

        ChevronPage chevronPage = new MainPage(getDriver()).clickMenuChevron();
        chevronPage.clickFilter("All")
                .clickNewFolder()
                .chooseRecordStatus(status)
                .sendKeys(title, int_, decimal, data, time)
                .clickSaveButton();
    }

    @Test(dependsOnMethods = "createMultipleEntities")
    public void checkChevronStatus() {

        MainPage mainPage = new MainPage(getDriver());

        ChevronPage chevronPage = mainPage
                .clickMenuChevron()
                .clickFilter("Pending");
        Assert.assertEquals(chevronPage.getStringValue(), "Pending");

        ChevronViewPage viewPage = chevronPage.viewRow(0);
        Assert.assertEquals(viewPage.getActiveChevronText(), "Pending");

        chevronPage = mainPage
                .clickMenuChevron()
                .clickFilter("Fulfillment");
        Assert.assertEquals(chevronPage.getStringValue(), "Fulfillment");

        viewPage = chevronPage.viewRow(0);
        Assert.assertEquals(viewPage.getActiveChevronText(), "Fulfillment");

        chevronPage = mainPage
                .clickMenuChevron()
                .clickFilter("Sent");
        Assert.assertEquals(chevronPage.getStringValue(), "Sent");

        viewPage = chevronPage.viewRow(0);
        Assert.assertEquals(viewPage.getActiveChevronText(), "Sent");
    }

    @Test(dependsOnMethods = "checkChevronStatus")
    public void createNewRecord() {

        ChevronPage chevronPage = new MainPage(getDriver())
                .clickMenuChevron()
                .clickNewFolder()
                .chooseRecordStatus("Fulfillment")
                .sendKeys(COMMENTS, INT, DECIMAL, DATE_TIME, DATE)
                .ChooseValues()
                .clickSaveButton()
                .clickFilter("All");
        Assert.assertEquals(chevronPage.getRow(3), expectedResults);
    }

    @Test(dependsOnMethods = "createNewRecord")
    public void dragAndDrop() {
        String stringValue = new MainPage(getDriver())
                .clickMenuChevron()
                .orderBy()
                .dragAndDrop(1, 3)
                .getCellData(1);
        Assert.assertEquals(stringValue, "TEST1");
    }

    @Test(dependsOnMethods = "dragAndDrop")
    public void viewRecord() {
        List<String> page = new MainPage(getDriver())
                .clickMenuChevron()
                .clickRowToView(2)
                .getColumn();
        Assert.assertEquals(page, expectedResults);
    }

    @Test (dependsOnMethods = "viewRecord")
    public void deleteRecord() {
        ChevronPage chevronPage = new ChevronPage(getDriver());
        Assert.assertEquals(chevronPage
                .clickMenuChevron()
                .deleteRow()
                .getRowCount(), 2);
    }

    @Ignore
    @Test(dependsOnMethods = "deleteRecord")
    public void getFullSum() {
        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuChevron()
                .getSum(), 271);
        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuChevron()
                .getAvr(), 54);
    }
}