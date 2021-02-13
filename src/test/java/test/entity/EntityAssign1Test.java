package test.entity;

import model.entity.common.MyAssignmentsPage;
import model.entity.common.RecycleBinPage;
import model.entity.edit.AssignEditPage;
import model.entity.table.AssignPage;
import model.entity.common.MainPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityAssign1Test extends BaseTest {

    private static final String STRING_INP = UUID.randomUUID().toString();
    private static final ProfileType PROFILE_TYPE = ProfileType.DEFAULT;
    private static final String FIRST_USER_NAME =  PROFILE_TYPE.getUserName();
    private static final String FIRST_USER_PASS =  PROFILE_TYPE.getPassword();

    @Test
    public void assignTest() {

        final String intInp = String.valueOf((int)(Math.random() * 100));
        final String decimalInp = String.valueOf(new DecimalFormat("0.00").format(Math.random() * 100));
        final List<String> expectedValues = Arrays.asList(STRING_INP, "test text", intInp, decimalInp, "date", "dateTime");

        AssignEditPage assignEditPage = new MainPage(getDriver())
                .clickMenuAssign()
                .clickNewFolder()
                .fillOutForm(STRING_INP, "test text", intInp, decimalInp);

        expectedValues.set(4, assignEditPage.getDate());
        expectedValues.set(5, assignEditPage.getDateTime());

        AssignPage assignPage = assignEditPage.clickSaveButton().selectUser(PROFILE_TYPE.getUserName());

        Assert.assertEquals(assignPage.getSelectedUser(), PROFILE_TYPE.getUserName());
        Assert.assertEquals(assignPage.getRowCount(), 1);
        Assert.assertEquals(assignPage.getRow(0), expectedValues);

        MyAssignmentsPage myAssignmentsPage = new MainPage(getDriver()).clickMenuMyAssignments();
        myAssignmentsPage.isMyAssignmentsPageOpen();

        Assert.assertEquals(myAssignmentsPage.getRowCount(), 1);
        Assert.assertEquals(assignPage.getRow(0), expectedValues);
    }

    @Ignore
    @Test (dependsOnMethods = "assignTest")
    public void editTest() {

        AssignPage assignPage = new AssignPage(getDriver()).clickMenuAssign();
        final List<String> expectedValues = assignPage.getRow(0);
        ProfileType.renewCredentials();
        assignPage.selectUser(PROFILE_TYPE.getUserName());

        Assert.assertNotSame(assignPage.getCurrentUser(), PROFILE_TYPE.getUserName());
        Assert.assertEquals(assignPage.getSelectedUser(), PROFILE_TYPE.getUserName());

        MyAssignmentsPage myAssignmentsPage = new MainPage(getDriver()).clickMenuMyAssignments();
        myAssignmentsPage.isMyAssignmentsPageOpen();

        Assert.assertEquals(myAssignmentsPage.getRowCount(), 0);

        myAssignmentsPage.logout();
        PROFILE_TYPE.login(getDriver());
        myAssignmentsPage.clickMenuMyAssignments();

        Assert.assertEquals(myAssignmentsPage.getRowCount(), 1);
        Assert.assertEquals(assignPage.getRow(0), expectedValues);
    }

    @Ignore
    @Test (dependsOnMethods = "editTest")
    public void deleteTest() {

        AssignPage assignPage = new AssignPage(getDriver());
        assignPage.logout().login(FIRST_USER_NAME, FIRST_USER_PASS);
        assignPage.clickMenuAssign().selectUser(FIRST_USER_NAME).deleteRow();

        Assert.assertEquals(assignPage.getRowCount(), 0);

        RecycleBinPage recycleBinPage = new RecycleBinPage(getDriver()).clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 1);
        Assert.assertTrue(recycleBinPage.getDeletedEntityContent().contains(STRING_INP));

        MyAssignmentsPage myAssignmentsPage = new MainPage(getDriver()).clickMenuMyAssignments();
        myAssignmentsPage.isMyAssignmentsPageOpen();

        Assert.assertEquals(myAssignmentsPage.getRowCount(), 0);
    }
}

