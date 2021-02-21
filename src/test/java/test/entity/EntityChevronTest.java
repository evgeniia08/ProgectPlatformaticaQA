package test.entity;

import model.entity.common.MainPage;
import model.entity.table.ChevronPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import runner.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityChevronTest extends BaseTest {
    // remove me !!!!!
    private WebDriverWait wait;

    private static final String TITLE = UUID.randomUUID().toString();
    private static final String INT_NUMBER = "123";
    private static final String DOUBLE_NUMBER = "4.56";
    private static final String STATUS_NEW = "Fulfillment";
    private static final String STATUS_EDITED = "Pending";

    final String comments = "TEST1";
    final String int_ = "11";
    final String decimal = "0.11";
    final String xpath = "//tr[@data-index='4']/td[2]/a";

    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    public String Data = data.format(new Date());

    SimpleDateFormat Time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public String DataTime = Time.format(new Date());

    List<String> expectedResults = Arrays.asList("Fulfillment", "TEST1", "11", "0.11", Data, DataTime);

    @DataProvider(name = "testData")
    private Object[][] testData1() {
        return new Object[][]{
                {"TEST2", "20", "0.11", Data, DataTime},
                {"TEST3", "30", "0.11", Data, DataTime},
                {"TEST4", "30", "0.11", Data, DataTime},
                {"TEST5", "100", "0.11", Data, DataTime}
        };
    }

    @Test(dependsOnMethods = "createMultipleEntities")
    public void createNewRecord() {
        ChevronPage chevronPage = new MainPage(getDriver())
                .clickMenuChevron()
                .clickNewFolder()
                .chooseRecordStatus()
                .sendKeys(comments, int_, decimal, DataTime, Data)
                .ChooseValues()
                .clickSaveButton();
        Assert.assertEquals(chevronPage.getRow(4), expectedResults);
    }

    @Test(dependsOnMethods = "findChevron")
    public void viewRecord() {
        List<String> page = new MainPage(getDriver())
                .clickMenuChevron()
                .clickRowToView(xpath)
                .getColumn();
        Assert.assertEquals(page,expectedResults);
    }


    @Test(dataProvider = "testData")
    public void createMultipleEntities(String title, String int_, String decimal, String data, String time) {

        ChevronPage chevronPage = new MainPage(getDriver()).clickMenuChevron();
        int rowCount = chevronPage.getRowCount();
        chevronPage.clickNewFolder()
                .chooseRecordStatus()
                .sendKeys(title, int_, decimal, data, time)
                .clickSaveButton();
        Assert.assertEquals(chevronPage.getRowCount(), rowCount + 1);
    }

    @Test(dependsOnMethods = "createNewRecord")
    public void dragTheRowUp() throws InterruptedException {
        String chevronPage = new MainPage(getDriver())
                .clickMenuChevron()
                .orderBy()
                .drugUp()
                .getCellData();
        Assert.assertEquals(chevronPage, "TEST1");
    }

    @Test (dependsOnMethods = "viewRecord")
    public void deleteRecord() {
        ChevronPage chevronPage = new ChevronPage(getDriver());
        Assert.assertEquals(chevronPage
                .clickMenuChevron()
                .deleteRow()
                .getRowCount(), 5);
    }

    @Test(dependsOnMethods = "deleteRecord")
    public void getFullSum() {
        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuChevron()
                .getSum(), 271);
        Assert.assertEquals(new MainPage(getDriver())
                .clickMenuChevron()
                .getAvr(), 54);
    }
    @Test(dependsOnMethods = "dragTheRowUp")
    public void findChevron()  {

        WebDriver driver = getDriver();

        WebElement clickChevron = driver.findElement(By.xpath("//p[contains(text(),'Chevron')]"));
        ProjectUtils.click(driver, clickChevron);

        WebElement clickCreateRecord = driver.findElement(By.xpath("//div[@class = 'card-icon']//i"));
        ProjectUtils.click(driver, clickCreateRecord);

        WebElement addString = driver.findElement(By.xpath("//div[@class = 'filter-option-inner-inner']"));
        ProjectUtils.click(driver, addString);

        WebElement clickString = driver.findElement(By.xpath("//div[contains(text(),'Pending')]"));
        ProjectUtils.click(driver, clickString);

        WebElement checkFulfillment = driver.findElement(By.xpath("//span[contains(text(),'Fulfillment')]"));
        ProjectUtils.click(driver, checkFulfillment);

        WebElement fillTextField = driver.findElement(By.xpath("//textarea[@id = 'text']"));
        fillTextField.sendKeys("This is the sign");

        WebElement fillInt = driver.findElement(By.xpath("//input[@id = 'int']"));
        fillInt.sendKeys("100");

        WebElement fillDec = driver.findElement(By.xpath("//input[@id = 'decimal']"));
        fillDec.sendKeys("0.01");

        WebElement fillDate = driver.findElement(By.xpath("//input[@id = 'date']"));
        ProjectUtils.click(driver, fillDate);

        WebElement fillTime = driver.findElement(By.xpath("//input[@id = 'datetime']"));
        ProjectUtils.click(driver, fillTime);

        WebElement buttonSaveClick = driver.findElement(By.xpath("//button[@class = 'btn btn-warning']"));
        ProjectUtils.click(driver, buttonSaveClick);

        Assert.assertEquals(driver.findElement(By.xpath("//tbody/tr/td[2]/a")).getText(),
                "Fulfillment");

        WebElement findFulfillmentAgain = driver.findElement(By.xpath("//tbody/tr/td[2]/a"));
        ProjectUtils.click(driver, findFulfillmentAgain);

        WebElement recheckFulfillment = driver.findElement(By.xpath("//a[@class = 'pa-chev-active']"));
        String ExpectedSign = "Fulfillment";
        Assert.assertEquals(ExpectedSign, recheckFulfillment.getText());
    }

    @Test(dependsOnMethods = "getFullSum")
    private void addRecord() {

        WebDriver driver = getDriver();

        goChevronPage(driver);

        ProjectUtils.click(driver, driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")));

        ProjectUtils.click(driver, driver.findElement(By.xpath("//button[@data-id='string']")));
        ProjectUtils.click(driver, driver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]", STATUS_NEW))));
        ProjectUtils.fill(getWait(), driver.findElement(By.id("text")), TITLE);
        ProjectUtils.fill(getWait(), driver.findElement(By.id("int")), INT_NUMBER);
        ProjectUtils.fill(getWait(), driver.findElement(By.id("decimal")), DOUBLE_NUMBER);
        ProjectUtils.click(driver, driver.findElement(By.id("pa-entity-form-save-btn")));

        WebElement row = findRow(driver);
        Assert.assertNotNull(row, "New record hasn't been found in the list");
    }

    // remove me !!!!!
    private WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), 10);
        }
        return wait;
    }

    @Test(dependsOnMethods = "addRecord")
    public void editStatus() {

        WebDriver driver = getDriver();

        goChevronPage(driver);

        WebElement row = findRow(driver);

        ProjectUtils.click(driver, row.findElement(By.xpath(".//a[contains(@href, 'action_edit')]")));

        ProjectUtils.click(driver, driver.findElement(By.xpath("//button[@data-id='string']")));
        // click on status pending
        ProjectUtils.click(driver, driver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]", STATUS_EDITED))));
        ProjectUtils.click(driver, driver.findElement(By.id("pa-entity-form-save-btn")));

        ProjectUtils.click(driver, driver.findElement(By.xpath(String.format("//div[contains(@class,'card-body')]/div/a[contains(text(), '%s')]", STATUS_EDITED))));

        WebElement rowEdited = findRow(driver);
        Assert.assertNotNull(rowEdited, "Edited title hasn't been found in the filtered list");

        ProjectUtils.click(driver, rowEdited.findElement(By.xpath(".//a[contains(@href, 'action_view')]")));
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='crumbs']//a[@class='pa-chev-active']")).getText(), STATUS_EDITED, "New status is not equal");
    }

    private WebElement findRow(WebDriver driver) {

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(TITLE)) {
                    return row;
                }
            }
        }
        Assert.fail("Title hasn't been found in result table.");
        return null;
    }

    private void goChevronPage(WebDriver driver) {
        ProjectUtils.click(driver, driver.findElement(By.xpath("//p[contains(text(),'Chevron')]")));
    }
}