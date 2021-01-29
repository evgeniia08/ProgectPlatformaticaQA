import model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private final String comments = "TEST";
    private final String int_ = "11";
    private final String decimal = "0.1";

    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    private String Data = data.format(new Date());

    SimpleDateFormat Time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private String DataTime = Time.format(new Date());

    private List<String> expectedResults = Arrays.asList("Fulfillment", "TEST", "11", "0.1", Data, DataTime);

    @Test
    public void createNewRecord() {
        ChevronPage chevronPage = new MainPage(getDriver())
                .clickMenuChevron()
                .clickNewFolder()
                .chooseRecordStatus()
                .sendKeys(comments, int_, decimal, DataTime, Data)
                .clickSaveButton();
        Assert.assertEquals(chevronPage.getRow(0), expectedResults);
    }

    @Test (dependsOnMethods = "createNewRecord")
    public void viewRecord() {
        ChevronPage page = new MainPage(getDriver())
                .clickMenuChevron()
                .clickViewButton()
                .getColumn();
    }

    @Test (dependsOnMethods = "viewRecord")
    public void deleteRecord() {
        ChevronPage chevronPage = new ChevronPage(getDriver());
        Assert.assertEquals(chevronPage
                .clickMenuChevron()
                .deleteRow()
                .getRowCount(), 0);

        Assert.assertEquals(chevronPage
                .clickRecycleBin()
                .getCellValue(0, 2), expectedResults.get(1));
    }

    @Test (dependsOnMethods = "deleteRecord")
    public void findChevron() {
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

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Fulfillment')]")).getText(),
                "Fulfillment");

        WebElement findFulfillmentAgain = driver.findElement(By.xpath("//td//div[contains(text(), 'Fulfillment')]"));
        ProjectUtils.click(driver, findFulfillmentAgain);

        WebElement recheckFulfillment = driver.findElement(By.xpath("//a[@class = 'pa-chev-active']"));
        String ExpectedSign = "Fulfillment";
        Assert.assertEquals(ExpectedSign, recheckFulfillment.getText());
    }
}