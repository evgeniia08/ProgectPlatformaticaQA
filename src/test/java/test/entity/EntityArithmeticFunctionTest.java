package test.entity;

import model.base.EntityBaseViewPage;
import model.entity.common.ErrorPage;
import model.entity.common.MainPage;
import model.entity.edit.ArithmeticFunctionEditPage;
import model.entity.table.ArithmeticFunctionPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityArithmeticFunctionTest extends BaseTest {

    private static final int F1 = 12;
    private static final int F2 = 6;
    private static final int F3 = 24;
    private static final int F4 = 8;

    private static final String STRING = "Text";

    private String[] expectedData(int value1, int value2) {

        return new String[] {String.valueOf(value1), String.valueOf(value2),
                String.valueOf(value1 + value2), String.valueOf(value1 - value2),
                String.valueOf(value1 * value2), String.valueOf(value1 / value2)};
    }

    @Test
    public void createNewRecordTest() {

        List<String> expectedData = Arrays.asList(expectedData(F1, F2));

        ArithmeticFunctionPage arithmeticFunctionPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .clickNewFolder()
                .inputInitialValue(F1, F2)
                .waitSumToBe(expectedData.get(2))
                .waitSubToBe(expectedData.get(3))
                .waitMulToBe(expectedData.get(4))
                .waitDivToBe(expectedData.get(5))
                .clickSaveButton();

        Assert.assertEquals(arithmeticFunctionPage.getRowCount() , 1);
        Assert.assertEquals(arithmeticFunctionPage.getRow(0), expectedData);
        Assert.assertEquals(arithmeticFunctionPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createNewRecordTest")
    public void viewRecordTest() {

        EntityBaseViewPage arithmeticFunctionViewPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .viewRow();

        Assert.assertEquals(arithmeticFunctionViewPage.getValues(), Arrays.asList(expectedData(F1, F2)));
    }

    @Test(dependsOnMethods = "viewRecordTest")
    public void editRecordTest() {

        List<String> expectedData = Arrays.asList(expectedData(F3, F4));

        ArithmeticFunctionEditPage arithmeticFunctionEditPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .editRow()
                .inputInitialValue(F3, F4)
                .waitSumToBe(expectedData.get(2))
                .waitSubToBe(expectedData.get(3))
                .waitMulToBe(expectedData.get(4))
                .waitDivToBe(expectedData.get(5));

        Assert.assertEquals(arithmeticFunctionEditPage.getValues(), expectedData);
    }

    @Test(dependsOnMethods = "editRecordTest")
    public void createNewRecordNegativeStringTest() {

        ErrorPage errorPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .editRow()
                .inputInitialValue(STRING, STRING)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "createNewRecordNegativeStringTest")
    public void deleteRecordTest() {

        ArithmeticFunctionPage arithmeticFunctionPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .deleteRow();

        Assert.assertEquals(arithmeticFunctionPage.getRowCount(), 0);
        Assert.assertEquals(arithmeticFunctionPage.clickRecycleBin().getDeletedEntityContent(),
                (String.format("F1: %sF2: %sSUM: %sSUB: %sMUL: %sDIV: %s", F1, F2, F1 + F2, F1 - F2, F1 * F2, F1 / F2)));
    }
}
