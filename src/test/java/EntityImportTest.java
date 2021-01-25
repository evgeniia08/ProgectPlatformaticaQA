import model.ImportValuesPage;
import model.MainPage;
import model.RecycleBinPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.List;
import java.util.UUID;

public class EntityImportTest extends BaseTest {

    @Ignore
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

    private static final String STRING_VALUE = "Denys_String";
    private static final String TEXT_VALUE = "Denys_Text";
    private static final String INTEGER_VALUE = "2";
    private static final String DECIMAL_VALUE = "2.55";
    private static final String USER_VALUE = "User 1 Demo";
    private static final By BY_IMPORT_ENTITY = By.xpath("//a[@href='index.php?action=action_list&entity_id=17']");
    private static final By BY_IMPORT_FOLDER = By.xpath("//i[text()='create_new_folder']");
    private static final By BY_DO_IMPORT_BUTTON = By.xpath("//input[@value='Do import']");
    private static final By BY_CUSTOM_IMPORT_BUTTON = By.xpath("//input[@value='Custom Import']");
    private static final By BY_SELECT_RECORD = By.xpath("//i[text()='done_all']");
    private static final By BY_SAVE_BUTTON = By.xpath("//button[@id='pa-entity-form-save-btn']");
    private static final By BY_IMPORTED_ROW = By.xpath("//tbody/tr");
    private static final By BY_CREATE_IMPORT_TAB = By.xpath("//p[contains(text(),'Import values')]");
    private static final By BY_CREATE_IMPORT_ICON = By.xpath("//i[contains(text(),'create_new_folder')]");
    private static final By BY_CREATE_STRING_FIELD = By.xpath("//input[@id='string']");
    private static final By BY_CREATE_TEXT_FIELD = By.xpath("//textarea[@id='text']");
    private static final By BY_CREATE_INT_FIELD = By.xpath("//input[@id='int']");
    private static final By BY_CREATE_DECIMAL_FIELD = By.xpath("//input[@id='decimal']");
    private static final By BY_CREATE_SAVE_BUTTON = By.xpath("//button[@id='pa-entity-form-save-btn']");

    @Test
    public void doImportButton() throws InterruptedException{

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        createRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE, DECIMAL_VALUE);

        WebElement ImportEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, ImportEntity);

        WebElement createImportFolder = driver.findElement(BY_IMPORT_FOLDER);
        createImportFolder.click();

        WebElement doImportButton = driver.findElement(BY_DO_IMPORT_BUTTON);
        doImportButton.click();

        WebElement selectRecord = driver.findElement(BY_SELECT_RECORD);
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord)).click();

        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]/a/div"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]/a/div"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]/a/div"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]/a/div"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), STRING_VALUE);
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE);
        Assert.assertEquals(fieldDecimal.getText(),DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    @Test
    public void customImportButton() throws InterruptedException{

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        createRecordInImportValuesEntity(driver, STRING_VALUE, TEXT_VALUE, INTEGER_VALUE, DECIMAL_VALUE);

        WebElement ImportEntity = driver.findElement(BY_IMPORT_ENTITY);
        ProjectUtils.click(driver, ImportEntity);

        WebElement createImportFolder = driver.findElement(BY_IMPORT_FOLDER);
        createImportFolder.click();

        WebElement doImportButton = driver.findElement(BY_CUSTOM_IMPORT_BUTTON);
        doImportButton.click();

        WebElement selectRecord = driver.findElement(BY_SELECT_RECORD);
        wait.until(ExpectedConditions.elementToBeClickable(selectRecord));
        selectRecord.click();

        WebElement saveButton = driver.findElement(BY_SAVE_BUTTON);
        ProjectUtils.scroll(driver, saveButton);
        saveButton.click();

        List<WebElement> importedRow = driver.findElements(BY_IMPORTED_ROW);
        Assert.assertEquals(importedRow.size(), 1);

        WebElement fieldString = importedRow.get(0).findElement(By.xpath("//td[2]/a/div"));
        WebElement fieldText = importedRow.get(0).findElement(By.xpath("//td[3]/a/div"));
        WebElement fieldInt = importedRow.get(0).findElement(By.xpath("//td[4]/a/div"));
        WebElement fieldDecimal = importedRow.get(0).findElement(By.xpath("//td[5]/a/div"));
        WebElement fieldUser = importedRow.get(0).findElement(By.xpath("//td[9]"));

        Assert.assertEquals(fieldString.getText(), "This is a custom TEXT");
        Assert.assertEquals(fieldText.getText(), TEXT_VALUE);
        Assert.assertEquals(fieldInt.getText(), INTEGER_VALUE);
        Assert.assertEquals(fieldDecimal.getText(), DECIMAL_VALUE);
        Assert.assertEquals(fieldUser.getText(), USER_VALUE);
    }

    public void createRecordInImportValuesEntity(WebDriver driver, String str, String text, String integ, String decimal){

        WebElement importValuesTab = driver.findElement(BY_CREATE_IMPORT_TAB);
        ProjectUtils.click(driver, importValuesTab);
        WebElement createImportValuesIcon = driver.findElement(BY_CREATE_IMPORT_ICON);
        createImportValuesIcon.click();

        WebElement stringInImportValueField = driver.findElement(BY_CREATE_STRING_FIELD);
        stringInImportValueField.sendKeys(str);
        WebElement textInImportValueField = driver.findElement(BY_CREATE_TEXT_FIELD);
        textInImportValueField.sendKeys(text);
        WebElement intInImportValueField = driver.findElement(BY_CREATE_INT_FIELD);
        intInImportValueField.sendKeys(String.valueOf(integ));
        WebElement decimalInImportValueField = driver.findElement(BY_CREATE_DECIMAL_FIELD);
        decimalInImportValueField.sendKeys(String.valueOf(decimal));
        WebElement saveButton = driver.findElement(BY_CREATE_SAVE_BUTTON);
        ProjectUtils.click(driver, saveButton);
    }
}