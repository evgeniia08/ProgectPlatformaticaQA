package test.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import static runner.ProjectUtils.createUUID;

@Run(run = RunType.Multiple)
public class EntityChildTest extends BaseTest {

    private static final String STRING = UUID.randomUUID().toString();
    private static final String COMMENT = "Comments";
    private static final String INT_ = "150";
    private static final String DECIMAL = "22.88";
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private static final String DATE_TIME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    private static final String EDIT_TITLE = String.format("%s_EditTextAllNew", createUUID());
    private static final String EDIT_COMMENTS = "New comment";
    private static final String EDIT_INT = "7";
    private static final String EDIT_DECIMAL = "77.99";
    private static final String EDIT_DATE = "01/01/2021";
    private static final String EDIT_DATE_TIME = "01/01/2021 01:25:45";

    @Test
    public void createChild() throws InterruptedException {

        WebDriver driver = getDriver();

        WebElement parent = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        ProjectUtils.click(driver, parent);

        WebElement newParent = driver.findElement(By.xpath("//div[@class='card-icon']/i"));
        newParent.click();

        WebElement titleElement = driver.findElement(By.xpath("//input[contains(@name, 'string')]"));
        titleElement.sendKeys(STRING);

        WebElement commentElement = driver.findElement(By.id("text"));
        commentElement.sendKeys(COMMENT);

        WebElement numberElement = driver.findElement(By.xpath("//input[contains(@name, 'int')]"));
        numberElement.sendKeys(INT_);

        WebElement number1Element = driver.findElement(By.xpath("//input[contains(@name, 'decimal')]"));
        number1Element.sendKeys(DECIMAL);

        WebElement dateElement = driver.findElement(By.xpath("//input[contains(@name, 'date')]"));
        dateElement.sendKeys(DATE);

        driver.findElement(By.id("datetime")).click();
        driver.findElement(By.id("datetime")).clear();
        driver.findElement(By.id("datetime")).sendKeys(DATE_TIME);

        WebElement submit = driver.findElement(By.xpath("//button[text() = 'Save']"));
        ProjectUtils.click(driver, submit);

        WebElement parentAction = driver.findElement(By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='view']"));
        ProjectUtils.click(driver, parentAction);

        WebElement child = driver.findElement(By.xpath("//div[@class='card-icon']/i"));
        ProjectUtils.click(driver, child);

        WebElement titleChild = driver.findElement(By.xpath("//input[contains(@name, 'string')]"));
        titleChild.sendKeys(STRING);

        WebElement commentChild = driver.findElement(By.id("text"));
        commentChild.sendKeys(COMMENT);

        WebElement intChild = driver.findElement(By.xpath("//input[contains(@name, 'int')]"));
        intChild.sendKeys(INT_);

        WebElement decimalChild = driver.findElement(By.xpath("//input[contains(@name, 'decimal')]"));
        decimalChild.sendKeys(DECIMAL);

        WebElement dateChild = driver.findElement(By.xpath("//input[contains(@name, 'date')]"));
        dateChild.sendKeys(DATE);

        driver.findElement(By.id("datetime")).click();
        driver.findElement(By.id("datetime")).clear();
        driver.findElement(By.id("datetime")).sendKeys(DATE_TIME);

        WebElement submitChild = driver.findElement(By.xpath("//button[text() = 'Save']"));
        ProjectUtils.click(driver, submitChild);

        WebElement resultTitle = driver.findElement(By.xpath("//div[contains(text(),'" + STRING + "')]"));
        WebElement resultComments = driver.findElement(By.xpath("//div[contains(text(),'" + COMMENT + "')]"));
        WebElement resultInt = driver.findElement(By.xpath("//div[contains(text(),'" + INT_ + "')]"));
        WebElement resultDecimal = driver.findElement(By.xpath("//div[contains(text(),'" + DECIMAL + "')]"));

        Assert.assertEquals(resultTitle.getText(), STRING);
        Assert.assertEquals(resultComments.getText(), COMMENT);
        Assert.assertEquals(resultInt.getText(), INT_);
        Assert.assertEquals(resultDecimal.getText(), DECIMAL);
    }

    @Test(dependsOnMethods = {"createChild"})
    public void editChild() {

        WebDriver driver = getDriver();

        WebElement parent = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        ProjectUtils.click(driver, parent);

        WebElement parentAction = driver.findElement(By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='view']"));
        ProjectUtils.click(driver, parentAction);

        WebElement childAction = driver.findElement(By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='edit']"));
        ProjectUtils.click(driver, childAction);

        WebElement titleChild = driver.findElement(By.xpath("//input[contains(@name, 'string')]"));
        titleChild.clear();
        titleChild.sendKeys(EDIT_TITLE);

        WebElement commentChild = driver.findElement(By.xpath("//input[contains(@name, 'string')]"));
        commentChild.clear();
        commentChild.sendKeys(EDIT_COMMENTS);

        WebElement intChild = driver.findElement(By.xpath("//input[contains(@name, 'int')]"));
        intChild.clear();
        intChild.sendKeys(EDIT_INT);

        WebElement decimalChild = driver.findElement(By.xpath("//input[contains(@name, 'decimal')]"));
        decimalChild.clear();
        decimalChild.sendKeys(EDIT_DECIMAL);

        WebElement dateChild = driver.findElement(By.xpath("//input[contains(@name, 'date')]"));
        dateChild.clear();
        dateChild.sendKeys(EDIT_DATE);

        driver.findElement(By.id("datetime")).click();
        driver.findElement(By.id("datetime")).clear();
        driver.findElement(By.id("datetime")).sendKeys(EDIT_DATE_TIME);

        WebElement submitChild = driver.findElement(By.xpath("//button[text() = 'Save']"));
        ProjectUtils.click(driver, submitChild);

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), '" + EDIT_INT + "')]")).isDisplayed());
    }

    @Test(dependsOnMethods = {"editChild"})
    public void deleteChild() {

        WebDriver driver = getDriver();

        WebElement parent = driver.findElement(By.xpath("//p[contains(text(),'Parent')]"));
        ProjectUtils.click(driver, parent);

        WebElement parentAction = driver.findElement(By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='view']"));
        ProjectUtils.click(driver, parentAction);

        WebElement childAction = driver.findElement(By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='delete']"));
        ProjectUtils.click(driver, childAction);

        WebElement RecycleBin = driver.findElement(By.xpath("//i[contains(text(),'delete_outline')]"));
        RecycleBin.click();
    }
}

