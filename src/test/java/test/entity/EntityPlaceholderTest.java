package test.entity;

import model.entity.common.ErrorPage;
import model.entity.common.MainPage;
import model.entity.common.RecycleBinPage;
import model.entity.edit.PlaceholderEditPage;
import model.entity.table.PlaceholderPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static runner.ProjectUtils.createUUID;

@Run(run = RunType.Multiple)
public class EntityPlaceholderTest extends BaseTest {

    private static final String TITLE = createUUID();
    private static final String COMMENTS = RandomStringUtils.randomAlphanumeric(25);
    private static final String INT = Integer.toString(ThreadLocalRandom.current().nextInt(100, 200));
    private static final String DECIMAL = "12.34";
    private static final String DECIMAL_ENDS_ZERO = "1.10";
    private static final String ALL_ZERO_AFTER_DECIMAL = "1.00";
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private static final String DATE_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    private static final String NEW_TITLE = String.format("%s_EditTextAllNew", createUUID());
    private static final String NEW_COMMENTS = "New comment text for edit test";
    private static final String NEW_INT = Integer.toString(ThreadLocalRandom.current().nextInt(300, 400));
    private static final String NEW_DECIMAL = "128.01";
    private static final String NEW_DATE = "25/01/2021";
    private static final String NEW_DATE_TIME = "25/01/2021 10:00:00";
    private static final String INVALID_ENTRY = "a";
    private static String currentUser = null;

    @Test
    public void createRecordTest() {

        MainPage mainPage = new MainPage(getDriver());

        currentUser = mainPage.getCurrentUser();
        final List<String> expectedValues = Arrays.asList(TITLE, COMMENTS, INT, DECIMAL, DATE, DATE_TIME, "", "",
                currentUser);

        PlaceholderPage placeholderPage = mainPage
                .clickMenuPlaceholder()
                .clickNewFolder()
                .fillOutForm(TITLE, COMMENTS, INT, DECIMAL, DATE, DATE_TIME)
                .selectUser(currentUser)
                .clickSaveButton();

        Assert.assertEquals(placeholderPage.getRowCount(), 1);
        Assert.assertEquals(placeholderPage.getRow(0), expectedValues);
        Assert.assertEquals(placeholderPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "deleteRecordTest")
    public void createDraftTest() {

        final List<String> expectedValues = Arrays.asList(TITLE, COMMENTS, "0", "0.00", "", "", "", "", currentUser);

        PlaceholderPage placeholderPage = new MainPage(getDriver())
                .clickMenuPlaceholder()
                .clickNewFolder()
                .fillTitle(TITLE)
                .fillComments(COMMENTS)
                .selectUser(currentUser)
                .clickSaveDraftButton();

        Assert.assertEquals(placeholderPage.getRowCount(), 1);
        Assert.assertEquals(placeholderPage.getRow(0), expectedValues);
        Assert.assertEquals(placeholderPage.getRowIconClass(0), AppConstant.DRAFT_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createRecordTest")
    public void editRecordTest() {

        final List<String> expectedValues = Arrays.asList(NEW_TITLE, NEW_COMMENTS, NEW_INT, NEW_DECIMAL, NEW_DATE,
                NEW_DATE_TIME, "", "", "randomUser");

        MainPage mainPage = new MainPage(getDriver());
        PlaceholderEditPage placeholderEditPage = mainPage.clickMenuPlaceholder().editRow(0);

        expectedValues.set(8, placeholderEditPage.getRandomUser());
        PlaceholderPage placeholderPage = placeholderEditPage
                .fillTitle(NEW_TITLE)
                .fillComments(NEW_COMMENTS)
                .fillInt(NEW_INT)
                .fillDecimal(NEW_DECIMAL)
                .fillDate(NEW_DATE)
                .fillDateTime(NEW_DATE_TIME)
                .selectUser(expectedValues.get(8))
                .clickSaveButton();

        Assert.assertEquals(placeholderPage.getRowCount(), 1);
        Assert.assertEquals(placeholderPage.getRow(0), expectedValues);
        Assert.assertEquals(placeholderPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "editRecordTest")
    public void deleteRecordTest() {

        PlaceholderPage placeholderPage = new MainPage(getDriver()).clickMenuPlaceholder();
        final String recordTitle = placeholderPage.getTitle(0);
        placeholderPage.deleteRow(0);

        Assert.assertEquals(placeholderPage.getRowCount(), 0, "Record has not been deleted");

        RecycleBinPage recycleBinPage = placeholderPage.clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 1);
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(recordTitle));
    }

    @Test(dependsOnMethods = "createDraftTest")
    public void deleteDraftTest() {

        PlaceholderPage placeholderPage = new MainPage(getDriver()).clickMenuPlaceholder();
        final String entityTitle = placeholderPage.getTitle(0);
        placeholderPage.deleteRow(0);

        Assert.assertEquals(placeholderPage.getRowCount(), 0, "Draft has not been deleted");

        RecycleBinPage recycleBinPage = placeholderPage.clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 2);
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(entityTitle));
    }

    @Ignore
    @Test
    public void invalidIntEntryCreateTest() {

        ErrorPage errorPage = new MainPage(getDriver())
                .clickMenuPlaceholder()
                .clickNewFolder()
                .fillInt(INVALID_ENTRY)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.ERROR_MESSAGE);
    }

    @Ignore
    @Test
    public void invalidDecimalEntryCreateTest() {

        ErrorPage errorPage = new MainPage(getDriver())
                .clickMenuPlaceholder()
                .clickNewFolder()
                .fillDecimal(INVALID_ENTRY)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "deleteDraftTest")
    public void entityDecimalEndsZeroTest() {

        PlaceholderPage placeholderPage = new MainPage(getDriver())
                .clickMenuPlaceholder()
                .clickNewFolder()
                .fillDecimal(DECIMAL_ENDS_ZERO)
                .clickSaveButton();

        Assert.assertEquals(placeholderPage.getDecimal(0), DECIMAL_ENDS_ZERO);

        placeholderPage.editRow(0)
                .fillDecimal(ALL_ZERO_AFTER_DECIMAL)
                .clickSaveButton();

        Assert.assertEquals(placeholderPage.getDecimal(0), ALL_ZERO_AFTER_DECIMAL);
    }
}
