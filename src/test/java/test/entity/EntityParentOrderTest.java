package test.entity;

import model.entity.table.ParentPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;
import model.entity.common.MainPage;

@Run(run = RunType.Multiple)
public class EntityParentOrderTest extends BaseTest {

    private static final String DRUG_THE_ROW_UP = "Drag the row up";
    private static final String DRUG_THE_ROW_DOWN = "Drag the row down";

    @Test
    public void createMultipleParent() {

        ParentPage parentPage = new MainPage(getDriver())
                .clickMenuParent()
                .clickNewFolder()
                .sendKeys1(DRUG_THE_ROW_UP)
                .clickSaveButton()
                .clickNewFolder()
                .sendKeys2(DRUG_THE_ROW_DOWN)
                .clickSaveButton();

        Assert.assertEquals(parentPage.getRowCount(), 2);
    }

    @Test(dependsOnMethods = "createMultipleParent")
    public void dragTheRowUp() throws InterruptedException {

        String parentPage = new MainPage(getDriver())
                .clickMenuParent()
                .clickOrderParent()
                .dragUp()
                .checkCell();

        Assert.assertEquals(parentPage, DRUG_THE_ROW_UP);
    }
}

