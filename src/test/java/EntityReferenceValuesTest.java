import model.MainPage;
import model.ReferenceValuesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityReferenceValuesTest extends BaseTest {

    private static final String LABEL = ProjectUtils.createUUID();
    private static final String FILTER_1 = ProjectUtils.createUUID();
    private static final String FILTER_2 = ProjectUtils.createUUID();
    private static final String LABEL_EDITED = "Edited Label";
    private static final String FILTER_1_EDITED = "Edited Filter 1";
    private static final String FILTER_2_EDITED = "Edited Filter 2";
    private static final String DRAFT_LABEL = ProjectUtils.createUUID();
    private static final String DRAFT_FILTER_1 = ProjectUtils.createUUID();
    private static final String DRAFT_FILTER_2 = ProjectUtils.createUUID();
    private static final String DRAFT_LABEL_EDITED = "Edited Draft Label";
    private static final String DRAFT_FILTER_1_EDITED = "Edited Draft Filter 1";
    private static final String DRAFT_FILTER_2_EDITED = "Edited Draft Filter 2";
    private static final String RECORD_ICON_CLASS = AppConstant.RECORD_ICON_CLASS;
    private static final String DRAFT_ICON_CLASS = AppConstant.DRAFT_ICON_CLASS;

    @Test
    public void createRecordTest() {

        List<String> expectedValues = Arrays.asList(LABEL, FILTER_1, FILTER_2);

        ReferenceValuesPage referenceValuesPage = new MainPage(getDriver())
                .clickMenuReferenceValues()
                .clickNewFolder()
                .fillData(LABEL, FILTER_1, FILTER_2)
                .clickSaveButton();

        Assert.assertEquals(referenceValuesPage.getRowCount(), 1);
        Assert.assertEquals(referenceValuesPage.getRow(0), expectedValues);
        Assert.assertEquals(referenceValuesPage.getRowIconClass(0), RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createRecordTest")
    public void viewRecordTest() {

        List<String> expectedValues = Arrays.asList(LABEL, FILTER_1, FILTER_2);

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuReferenceValues()
                .viewRow()
                .getReferenceValues(), expectedValues);
    }

    @Test(dependsOnMethods = "viewRecordTest")
    public void editRecordTest() {

        List<String> expectedValues = Arrays.asList(LABEL_EDITED, FILTER_1_EDITED, FILTER_2_EDITED);

        ReferenceValuesPage referenceValuesPage = new MainPage(getDriver())
                .clickMenuReferenceValues()
                .editRow(0)
                .fillData(LABEL_EDITED, FILTER_1_EDITED, FILTER_2_EDITED)
                .clickSaveButton();

        Assert.assertEquals(referenceValuesPage.getRowCount(), 1);
        Assert.assertEquals(referenceValuesPage.getRow(0), expectedValues);
        Assert.assertEquals(referenceValuesPage.getRowIconClass(0), RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "editRecordTest")
    public void deleteRecordTest() {

        String deletedReferenceValueRecord = String.format("Label: %sFilter 1: %sFilter 2: %s",
                LABEL_EDITED, FILTER_1_EDITED, FILTER_2_EDITED);

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuReferenceValues()
                .deleteRow()
                .getRowCount(), 0);

        Assert.assertEquals(new MainPage(getDriver())
                .clickRecycleBin()
                .getDeletedEntityContent(0), deletedReferenceValueRecord);
    }

    @Test(dependsOnMethods = "deleteRecordTest")
    public void createDraftTest() {

        List<String> expectedValues = Arrays.asList(DRAFT_LABEL, DRAFT_FILTER_1, DRAFT_FILTER_2);

        ReferenceValuesPage referenceValuesPage = new MainPage(getDriver())
                .clickMenuReferenceValues()
                .clickNewFolder()
                .fillData(DRAFT_LABEL, DRAFT_FILTER_1, DRAFT_FILTER_2)
                .clickSaveDraftButton();

        Assert.assertEquals(referenceValuesPage.getRowCount(), 1);
        Assert.assertEquals(referenceValuesPage.getRow(0), expectedValues);
        Assert.assertEquals(referenceValuesPage.getRowIconClass(0), DRAFT_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createDraftTest")
    public void viewDraftTest() {

        List<String> expectedValues = Arrays.asList(DRAFT_LABEL, DRAFT_FILTER_1, DRAFT_FILTER_2);

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuReferenceValues()
                .viewRow()
                .getReferenceValues(), expectedValues);
    }

    @Test(dependsOnMethods = "viewDraftTest")
    public void editDraftTest() {

        List<String> expectedValues = Arrays.asList(DRAFT_LABEL_EDITED, DRAFT_FILTER_1_EDITED, DRAFT_FILTER_2_EDITED);

        ReferenceValuesPage referenceValuesPage = new MainPage(getDriver())
                .clickMenuReferenceValues()
                .editRow(0)
                .fillData(DRAFT_LABEL_EDITED, DRAFT_FILTER_1_EDITED, DRAFT_FILTER_2_EDITED)
                .clickSaveDraftButton();

        Assert.assertEquals(referenceValuesPage.getRowCount(), 1);
        Assert.assertEquals(referenceValuesPage.getRow(0), expectedValues);
        Assert.assertEquals(referenceValuesPage.getRowIconClass(0), DRAFT_ICON_CLASS);
    }

    @Test(dependsOnMethods = "editDraftTest")
    public void deleteDraftTest() {

        String deletedReferenceValueDraft = String.format("Label: %sFilter 1: %sFilter 2: %s",
                DRAFT_LABEL_EDITED, DRAFT_FILTER_1_EDITED, DRAFT_FILTER_2_EDITED);

        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuReferenceValues()
                .deleteRow()
                .getRowCount(), 0);

        Assert.assertEquals(new MainPage(getDriver())
                .clickRecycleBin()
                .getDeletedEntityContent(0), deletedReferenceValueDraft);
    }

    @Test(dependsOnMethods = "deleteDraftTest")
    public void saveRecordAsDraftTest() {

        List<String> expectedValues = Arrays.asList(LABEL, FILTER_1, FILTER_2);

        createRecordTest();

        ReferenceValuesPage referenceValuesPage = new ReferenceValuesPage(getDriver()).editRow().clickSaveDraftButton();

        Assert.assertEquals(referenceValuesPage.getRowCount(), 1);
        Assert.assertEquals(referenceValuesPage.getRow(0), expectedValues);
        Assert.assertEquals(referenceValuesPage.getRowIconClass(0), DRAFT_ICON_CLASS);
    }

    @Test(dependsOnMethods = "saveRecordAsDraftTest")
    public void saveDraftAsRecordTest() {

        List<String> expectedValues = Arrays.asList(LABEL, FILTER_1, FILTER_2);

        ReferenceValuesPage referenceValuesPage = new ReferenceValuesPage(getDriver())
                .clickMenuReferenceValues()
                .editRow()
                .clickSaveButton();

        Assert.assertEquals(referenceValuesPage.getRowCount(), 1);
        Assert.assertEquals(referenceValuesPage.getRow(0), expectedValues);
        Assert.assertEquals(referenceValuesPage.getRowIconClass(0), RECORD_ICON_CLASS);
    }
}
