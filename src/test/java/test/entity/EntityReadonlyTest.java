package test.entity;

import model.entity.common.MainPage;
import model.entity.edit.ReadOnlyEditPage;
import model.entity.table.ReadOnlyPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityReadonlyTest extends BaseTest {

    private static final List<String> EXPECTED_VALUES_LIST =
            Arrays.asList("", "", "0", "0.00", "", "");

    private static final String EXPECTED_VALUES = "String\nText\nInt\nDecimal\nDate\nDatetime\nFile\nFile image\nUser";

    @Test
    public void verifyListIsEmptyTest() {
        ReadOnlyPage readOnlyPage = new MainPage(getDriver())
                .clickMenuReadOnly()
                .clickListButton();

        Assert.assertEquals(readOnlyPage.getCardBodyText(), "");
    }

    @Test (dependsOnMethods = "verifyListIsEmptyTest")
    public void inputTest() {
        ReadOnlyEditPage editPage = new MainPage(getDriver())
                .clickMenuReadOnly()
                .clickNewFolder();

        Assert.assertTrue(editPage.getStringIsReadOnly());
        Assert.assertTrue(editPage.getTextIsReadOnly());
        Assert.assertTrue(editPage.getIntIsReadOnly());
        Assert.assertTrue(editPage.getDecimalIsReadOnly());
        Assert.assertTrue(editPage.getDateIsReadOnly());
        Assert.assertTrue(editPage.getDateTimeIsReadOnly());

        ReadOnlyPage tablePage = editPage
                .clickSaveButton()
                .clickButtonOrder();

        Assert.assertEquals(tablePage.getLastRowText(), EXPECTED_VALUES);
    }

    @Test(dependsOnMethods = "inputTest")
    public void verifyRowsAreEmptyTest() {

        ReadOnlyPage tablePage = new MainPage(getDriver())
                .clickMenuReadOnly();

        Assert.assertEquals(tablePage.getRowCount(), 1);
        Assert.assertEquals(tablePage.getRow(0), EXPECTED_VALUES_LIST);
    }

}
