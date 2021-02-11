package test.entity;

import model.entity.common.MainPage;
import model.entity.table.ChevronPage;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import runner.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityChevronTest extends BaseTest {

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
                {"TEST2", "11", "0.11", Data, DataTime},
                {"TEST3", "11", "0.11", Data, DataTime},
                {"TEST4", "11", "0.11", Data, DataTime},
                {"TEST5", "11", "0.11", Data, DataTime}
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
}