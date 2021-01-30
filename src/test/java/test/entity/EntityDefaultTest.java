package test.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import model.entity.edit.DefaultEditPage;
import model.entity.table.DefaultPage;
import model.entity.common.MainPage;
import model.entity.common.RecycleBinPage;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.Assert;
import runner.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;

@Run(run = RunType.Multiple)

public class EntityDefaultTest extends BaseTest {

    private class FieldValues {
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

    private static final By BY_RECORD_HAMBURGER_MENU = By.xpath("//button[contains(@data-toggle, 'dropdown')] ");
    private static final By BY_VIEW = By.xpath("//a[text() = 'view']");
    //private static final By BY_SAVE_BUTTON = By.xpath("//button[.='Save']");

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

    private final String[] CHANGED_DEFAULT_VALUES = {changedDefaultValues.fieldString,
                   changedDefaultValues.fieldText, changedDefaultValues.fieldInt, changedDefaultValues.fieldDecimal,
                   changedDefaultValues.fieldDate, changedDefaultValues.fieldDateTime};

    private final String[] CHANGED_EMBEDD_VALUES = {changedEmbeddedValues.lineNumber, changedEmbeddedValues.fieldString,
            changedEmbeddedValues.fieldText, changedEmbeddedValues.fieldInt, changedEmbeddedValues.fieldDecimal,
            changedEmbeddedValues.fieldDate, changedEmbeddedValues.fieldDateTime, null, null, changedEmbeddedValues.fieldUser};

    private final List<String> DEFAULT_EMBEDED_VALUES =
            new ArrayList<>(Arrays.asList(defaultEmbeddedValues.lineNumber, defaultEmbeddedValues.fieldString,
            defaultEmbeddedValues.fieldText, defaultEmbeddedValues.fieldInt, defaultEmbeddedValues.fieldDecimal,
            defaultEmbeddedValues.fieldDate, defaultEmbeddedValues.fieldDateTime, defaultEmbeddedValues.fieldUser));

    private void selectFromRecordMenu (WebDriver driver, By byFunction) {

        driver.findElement(BY_RECORD_HAMBURGER_MENU).click();

        WebElement viewFunction = driver.findElement(byFunction);
        ProjectUtils.click(driver, viewFunction);
    }

    private void assertRecordValues(WebDriver driver, String xpath, String[] changed_default_values) {
        List<WebElement> rows = driver.findElements(By.xpath(xpath));
        for (int i = 0; i < changed_default_values.length; i++) {
            if (changed_default_values[i] != null) {
                Assert.assertEquals(rows.get(i).getText(), changed_default_values[i]);
            }
        }
    }

    @Test
    public void checkDefaultValuesAndUpdate() {

        WebDriver driver = getDriver();

        MainPage mainPage = new MainPage(getDriver());
        DefaultEditPage defaultEditPage = mainPage
                .clickMenuDefault()
                .clickNewFolder();

        Assert.assertEquals(defaultEditPage.toList(), DEFAULT_VALUES);

        defaultEditPage.sendKeys(changedDefaultValues.fieldString, changedDefaultValues.fieldText, changedDefaultValues.fieldInt,
                changedDefaultValues.fieldDecimal, changedDefaultValues.fieldDate, changedDefaultValues.fieldDateTime, changedDefaultValues.fieldUser);

        DefaultEmbeddedPage embeddedTable = defaultEditPage
                .getEmbededTable()
                .clickNewEmbededRow();

        Assert.assertEquals(embeddedTable.getLineNumber(0), changedEmbeddedValues.lineNumber);
        Assert.assertEquals(embeddedTable.getRow(0), DEFAULT_EMBEDED_VALUES);

        embeddedTable.sendKeys(0, changedEmbeddedValues.fieldString,
                changedEmbeddedValues.fieldText, changedEmbeddedValues.fieldInt, changedEmbeddedValues.fieldDecimal,
                changedEmbeddedValues.fieldDate, changedEmbeddedValues.fieldDateTime, changedEmbeddedValues.fieldUser);

        defaultEditPage.clickSaveButton();
//        WebElement saveBtn = driver.findElement(BY_SAVE_BUTTON);
//        ProjectUtils.click(driver, saveBtn);

        selectFromRecordMenu(driver, BY_VIEW);

        assertRecordValues(driver, "//span[@class='pa-view-field']", CHANGED_DEFAULT_VALUES);

        WebElement fieldUser = driver.findElement(By.xpath("//div[@class='form-group']/p"));
        Assert.assertEquals(fieldUser.getText(), changedDefaultValues.fieldUser);

        assertRecordValues(driver, "//table/tbody/tr/td", CHANGED_EMBEDD_VALUES);
    }

    @Test (dependsOnMethods = "checkDefaultValuesAndUpdate")
    public void deleteRecord() {

    //    MainPage mainPage  = new MainPage(getDriver());
        DefaultPage defaultPage = new DefaultPage(getDriver())
                .clickMenuDefault()
                .deleteRow();

      //  defaultPage.deleteRow();

        Assert.assertEquals(defaultPage.getRowCount(), 0);

        RecycleBinPage recycleBinPage = defaultPage.clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getRowCount(), 1);
        Assert.assertEquals(recycleBinPage.getCellValue(0, 1), changedDefaultValues.fieldString);

        recycleBinPage.clickDeletePermanently(0);
        Assert.assertEquals(recycleBinPage.getRowCount(), 0);
    }

    @ Test (dependsOnMethods = "deleteRecord")
    public void editExistingRecord() {

    //    MainPage mainPage = new MainPage(getDriver());
        DefaultPage defaultPage = new DefaultPage(getDriver())
                .clickMenuDefault()
                .clickNewFolder()
                .clickSaveButton();

        DefaultEditPage defaultEditPage = defaultPage.editRow(0);

        defaultEditPage.sendKeys(newValues.fieldString, newValues.fieldText, newValues.fieldInt,
                newValues.fieldDecimal, newValues.fieldDate, newValues.fieldDateTime, newValues.fieldUser);

        defaultPage = defaultEditPage.clickSaveButton();
        Assert.assertEquals(defaultPage.getRowCount(), 1);
        Assert.assertEquals(defaultPage.getRow(0, "//td"), NEW_VALUES);
    }
}