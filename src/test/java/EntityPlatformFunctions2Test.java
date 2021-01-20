import model.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityPlatformFunctions2Test extends BaseTest {

    private static final String POSITIVE_INT = "15";
    private static final String MIN_INT = "-2147483648";
    private static final String MAX_INT = "2147483647";
    private static final String OUT_OF_RANGE_MIN_INT = "-2147483649";
    private static final String TEST_TEXT = "Test";
    private static final String TEST_TEXT_120 = RandomStringUtils.randomAlphanumeric(120);
    private static final String INVALID_LAST_INT = "1a";
    private static final String CONSTANT = "contact@company.com";
    private static final String ERROR_MESSAGE = "error saving entity";

    @Test
    public void createCancelTest() {

        PlatformFuncEditPage platformFuncEditPage = new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder();
        final String LAST_INT = platformFuncEditPage.getLastInt();

        PlatformFuncPage platformFuncPage = platformFuncEditPage.clickCancelButton();
        Assert.assertEquals(platformFuncPage.getRowCount(), 0);
        Assert.assertEquals(platformFuncPage.clickNewFolder().getLastInt(), LAST_INT);
    }

    @Test(dependsOnMethods = "createCancelTest")
    public void createDraftTest() {

        List<String> expectedValues = Arrays.asList("", POSITIVE_INT, TEST_TEXT, CONSTANT, "menu");

        PlatformFuncPage platformFuncPage = new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(POSITIVE_INT, TEST_TEXT)
                .clickSaveDraftButton();

        Assert.assertEquals(platformFuncPage.getRowCount(), 1);
        Assert.assertEquals(platformFuncPage.getRow(0), expectedValues);
        Assert.assertEquals(platformFuncPage.getRowEntityIcon(0).getAttribute("class"), "fa fa-pencil");

        platformFuncPage.deleteRow();
    }

    @Test(dependsOnMethods = "createDraftTest")
    public void viewRecordLongStringTest() {

        List<String> expectedValues = Arrays.asList("", POSITIVE_INT, TEST_TEXT_120, CONSTANT, "menu");

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(POSITIVE_INT, TEST_TEXT_120)
                .clickSaveButton()
                .getRow(0), expectedValues);

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .viewRow()
                .getValues(), expectedValues.subList(1,4));
    }

    @Test(dependsOnMethods = "viewRecordLongStringTest")
    public void invalidLastIntTest() {

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(INVALID_LAST_INT, TEST_TEXT)
                .clickSaveButtonErrorExpected()
                .getErrorMessage(), ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "invalidLastIntTest")
    public void maxBoundaryIntTest() {

        List<String> expectedValues = Arrays.asList("", MAX_INT, TEST_TEXT, CONSTANT, "menu");

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(MAX_INT, TEST_TEXT)
                .clickSaveButton()
                .getRow(1), expectedValues);

        Assert.assertEquals(new PlatformFuncPage(getDriver())
                .clickNewFolder()
                .clickSaveButtonErrorExpected()
                .getErrorMessage(), ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "maxBoundaryIntTest")
    public void minBoundaryIntTest() {

        List<String> expectedValues = Arrays.asList("", MIN_INT, TEST_TEXT, CONSTANT, "menu");

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuPlatformFunctions()
                .clickNewFolder()
                .fillValues(MIN_INT, TEST_TEXT)
                .clickSaveButton()
                .getRow(2), expectedValues);

        Assert.assertEquals(new PlatformFuncPage(getDriver())
                .clickNewFolder()
                .fillLastInt(OUT_OF_RANGE_MIN_INT)
                .clickSaveButtonErrorExpected()
                .getErrorMessage(), ERROR_MESSAGE);
    }
}
