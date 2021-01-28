import model.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;

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

        ArithmeticFunctionPage arithmeticFunctionPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .clickNewFolder()
                .inputInitialValue(F1, F2)
                .clickSaveButton();

        Assert.assertEquals(arithmeticFunctionPage.getRowCount() , 1);
        Assert.assertEquals(arithmeticFunctionPage.getRow(0), Arrays.asList(expectedData(F1, F2)));
        Assert.assertEquals(arithmeticFunctionPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createNewRecordTest")
    public void viewRecordTest() {

        BaseViewPage arithmeticFunctionViewPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .viewRow();

        Assert.assertEquals(arithmeticFunctionViewPage.getValues(), Arrays.asList(expectedData(F1, F2)));
    }

    @Test(dependsOnMethods = "viewRecordTest")
    public void editRecordTest() {

        ArithmeticFunctionEditPage arithmeticFunctionEditPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .editRow()
                .inputInitialValue(F3, F4);

        Assert.assertEquals(arithmeticFunctionEditPage.getValues(), Arrays.asList(expectedData(F3, F4)));
    }

    @Test(dependsOnMethods = "editRecordTest")
    public void createNewRecordNegativeStringTest() {

        ErrorPage errorPage = new MainPage(getDriver())
                .clickMenuArithmeticFunction()
                .editRow()
                .inputInitialValue(STRING, STRING)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), errorPage.ERROR_MESSAGE);
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