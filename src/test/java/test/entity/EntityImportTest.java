package test.entity;

import model.entity.common.MainPage;
import model.entity.edit.ImportValuesEditPage;
import model.entity.table.ImportPage;
import model.entity.table.ImportValuesPage;
import model.entity.common.RecycleBinPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class EntityImportTest extends BaseTest {

    private static final String STRING_VALUE = "Denys_String";
    private static final String TEXT_VALUE = "Denys_Text";
    private static final String INTEGER_VALUE = "2";
    private static final String INTEGER_VALUE2 = "7";
    private static final String DECIMAL_VALUE = "2.55";
    private static final String USER_VALUE = "User 1 Demo";
    private static final By BY_IMPORT_ENTITY = By.xpath("//a[@href='index.php?action=action_list&entity_id=17']");
    private static final By BY_IMPORT_FOLDER = By.xpath("//i[text()='create_new_folder']");
    private static final By BY_DO_IMPORT_BUTTON = By.xpath("//input[@value='Do import']");
    private static final By BY_FILTERED_IMPORT2 = By.xpath("//input[@value='Filtered Import2']");
    private static final By BY_FILTERED_IMPORT3 = By.xpath("//input[@value='Filtered Import3']");
    private static final By BY_INT_FIELD = By.xpath("//input[@id='int']");
    private static final By BY_CUSTOM_IMPORT_BUTTON = By.xpath("//input[@value='Custom Import']");
    private static final By BY_SELECT_RECORD = By.xpath("//i[text()='done_all']");
    private static final By BY_SAVE_BUTTON = By.xpath("//button[@id='pa-entity-form-save-btn']");
    private static final By BY_IMPORTED_ROW = By.xpath("//tbody/tr");
    private static final By BY_IMPORTED_EMBEDED_ROW = By.xpath("//tbody/tr");
    private static final By BY_CREATE_DATE_FIELD = By.id("date");
    private static final By BY_CREATE_DATETIME_FIELD = By.id("datetime");
    private static final By BY_CREATE_IMPORT_TAB = By.xpath("//p[contains(text(),'Import values')]");
    private static final By BY_CREATE_IMPORT_ICON = By.xpath("//i[contains(text(),'create_new_folder')]");
    private static final By BY_CREATE_STRING_FIELD = By.xpath("//input[@id='string']");
    private static final By BY_CREATE_STRING_FIELD2 = By.xpath("//textarea[@id='t-21-r-1-string']");
    private static final By BY_CREATE_TEXT_FIELD = By.xpath("//textarea[@id='text']");
    private static final By BY_CREATE_TEXT_FIELD2 = By.xpath("//textarea[@id='t-21-r-1-text']");
    private static final By BY_CREATE_INT_FIELD = By.xpath("//input[@id='int']");
    private static final By BY_CREATE_INT_FIELD2 = By.xpath("//textarea[@id='t-21-r-1-int']");
    private static final By BY_CREATE_DECIMAL_FIELD = By.xpath("//input[@id='decimal']");
    private static final By BY_CREATE_DECIMAL_FIELD2 = By.xpath("//textarea[@id='t-21-r-1-decimal']");
    private static final By BY_SELECT_USER_EMBED = By.xpath("//select[@id='t-21-r-1-user']");
    private static final By BY_CREATE_SAVE_BUTTON = By.xpath("//button[@id='pa-entity-form-save-btn']");
    private static final By BY_CREATE_EMDEDED_BTN = By.xpath("//button[@data-table_id='21']");
    private static final By BY_SELECT_FOR_EMBEDED = By.xpath("//input[@value='Select For Embeded']");
    private static final By BY_SELECT_FOR_EMBEDED_CUSTOM = By.xpath("//input[@value='Select For Embeded Custom']");
    private static final By BY_CHECKBOX_EMBEDED = By.xpath("//span[@class='check']");
    private static final By BY_BUTTON_OK1 = By.xpath("//div[@id='pa-ajax-data-27']/div/div/div[2]/button[1]");
    private static final By BY_BUTTON_OK2 = By.xpath("//div[@id='pa-ajax-data-56']/div/div/div[2]/button[1]");
    private static final String STRING_INP = UUID.randomUUID().toString();

    @Test
    public void deleteRecordFromEntityImport() {

        final String str = UUID.randomUUID().toString();

        RecycleBinPage recycleBinPage = new ImportValuesPage(getDriver())
                .clickMenuImportValues()
                .clickNewFolder()
                .sendKeys(str)
                .clickSaveButton()
                .deleteRow()
                .clickRecycleBin();

        Assert.assertEquals(recycleBinPage.getDeletedImportValue(), str);
    }

    @Test
    public void doImportTest() {

        final String intInp = String.valueOf((int) (Math.random() * 100));
        final String decimalInp = String.valueOf(new DecimalFormat("0.00").format(Math.random() * 100));
        final List<String> expectedValues = Arrays.asList(STRING_INP, "test text", intInp, decimalInp, "date", "dateTime");

        ImportValuesEditPage importValuesEditPage = new MainPage(getDriver())
                .clickMenuImportValues()
                .clickNewFolder()
                .fillOutForm(STRING_INP, "test text", intInp, decimalInp);

        expectedValues.set(4, importValuesEditPage.getDate());
        expectedValues.set(5, importValuesEditPage.getDateTime());

        importValuesEditPage.clickSaveButton();

        Assert.assertEquals(new ImportValuesPage(getDriver()).getRowCount(), 1);
        Assert.assertEquals(new ImportValuesPage(getDriver()).getRow(0), expectedValues);

        ImportPage importPage = new MainPage(getDriver())
                .clickMenuImport()
                .clickNewFolder()
                .clickDoImportButton()
                .clickImportButton()
                .clickSaveButton();

        Assert.assertEquals(importPage.getRowCount(), 1);
        Assert.assertEquals(importPage.getRow(0), expectedValues);
    }

    @Test
    public void customImportButtonTest() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        createRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE, DECIMAL_VALUE);

        WebElement importEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, importEntity);
        WebElement importFolder = driver.findElement(BY_IMPORT_FOLDER);
        importFolder.click();
        WebElement customImportButton = driver.findElement(BY_CUSTOM_IMPORT_BUTTON);
        customImportButton.click();
        WebElement selectRecord = driver.findElement(BY_SELECT_RECORD);
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord));
        selectRecord.click();
        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), "This is a custom TEXT");
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE);
        Assert.assertEquals(fieldDecimal.getText(), DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    @Test
    public void filteredImport2ButtonTest() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        createRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE2, DECIMAL_VALUE);

        WebElement importEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, importEntity);
        WebElement createImportFolder = driver.findElement(BY_IMPORT_FOLDER);
        createImportFolder.click();
        WebElement filteredImport2 = driver.findElement(BY_FILTERED_IMPORT2);
        filteredImport2.click();
        WebElement selectRecord = driver.findElement(BY_SELECT_RECORD);
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord));
        selectRecord.click();
        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), STRING_VALUE);
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE2);
        Assert.assertEquals(fieldDecimal.getText(), DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    @Test
    public void filteredImport3ButtonTest() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        createRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE, DECIMAL_VALUE);

        WebElement importEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, importEntity);
        WebElement importFolder = driver.findElement(BY_IMPORT_FOLDER);
        importFolder.click();
        WebElement fillInfField = driver.findElement(BY_INT_FIELD);
        ProjectUtils.inputKeys(driver, fillInfField, INTEGER_VALUE);
        WebElement filteredImport3 = driver.findElement(BY_FILTERED_IMPORT3);
        filteredImport3.click();
        WebElement selectRecord = driver.findElement(BY_SELECT_RECORD);
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord));
        selectRecord.click();
        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), STRING_VALUE);
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE);
        Assert.assertEquals(fieldDecimal.getText(), DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    @Test
    public void selectForEmbededButtonTest(){

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        createEmbededRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE, DECIMAL_VALUE);

        WebElement importEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, importEntity);
        WebElement importFolder = driver.findElement(BY_IMPORT_FOLDER);
        importFolder.click();
        WebElement selectForEmbeded = driver.findElement(BY_SELECT_FOR_EMBEDED);
        selectForEmbeded.click();
        WebElement checkbox = driver.findElement(BY_CHECKBOX_EMBEDED);
        checkbox.click();
        getWebDriverWait().until(TestUtils.movingIsFinished(checkbox));
        WebElement buttonOK = driver.findElement(BY_BUTTON_OK1);
        buttonOK.click();
        wait.until(ExpectedConditions.invisibilityOf(buttonOK));
        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();
        WebElement openRecord = driver.findElement(By.xpath("//td/a[contains(text(), '0')]"));
        openRecord.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_EMBEDED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), STRING_VALUE);
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE);
        Assert.assertEquals(fieldDecimal.getText(), DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    @Test
    public void selectForEmbededCustomButtonTest(){

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        createEmbededRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE, DECIMAL_VALUE);

        WebElement importEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, importEntity);
        WebElement importFolder = driver.findElement(BY_IMPORT_FOLDER);
        importFolder.click();
        WebElement selectForEmbededCustom = driver.findElement(BY_SELECT_FOR_EMBEDED_CUSTOM);
        selectForEmbededCustom.click();
        WebElement checkbox = driver.findElement(BY_CHECKBOX_EMBEDED);
        checkbox.click();
        getWebDriverWait().until(TestUtils.movingIsFinished(checkbox));
        WebElement buttonOk = driver.findElement(BY_BUTTON_OK2);
        buttonOk.click();
        wait.until(ExpectedConditions.invisibilityOf(buttonOk));
        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();
        WebElement openRecord = driver.findElement(By.xpath("//td/a[contains(text(), '0')]"));
        openRecord.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_EMBEDED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), "This is a custom text");
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE);
        Assert.assertEquals(fieldDecimal.getText(), DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    public void createRecordInImportValuesEntity(WebDriver driver, String str, String text, String integ, String decimal) {

        openImportValuesTab(driver);
        WebElement stringField = driver.findElement(BY_CREATE_STRING_FIELD);
        stringField.sendKeys(str);
        WebElement textField = driver.findElement(BY_CREATE_TEXT_FIELD);
        textField.sendKeys(text);
        WebElement intField = driver.findElement(BY_CREATE_INT_FIELD);
        intField.sendKeys(integ);
        WebElement decimalField = driver.findElement(BY_CREATE_DECIMAL_FIELD);
        decimalField.sendKeys(decimal);
        WebElement saveButton = driver.findElement(BY_CREATE_SAVE_BUTTON);
        ProjectUtils.click(driver, saveButton);
    }

    public void createEmbededRecordInImportValuesEntity(WebDriver driver, String str, String text, String integ, String decimal){

        openImportValuesTab(driver);
        WebElement createEmdededRow = driver.findElement(BY_CREATE_EMDEDED_BTN);
        createEmdededRow.click();

        WebElement stringField = driver.findElement(BY_CREATE_STRING_FIELD);
        stringField.sendKeys(str);
        WebElement textField = driver.findElement(BY_CREATE_TEXT_FIELD);
        textField.sendKeys(text);
        WebElement intField = driver.findElement(BY_CREATE_INT_FIELD);
        intField.sendKeys(integ);
        WebElement decimalField = driver.findElement(BY_CREATE_DECIMAL_FIELD);
        decimalField.sendKeys(decimal);
        WebElement dateField = driver.findElement(BY_CREATE_DATE_FIELD);
        dateField.click();
        WebElement dateTimeField = driver.findElement(BY_CREATE_DATETIME_FIELD);
        dateTimeField.click();

        WebElement stringField2 = driver.findElement(BY_CREATE_STRING_FIELD2);
        stringField2.sendKeys(str);
        WebElement textField2 = driver.findElement(BY_CREATE_TEXT_FIELD2);
        textField2.sendKeys(text);
        WebElement intField2 = driver.findElement(BY_CREATE_INT_FIELD2);
        intField2.sendKeys(integ);
        WebElement decimalField2 = driver.findElement(BY_CREATE_DECIMAL_FIELD2);
        decimalField2.sendKeys(decimal);
        Select userButton = new Select(driver.findElement(BY_SELECT_USER_EMBED));
        userButton.selectByIndex(1);
        WebElement saveButton = driver.findElement(BY_CREATE_SAVE_BUTTON);
        ProjectUtils.click(driver, saveButton);
    }

    public void openImportValuesTab (WebDriver driver){
        WebElement importValuesTab = driver.findElement(BY_CREATE_IMPORT_TAB);
        ProjectUtils.click(driver, importValuesTab);
        WebElement importValuesIcon = driver.findElement(BY_CREATE_IMPORT_ICON);
        importValuesIcon.click();
    }
}
