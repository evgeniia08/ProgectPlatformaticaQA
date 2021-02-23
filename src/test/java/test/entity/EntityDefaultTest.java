package test.entity;

import model.entity.edit.DefaultEmbeddedEditPage;
import model.entity.edit.DefaultEditPage;
import model.entity.table.DefaultPage;
import model.entity.common.MainPage;
import model.entity.common.RecycleBinPage;
import model.entity.view.DefaultViewPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Run;
import runner.type.RunType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityDefaultTest extends BaseTest {

    private static class FieldValues {
        String lineNumber;
        String fieldString;
        String fieldText;
        String fieldInt;
        String fieldDecimal;
        String fieldDate;
        String fieldDateTime;
        String fieldUser;

        private FieldValues(String lineNumber, String fieldString, String fieldText, String fieldInt, String fieldDecimal, String fieldDate,
                            String fieldDateTime, String fieldUser) {
            this.lineNumber = lineNumber;
            this.fieldString = fieldString;
            this.fieldText = fieldText;
            this.fieldInt = fieldInt;
            this.fieldDecimal = fieldDecimal;
            this.fieldDate = fieldDate;
            this.fieldDateTime = fieldDateTime;
            this.fieldUser = fieldUser;
        }
    }

    private final FieldValues defaultValues = new FieldValues(
            null,
            "DEFAULT STRING VALUE",
            "DEFAULT TEXT VALUE",
            "55",
            "110.32",
            "01/01/1970",
            "01/01/1970 00:00:00",
            "USER 1 DEMO");

    private final FieldValues defaultEmbeddedValues = new FieldValues(
            "1",
            "Default String",
            "Default text",
            "77",
            "153.17",
            "",
            "",
            "Not selected");

    private final FieldValues changedDefaultValues = new FieldValues(
            null,
            "Changed default String",
            "Changed default Text",
             String.valueOf((int) (Math.random() * 100)),
             "33.33", //String.valueOf((int) (Math.random()*20000) / 100.0),
            "01/01/2021",
            "01/01/2021 12:34:56",
            "user115@tester.com");

    private final FieldValues changedEmbeddedValues = new FieldValues(
            "1",
            "Changed EmbedD String",
            "Changed EmbedD Text",
            String.valueOf((int) (Math.random() * 100)),
            "55.55",   //String.valueOf((int) (Math.random()*20000) / 100.0 after a bug will be fixed),
            "12/12/2020",
            "12/12/2020 00:22:22",
            "User 4");

    private final FieldValues newValues = new FieldValues(
            null,
            UUID.randomUUID().toString(),
            "Some random text as Edited Text Value",
             String.valueOf((int) (Math.random() * 100)),
            "77.77",    //String.valueOf((int) (Math.random()*20000) / 100.0 after a bug will be fixed),
            "30/12/2020",
            "30/12/2020 12:34:56",
            "user100@tester.com");

    private final List<String> DEFAULT_VALUES = new ArrayList<>(Arrays.asList(defaultValues.fieldString, defaultValues.fieldText,
            defaultValues.fieldInt, defaultValues.fieldDecimal,
            defaultValues.fieldDate, defaultValues.fieldDateTime, defaultValues.fieldUser));

    private final List<String> NEW_VALUES = new ArrayList<>(Arrays.asList("", newValues.fieldString, newValues.fieldText,
                  newValues.fieldInt, newValues.fieldDecimal,
                  newValues.fieldDate, newValues.fieldDateTime, "", "", newValues.fieldUser, "menu"));

    private final List<String> CHANGED_DEFAULT_VALUES =
                    new ArrayList<>(Arrays.asList(changedDefaultValues.fieldString, changedDefaultValues.fieldText,
                    changedDefaultValues.fieldInt, changedDefaultValues.fieldDecimal, changedDefaultValues.fieldDate,
                    changedDefaultValues.fieldDateTime));

    private final List<String> CHANGED_EMBEDDED_VALUES = new ArrayList<>(Arrays.asList(
            changedEmbeddedValues.lineNumber, changedEmbeddedValues.fieldString, changedEmbeddedValues.fieldText,
            changedEmbeddedValues.fieldInt, changedEmbeddedValues.fieldDecimal, changedEmbeddedValues.fieldDate,
            changedEmbeddedValues.fieldDateTime, "", "", changedEmbeddedValues.fieldUser));

    private final List<String> DEFAULT_EMBEDDED_VALUES =
            new ArrayList<>(Arrays.asList(defaultEmbeddedValues.lineNumber, defaultEmbeddedValues.fieldString,
            defaultEmbeddedValues.fieldText, defaultEmbeddedValues.fieldInt, defaultEmbeddedValues.fieldDecimal,
            defaultEmbeddedValues.fieldDate, defaultEmbeddedValues.fieldDateTime, defaultEmbeddedValues.fieldUser));

    @Test
    public void checkDefaultValuesAndUpdate() {

        MainPage mainPage = new MainPage(getDriver());
        DefaultEditPage defaultEditPage = mainPage
                .clickMenuDefault()
                .clickNewFolder();

        Assert.assertEquals(defaultEditPage.toList(), DEFAULT_VALUES);

        defaultEditPage.sendKeys(changedDefaultValues.fieldString, changedDefaultValues.fieldText, changedDefaultValues.fieldInt,
                changedDefaultValues.fieldDecimal, changedDefaultValues.fieldDate, changedDefaultValues.fieldDateTime, changedDefaultValues.fieldUser);

        DefaultEmbeddedEditPage embeddedTable = defaultEditPage
                .getEmbededTable()
                .clickNewEmbeddedRow();

        Assert.assertEquals(embeddedTable.getLineNumber(0), changedEmbeddedValues.lineNumber);
        Assert.assertEquals(embeddedTable.getRow(0), DEFAULT_EMBEDDED_VALUES);

        embeddedTable.sendKeys(0, changedEmbeddedValues.fieldString,
                changedEmbeddedValues.fieldText, changedEmbeddedValues.fieldInt, changedEmbeddedValues.fieldDecimal,
                changedEmbeddedValues.fieldDate, changedEmbeddedValues.fieldDateTime, changedEmbeddedValues.fieldUser);

        DefaultPage defaultPage = defaultEditPage.clickSaveButton();
        Assert.assertEquals(defaultPage.getRowCount(), 1);

        defaultPage.viewRow(0);
        DefaultViewPage viewPage = new DefaultViewPage(getDriver());

        Assert.assertEquals(viewPage.getValues(), CHANGED_DEFAULT_VALUES);
        Assert.assertEquals(viewPage.getUser(), changedDefaultValues.fieldUser);
        Assert.assertEquals(viewPage.getEmbeddedRow(0), CHANGED_EMBEDDED_VALUES);
    }

    @Test(dependsOnMethods = "checkDefaultValuesAndUpdate")
    public void deleteRecord() {

        MainPage mainPage = new MainPage(getDriver());
        DefaultPage defaultPage = mainPage.clickMenuDefault()
                .deleteRow();

        Assert.assertEquals(defaultPage.getRowCount(), 0);

        RecycleBinPage recycleBinPage = mainPage.clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 1);
        Assert.assertEquals(recycleBinPage.getCellValue(0, 1), changedDefaultValues.fieldString);

        recycleBinPage.clickDeletePermanently(0);
        Assert.assertEquals(recycleBinPage.getRowCount(), 0);
    }

    @ Test (dependsOnMethods = "deleteRecord")
    public void editExistingRecord() {

        DefaultPage defaultPage = new MainPage(getDriver())
                .clickMenuDefault()
                .clickNewFolder()
                .clickSaveButton()
                .editRow(0)
                .sendKeys(newValues.fieldString, newValues.fieldText, newValues.fieldInt,
                newValues.fieldDecimal, newValues.fieldDate, newValues.fieldDateTime, newValues.fieldUser)
                .clickSaveButton();

        Assert.assertEquals(defaultPage.getRowCount(), 1);
        Assert.assertEquals(defaultPage.getRow(0, "//td"), NEW_VALUES);
    }
}