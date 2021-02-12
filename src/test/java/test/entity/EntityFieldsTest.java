package test.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.entity.common.ErrorPage;
import model.entity.common.MainPage;
import model.entity.common.RecycleBinPage;
import model.entity.edit.FieldsEditPage;
import model.entity.table.FieldsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.Assert;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import static runner.ProjectUtils.createUUID;

@Run(run = RunType.Multiple)
public class EntityFieldsTest extends BaseTest {

    private static final String TITLE = createUUID();
    private static final String COMMENTS = RandomStringUtils.randomAlphanumeric(25);
    private static final String INT = Integer.toString(ThreadLocalRandom.current().nextInt(100, 200));
    private static final String DECIMAL = "12.34";
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private static final String DATE_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    private static final String NEW_TITLE = String.format("%s_EditTextAllNew", createUUID());
    private static final String NEW_COMMENTS = "New comment text for edit test";
    private static final String NEW_INT = Integer.toString(ThreadLocalRandom.current().nextInt(300, 400));
    private static final String NEW_DECIMAL = "128.01";
    private static final String NEW_DATE = "25/10/2018";
    private static final String NEW_DATE_TIME = "25/10/2018 08:22:05";
    private static final String INVALID_ENTRY = "a";
    private static String currentUser = null;

    @Test
    public void createRecordTest() {

        final List<String> expectedRecordValues = Arrays.asList(TITLE, COMMENTS, INT, DECIMAL, DATE,
                DATE_TIME, "", currentUser, "");

        MainPage mainPage = new MainPage(getDriver());
        currentUser = mainPage.getCurrentUser();
        expectedRecordValues.set(7, currentUser);

        FieldsPage fieldsPage = mainPage
                .clickMenuFields()
                .clickNewFolder()
                .fillTitle(TITLE)
                .fillComments(COMMENTS)
                .fillInt(INT)
                .fillDecimal(DECIMAL)
                .fillDate(DATE)
                .fillDateTime(DATE_TIME)
                .selectUser(currentUser)
                .clickSaveButton();

        Assert.assertEquals(fieldsPage.getRowCount(), 1);
        Assert.assertEquals(fieldsPage.getRow(0), expectedRecordValues);
        Assert.assertEquals(fieldsPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createRecordTest")
    public void editRecordTest() {

        List<String> expectedValues = Arrays.asList(NEW_TITLE, NEW_COMMENTS, NEW_INT, NEW_DECIMAL, NEW_DATE,
                NEW_DATE_TIME, "", "randomUser", "");

        MainPage mainPage = new MainPage(getDriver());
        FieldsEditPage fieldsEditsPage = mainPage.clickMenuFields().editRow(0);
        final String randomUser = fieldsEditsPage.getRandomUser();
        expectedValues.set(7, randomUser);

        FieldsPage fieldsPage = fieldsEditsPage
                .fillTitle(NEW_TITLE)
                .fillComments(NEW_COMMENTS)
                .fillInt(NEW_INT)
                .fillDecimal(NEW_DECIMAL)
                .fillDate(NEW_DATE)
                .fillDateTime(NEW_DATE_TIME)
                .selectUser(randomUser)
                .clickSaveButton();

        Assert.assertEquals(fieldsPage.getRowCount(), 1);
        Assert.assertEquals(fieldsPage.getRow(0), expectedValues);
        Assert.assertEquals(fieldsPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "editRecordTest")
    public void deleteRecordTest() {

        FieldsPage fieldsPage = new MainPage(getDriver()).clickMenuFields();
        fieldsPage.deleteRow(0);

        Assert.assertEquals(fieldsPage.getRowCount(), 0, "Record has not been deleted");

        RecycleBinPage recycleBinPage = fieldsPage.clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 1);
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(NEW_TITLE));
    }

    @Test(dependsOnMethods = "deleteRecordTest")
    public void createDraftTest() {

        final List<String> expectedValues = Arrays.asList(TITLE, COMMENTS, "0", "0.00", "", "", "", currentUser, "");

        FieldsPage fieldsPage = new MainPage(getDriver())
                .clickMenuFields()
                .clickNewFolder()
                .fillTitle(TITLE)
                .fillComments(COMMENTS)
                .selectUser(currentUser)
                .clickSaveDraftButton();

        Assert.assertEquals(fieldsPage.getRowCount(), 1);
        Assert.assertEquals(fieldsPage.getRow(0), expectedValues);
        Assert.assertEquals(fieldsPage.getRowIconClass(0), AppConstant.DRAFT_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createDraftTest")
    public void deleteDraftTest() {

        FieldsPage fieldsPage = new MainPage(getDriver()).clickMenuFields();
        fieldsPage.deleteRow(0);

        Assert.assertEquals(fieldsPage.getRowCount(), 0, "Draft has not been deleted");

        RecycleBinPage recycleBinPage = fieldsPage.clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 2);
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(TITLE));
    }

    @Test(dependsOnMethods = "deleteDraftTest")
    public void entityDecimalFormatTest() {

        FieldsPage fieldsPage = new MainPage(getDriver())
                .clickMenuFields()
                .clickNewFolder()
                .fillDecimal("1")
                .clickSaveButton();

        Assert.assertEquals(fieldsPage.getDecimal(0), "1.00");

        fieldsPage.editRow(0)
                .fillDecimal("1.1")
                .clickSaveButton();

        Assert.assertEquals(fieldsPage.getDecimal(0), "1.10");
    }

    @Ignore
    @Test
    public void invalidIntEntryCreateTest() {

        ErrorPage errorPage = new MainPage(getDriver())
                .clickMenuFields()
                .clickNewFolder()
                .fillInt(INVALID_ENTRY)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.ERROR_MESSAGE);
    }

    @Ignore
    @Test
    public void invalidDecimalEntryCreateTest() {

        ErrorPage errorPage = new MainPage(getDriver())
                .clickMenuFields()
                .clickNewFolder()
                .fillDecimal(INVALID_ENTRY)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.ERROR_MESSAGE);
    }
}
