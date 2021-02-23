package test.entity;

import model.entity.common.MainPage;
import model.entity.table.TagListPage;
import model.entity.table.TagPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityTagTest extends BaseTest {

    private static final String TAG_1 = String.format("TempTag1_%s", RandomStringUtils.randomNumeric(5));
    private static final String TAG_2 = String.format("TempTag2_%s", RandomStringUtils.randomNumeric(5));
    private static final String TAG_3 = String.format("TempTag3_%s", RandomStringUtils.randomNumeric(5));
    private static final String STRING = String.format("Record_1_%s", RandomStringUtils.randomNumeric(5));
    private static final String TEXT = RandomStringUtils.randomAlphanumeric(25);
    private static final String INT = "1025";
    private static final String DECIMAL = "512.99";
    private static final String DATE = ProjectUtils.getGMTDate();
    private static final String DATE_TIME = DATE + " 09:55:00";
    private String user;

    @Test
    public void createTagRecordTest() {

        List<String> expectedData = Arrays.asList(STRING, TEXT, INT, DECIMAL, DATE, DATE_TIME, "", user);

        MainPage mainPage = new MainPage(getDriver());
        user = mainPage.getCurrentUser();
        expectedData.set(7, user);
        TagListPage tagListPage = mainPage
                .clickMenuTag()
                .clickNewFolder()
                .fillOutTagEditForm(STRING, TEXT, INT, DECIMAL, DATE, DATE_TIME, user)
                .clickSaveButton();

        Assert.assertEquals(tagListPage.getRowCount(), 1);
        Assert.assertEquals(tagListPage.getRow(0), expectedData);
        Assert.assertEquals(tagListPage.getRowIconClass(0), AppConstant.RECORD_ICON_CLASS);
    }

    @Test(dependsOnMethods = "createTagRecordTest")
    public void createTagTest() {

        TagPage tagPage = new MainPage(getDriver())
                .clickMenuTag()
                .clickTagButton()
                .createNewTag(TAG_1);

        Assert.assertEquals(tagPage.getTagCountByName(TAG_1), 1);

        tagPage.createNewTag(TAG_2).createNewTag(TAG_3);

        Assert.assertEquals(tagPage.getTagCountByName(TAG_1), 1);
        Assert.assertEquals(tagPage.getTagCountByName(TAG_2), 1);
        Assert.assertEquals(tagPage.getTagCountByName(TAG_3), 1);
    }

    @Test(dependsOnMethods = "createTagTest")
    public void assignTagTest() {

        TagPage tagPage = new MainPage(getDriver())
                .clickMenuTag()
                .clickTagButton()
                .selectTagRecordByString(STRING)
                .selectTagByName(TAG_1)
                .clickAssignButton();

        Assert.assertEquals(tagPage.getRowCount(), 1);
        Assert.assertEquals(tagPage.getTagsByRecordString(STRING), Arrays.asList(TAG_1));
    }

    @Test(dependsOnMethods = "assignTagTest")
    public void reAssignTagTest() {

        TagPage tagPage = new MainPage(getDriver())
                .clickMenuTag()
                .clickTagButton()
                .selectTagRecordByString(STRING)
                .selectTagByName(TAG_2)
                .selectTagByName(TAG_3)
                .clickAssignButton();

        Assert.assertEquals(tagPage.getRowCount(), 1);
        Assert.assertEquals(tagPage.getTagsByRecordString(STRING), Arrays.asList(TAG_2, TAG_3));
    }

    @Test(dependsOnMethods = "reAssignTagTest")
    public void deleteTagTest() {

        TagPage tagPage = new MainPage(getDriver())
                .clickMenuTag()
                .clickTagButton();

        List<String> expectedTags = tagPage.getTags();

        tagPage.selectTagByName(TAG_1).clickDeleteTagsButton();
        expectedTags.remove(TAG_1);

        Assert.assertEquals(tagPage.getTagCountByName(TAG_1), 0);
        Assert.assertEquals(tagPage.getTags(), expectedTags);
        Assert.assertEquals(tagPage.getRowCount(), 1);
    }

    @Test(dependsOnMethods = "deleteTagTest")
    public void deleteAssignedTagTest() {

        TagPage tagPage = new MainPage(getDriver())
                .clickMenuTag()
                .clickTagButton();

        List<String> expectedTags = tagPage.getTags();

        tagPage.selectTagByName(TAG_2).clickDeleteTagsButton();
        expectedTags.remove(TAG_2);

        Assert.assertEquals(tagPage.getTagCountByName(TAG_2), 0);
        Assert.assertEquals(tagPage.getTags(), expectedTags);
        Assert.assertEquals(tagPage.getRowCount(), 1);
        Assert.assertEquals(tagPage.getTagsByRecordString(STRING), Arrays.asList(TAG_3));
        Assert.assertNull(tagPage.selectTagByName(TAG_3).clickDeleteTagsButton().getTagsByRecordString(STRING));

        expectedTags.remove(TAG_3);
        Assert.assertEquals(tagPage.getTagCountByName(TAG_3), 0);
        Assert.assertEquals(tagPage.getTags(), expectedTags);
    }

    @Test(dependsOnMethods = "deleteAssignedTagTest")
    public void deleteTagRecordTest() {

        TagPage tagPage = new MainPage(getDriver())
                .clickMenuTag()
                .clickTagButton();

        Assert.assertEquals(tagPage.deleteRow(0).getRowCount(), 0);
        Assert.assertEquals(tagPage.clickListButton().getRowCount(), 0);
    }
}
