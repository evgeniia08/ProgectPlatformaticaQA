import model.MainPage;
import model.PlatformFuncEditPage;
import model.PlatformFuncPage;
import model.RecycleBinPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityPlatformFunctions1Test extends BaseTest {

    private static final String POSITIVE_INT = "15";
    private static final String POSITIVE_INT_2 = "25";
    private static final String TEXT_SAMPLE = "Simple text";
    private static final String TEXT_SAMPLE_2 = RandomStringUtils.randomAlphabetic(10);
    private static final String CUSTOM_CONSTANT  = "john.doe@mail.com";
    private static final String DEFAULT_CONSTANT  = "contact@company.com";

    @Test
    public void createRecordTest() {

        List<String> expectedValues = Arrays.asList("", POSITIVE_INT, TEXT_SAMPLE, DEFAULT_CONSTANT, "menu");

        PlatformFuncPage platformFuncPage = new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(POSITIVE_INT, TEXT_SAMPLE)
                .clickSaveButton();

        Assert.assertEquals(platformFuncPage.getRowCount(), 1);
        Assert.assertEquals(platformFuncPage.getRow(0), expectedValues);
        Assert.assertEquals(platformFuncPage.getRowEntityIcon(0).getAttribute("class"),
                "fa fa-check-square-o");
    }

    @Test (dependsOnMethods = "createRecordTest")
    public void editRecordTest() {

        List<String> expectedValues = Arrays.asList("", POSITIVE_INT_2, TEXT_SAMPLE_2, CUSTOM_CONSTANT, "menu");

        PlatformFuncPage platformFuncPage = new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .editRow()
                .fillValues(POSITIVE_INT_2, TEXT_SAMPLE_2, CUSTOM_CONSTANT)
                .clickSaveButton();

        Assert.assertEquals(platformFuncPage.getRowCount(), 1);
        Assert.assertEquals(platformFuncPage.getRow(0), expectedValues);
        Assert.assertEquals(platformFuncPage.getRowEntityIcon(0).getAttribute("class"),
                "fa fa-check-square-o");
    }

    @Test (dependsOnMethods = "editRecordTest")
    public void deleteRecordTest() {
        PlatformFuncPage platformFuncPage = new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .deleteRow();

        Assert.assertEquals(platformFuncPage.getRowCount(), 0);

        RecycleBinPage recycleBinPage = platformFuncPage.clickRecycleBin();
        Assert.assertEquals(recycleBinPage.getRowCount(), 1);
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(POSITIVE_INT_2));
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(TEXT_SAMPLE_2));
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(CUSTOM_CONSTANT));
    }

    @Test(dependsOnMethods = "deleteRecordTest")
    public void entityMainLogicTest() {

        final String RECORD_1_LAST_INT = "-1";
        final String RECORD_2_LAST_INT = "0";
        final String RECORD_3_LAST_INT = "1";

        final String RECORD_1_LAST_STRING = "Test text";
        final String RECORD_2_LAST_STRING = String.format("%s suffix", RECORD_1_LAST_STRING);
        final String RECORD_3_LAST_STRING = String.format("%s suffix suffix", RECORD_1_LAST_STRING);

        List<String> firstRecordExpectedValues = Arrays.asList("", RECORD_1_LAST_INT, RECORD_1_LAST_STRING,
                DEFAULT_CONSTANT, "menu");
        List<String> secondRecordExpectedValues = Arrays.asList("", RECORD_2_LAST_INT, RECORD_2_LAST_STRING,
                DEFAULT_CONSTANT, "menu");
        List<String> thirdRecordExpectedValues = Arrays.asList("", RECORD_3_LAST_INT, RECORD_3_LAST_STRING,
                DEFAULT_CONSTANT, "menu");

        PlatformFuncPage platformFuncPage = new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(RECORD_1_LAST_INT, RECORD_1_LAST_STRING)
                .clickSaveButton();

        Assert.assertEquals(platformFuncPage.getRowCount(), 1);
        Assert.assertEquals(platformFuncPage.getRow(0), firstRecordExpectedValues);

        PlatformFuncEditPage platformFuncEditPage = platformFuncPage.clickNewFolder();

        Assert.assertEquals(platformFuncEditPage.getLastInt(), RECORD_2_LAST_INT);
        Assert.assertEquals(platformFuncEditPage.getLastString(), RECORD_2_LAST_STRING);

        platformFuncPage = platformFuncEditPage.clickSaveButton();

        Assert.assertEquals(platformFuncPage.getRowCount(), 2);
        Assert.assertEquals(platformFuncPage.getRow(0), firstRecordExpectedValues);
        Assert.assertEquals(platformFuncPage.getRow(1), secondRecordExpectedValues);

        platformFuncEditPage = platformFuncPage.clickNewFolder();

        Assert.assertEquals(platformFuncEditPage.getLastInt(), RECORD_3_LAST_INT);
        Assert.assertEquals(platformFuncEditPage.getLastString(), RECORD_3_LAST_STRING);

        platformFuncPage = platformFuncEditPage.clickSaveButton();

        Assert.assertEquals(platformFuncPage.getRowCount(), 3);
        Assert.assertEquals(platformFuncPage.getRow(0), firstRecordExpectedValues);
        Assert.assertEquals(platformFuncPage.getRow(1), secondRecordExpectedValues);
        Assert.assertEquals(platformFuncPage.getRow(2), thirdRecordExpectedValues);
    }
}