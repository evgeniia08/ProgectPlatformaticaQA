package test.entity;

import model.entity.table.ParentPage;
import model.entity.view.ParentViewPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import model.entity.common.MainPage;
import static runner.ProjectUtils.createUUID;

@Run(run = RunType.Multiple)
public class EntityParentTest extends BaseTest {

    private static final String STRING = UUID.randomUUID().toString();
    private static final String COMMENT = "Comment";
    private static final String INT_ = "30";
    private static final String DECIMAL = "44.55";
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private static final String DATE_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    private static final String EDIT_TITLE = String.format("%s_EditTextAllNew", createUUID());
    private static final String EDIT_COMMENTS = "New comment";
    private static final String EDIT_INT = "7";
    private static final String EDIT_DECIMAL = "77.99";
    private static final String EDIT_DATE = "01/01/2021";
    private static final String EDIT_DATE_TIME = "02/22/2021 01:00:00";
    private static final String USER_DEMO = "User 1 Demo";

    @Test
    public void createParent() {

        ParentPage parentPage = new MainPage(getDriver())
                .clickMenuParent()
                .clickNewFolder()
                .fillOutParentForm(STRING, COMMENT, INT_, DECIMAL, DATE, DATE_TIME)
                .clickSaveButton();

        Assert.assertEquals(parentPage.getTitleText(), STRING);
        Assert.assertEquals(parentPage.getCommentsText(), COMMENT);
        Assert.assertEquals(parentPage.getNumberText(), INT_);
        Assert.assertEquals(parentPage.getNumber1Text(), DECIMAL);
        Assert.assertEquals(parentPage.getDataText(), DATE);
        Assert.assertEquals(parentPage.getDefaultUser(), (USER_DEMO));
        Assert.assertEquals(parentPage.getRowCount(), 1);
    }

    @Test(dependsOnMethods = {"createParent"})
    public void viewParent() {

        ParentViewPage parentViewPage = new MainPage(getDriver())
                .clickMenuParent()
                .clickListButton()
                .viewRow();

        Assert.assertEquals(parentViewPage.viewTitle(), STRING);
        Assert.assertEquals(parentViewPage.viewComment(), COMMENT);
        Assert.assertEquals(parentViewPage.viewInt(), INT_);
        Assert.assertEquals(parentViewPage.viewDecimal(), DECIMAL);
        Assert.assertEquals(parentViewPage.viewDate(), DATE);
        Assert.assertEquals(parentViewPage.viewUser(), (USER_DEMO));
    }

    @Test(dependsOnMethods = {"viewParent"})
    public void editParent() {

        ParentPage parentPage = new MainPage(getDriver())
                .clickMenuParent()
                .editRow()
                .fillOutParentForm(EDIT_TITLE, EDIT_COMMENTS, EDIT_INT, EDIT_DECIMAL, EDIT_DATE, EDIT_DATE_TIME)
                .clickSaveButton();

        Assert.assertEquals(parentPage.getRowCount(), 1);
        Assert.assertEquals(parentPage.getTitleText(), EDIT_TITLE);
        Assert.assertEquals(parentPage.getCommentsText(), EDIT_COMMENTS);
        Assert.assertEquals(parentPage.getNumberText(), EDIT_INT);
        Assert.assertEquals(parentPage.getNumber1Text(), EDIT_DECIMAL);
    }

    @Test(dependsOnMethods = {"editParent"})
    public void deleteParent() {

        ParentPage parentPage = new MainPage(getDriver())
                .clickMenuParent()
                .deleteRow();

        Assert.assertEquals(parentPage.getRowCount(), 0);
    }
}

