import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EntityExportTest extends BaseTest {
    public String exportString = "My String";
    public String exportText = "New text with 1234";
    public String exportInt = "1234";
    public String exportDec = "23.34";

    SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
    public String Data = data.format(new Date());

    SimpleDateFormat dataTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public String DataTime = dataTime.format(new Date());

    public String User = "User 1";
    public String tablString = "abc";
    public String tableTex = "abc123";
    public String tableInt = "124";
    public String tableDec = "34.56";

    private void createFieldForm(WebDriver driver){

        driver.findElement(By.xpath("//div[@id='menu-list-parent']//p[contains(text(), 'Export')]")).click();
        driver.findElement(By.xpath("//div/i")).click();

        driver.findElement(By.id("string")).click();
        driver.findElement(By.id("string")).clear();
        driver.findElement(By.id("string")).sendKeys(exportString);

        driver.findElement(By.id("text")).click();
        driver.findElement(By.id("text")).clear();
        driver.findElement(By.id("text")).sendKeys(exportText);

        driver.findElement(By.id("int")).click();
        driver.findElement(By.id("int")).clear();
        driver.findElement(By.id("int")).sendKeys(exportInt);

        driver.findElement(By.id("decimal")).click();
        driver.findElement(By.id("decimal")).clear();
        driver.findElement(By.id("decimal")).sendKeys(exportDec);

        driver.findElement(By.id("date")).click();
        driver.findElement(By.id("date")).clear();
        driver.findElement(By.id("date")).sendKeys(Data);

        driver.findElement(By.id("datetime")).click();
        driver.findElement(By.id("datetime")).clear();
        driver.findElement(By.id("datetime")).sendKeys(DataTime);

        driver.findElement(By.xpath("//div[@id='_field_container-user']/div/button/div/div/div")).click();
        Select selDr = new Select(driver.findElement(By.id("user")));
        selDr.selectByVisibleText(User);
    }

    private void createEmbedExp (WebDriver driver) {
        WebElement addRecord = driver.findElement(By.xpath("//tr[@id='add-row-23']/td/button"));
        ProjectUtils.click(driver, addRecord);
        driver.findElement(By.id("t-undefined-r-1-_line_number")).click();

        driver.findElement(By.id("t-23-r-1-string")).click();
        driver.findElement(By.id("t-23-r-1-string")).clear();
        driver.findElement(By.id("t-23-r-1-string")).sendKeys(tablString);

        driver.findElement(By.xpath("//tr[@id='row-23-1']/td[4]")).click();
        driver.findElement(By.id("t-23-r-1-text")).click();
        driver.findElement(By.id("t-23-r-1-text")).clear();
        driver.findElement(By.id("t-23-r-1-text")).sendKeys(tableTex);

        driver.findElement(By.id("t-23-r-1-int")).click();
        driver.findElement(By.id("t-23-r-1-int")).clear();
        driver.findElement(By.id("t-23-r-1-int")).sendKeys(tableInt);

        driver.findElement(By.id("t-23-r-1-decimal")).click();
        driver.findElement(By.id("t-23-r-1-decimal")).clear();
        driver.findElement(By.id("t-23-r-1-decimal")).sendKeys(tableDec);

        driver.findElement(By.id("t-23-r-1-date")).click();
        driver.findElement(By.id("t-23-r-1-date")).clear();
        driver.findElement(By.id("t-23-r-1-date")).sendKeys(Data);

        driver.findElement(By.id("t-23-r-1-datetime")).click();
        driver.findElement(By.id("t-23-r-1-datetime")).clear();
        driver.findElement(By.id("t-23-r-1-datetime")).sendKeys(DataTime);

        driver.findElement(By.id("t-23-r-1-user")).click();
        new Select(driver.findElement(By.id("t-23-r-1-user"))).selectByVisibleText(User);

        WebElement submit = driver.findElement(By.id("pa-entity-form-save-btn"));
        ProjectUtils.click(driver, submit);
    }


    @Test
    public void inputTest(){

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver,4);

        createFieldForm(driver);
        createEmbedExp(driver);

        int size = driver.findElements(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr")).size();

        List<WebElement> list = driver.findElements(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[7]"));

        int number =0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).getText().equals(DataTime)) {
                number = i;
            }
        }

        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[2]")).getText(),
                exportString);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[3]")).getText(),
                exportText);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[4]")).getText(),
                exportInt);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[5]")).getText(),
                exportDec);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[6]")).getText(),
                Data);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[7]")).getText(),
                DataTime);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number +1) + "]/td[9]")).getText(),
                User);

        driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr[" + (number + 1) + "]/td[2]/a/div")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[2]")).getText(), tablString);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[3]")).getText(), tableTex);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[4]")).getText(), tableInt);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[5]")).getText(), tableDec);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[6]")).getText(), Data);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[7]")).getText(), DataTime);
        Assert.assertEquals(driver.findElement(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[9]")).getText(), User);

        driver.findElement(By.xpath("//div[@id='menu-list-parent']//p[contains(text(), 'Export')]")).click();
    }


    @Test
    public void viewTest() {

        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver,4);

        WebElement exportButton = driver.findElement(By.xpath("//div[@id= 'menu-list-parent']/ul/li[8]/a"));
        ProjectUtils.click(driver, exportButton);

        WebElement createRecordButton = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createRecordButton.click();

        final String title = "TestV";
        final String text = "New Text";
        final int number = 10;
        final double number1 = 22.22;

        WebElement titleElement = driver.findElement(By.xpath("//input[@id= 'string']"));
        titleElement.sendKeys(title);
        WebElement textElement = driver.findElement(By.xpath("//textarea[@id= 'text']"));
        textElement.sendKeys(text);
        WebElement numberElement = driver.findElement(By.xpath("//input[@id= 'int']"));
        numberElement.sendKeys(String.valueOf(number));
        WebElement number1Element = driver.findElement(By.xpath("//input[@id= 'decimal']"));
        number1Element.sendKeys(String.valueOf(number1));

        WebElement dateElement = driver.findElement(By.xpath("//input[@id= 'date']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(dateElement).build().perform();
        dateElement.click();

        WebElement datetimeElement = driver.findElement(By.xpath("//input[@id= 'datetime']"));
        Actions actions1 = new Actions(driver);
        actions1.moveToElement(datetimeElement).build().perform();
        datetimeElement.click();

        WebElement saveButton = driver.findElement(By.xpath("//button[@value='1']"));
        ProjectUtils.click(driver,saveButton);
        WebElement actionsButton = driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[1]/button[1]"));
        actionsButton.click();
        WebElement someLabelButton = driver.findElement(By.xpath("//tbody/tr[last()]//a[contains(text(), 'Some label')]"));
        ProjectUtils.click(driver, someLabelButton);
        WebElement save1Button = driver.findElement(By.xpath("//button[@value='1']"));
        ProjectUtils.click(driver,save1Button);

        //verify Entity:View tickets
        WebElement exportDistButton = driver.findElement(By.xpath("//div[@id= 'menu-list-parent']/ul/li[9]/a"));
        ProjectUtils.click(driver, exportDistButton);
        WebElement verifyView = driver.findElement(By.xpath("//div[contains(text(),'TestV')]"));
        Assert.assertEquals(verifyView.getText(), "TestV");
    }
}



