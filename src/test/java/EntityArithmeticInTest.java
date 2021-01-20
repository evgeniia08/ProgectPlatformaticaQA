import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;
import java.util.List;

@Run(run = RunType.Multiple)
public class EntityArithmeticInTest extends BaseTest {

    private static final String NUM_1 = "8";
    private static final String NUM_2 = "2";
    private static final String ALPHABETIC_CHAR ="t";
    private static final String ERROR_MESSAGE ="error saving entity";

    @Test
    public void newIntRecord() {

        final String SUM = "10";
        final String SUB = "6";
        final String MUL = "16";
        final String DIV = "4";
        final List<String> expectedValues = Arrays.asList(NUM_1, NUM_2, SUM, SUB, MUL, DIV);

        WebDriver driver = getDriver();

        WebElement elementArithmetic = driver.findElement(By.xpath("//p[contains(text(), 'Arithmetic Inline')]"));
        ProjectUtils.click(driver, elementArithmetic);
        ProjectUtils.click(getWebDriverWait(), driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")));

        driver.findElement(By.xpath("//input[@id = 'f1']")).sendKeys(NUM_1);
        driver.findElement(By.xpath("//input[@id = 'f2']")).sendKeys(NUM_2);
        getWebDriverWait().until(d -> !driver.findElement(By.cssSelector("input#div")).getAttribute("value").equals(""));

        Assert.assertEquals(driver.findElement(By.cssSelector("input#sum")).getAttribute("value"), SUM);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#sub")).getAttribute("value"), SUB);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#mul")).getAttribute("value"), MUL);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#div")).getAttribute("value"), DIV);

        ProjectUtils.click(driver, driver.findElement(By.cssSelector("button[id*=save]")));

        List<WebElement> rows  = driver.findElements(By.cssSelector("tbody tr"));
        Assert.assertEquals(rows.size(), 1);
        for (int i = 0; i < expectedValues.size(); i++) {
            Assert.assertEquals(rows.get(0).findElement(By.xpath(String.format("//td[%d]", i + 2))).getText(), expectedValues.get(i));
        }

        rows.get(0).findElement(By.tagName("button")).click();
        ProjectUtils.click(driver, rows.get(0).findElement(By.cssSelector("ul a")));

        for (int i = 0; i < expectedValues.size(); i++) {
            String actualValue = driver.findElement(By.xpath(String.format("//div[@class='row']//div[%d]/div/span", i + 1))).getText();
            Assert.assertEquals(actualValue, expectedValues.get(i));
        }
    }

    @Test(dependsOnMethods = "newIntRecord")
    public void invalidF1Entry() {

        WebDriver driver = getDriver();

        WebElement elementArithmetic = driver.findElement(By.xpath("//p[contains(text(), 'Arithmetic Inline')]"));
        ProjectUtils.click(driver, elementArithmetic);

        driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")).click();
        driver.findElement(By.cssSelector("input#f1")).sendKeys(ALPHABETIC_CHAR);
        driver.findElement(By.cssSelector("input#f2")).sendKeys(NUM_2);
        driver.findElement(By.cssSelector("button[id*=save]")).click();

        WebElement error = driver.findElement(By.tagName("body"));
        Assert.assertEquals(error.getText(), ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "invalidF1Entry")
    public void invalidF2Entry() {

        WebDriver driver = getDriver();

        WebElement elementArithmetic = driver.findElement(By.xpath("//p[contains(text(), 'Arithmetic Inline')]"));
        ProjectUtils.click(driver, elementArithmetic);

        driver.findElement(By.xpath("//i[contains(text(), 'create_new_folder')]")).click();
        driver.findElement(By.cssSelector("input#f1")).sendKeys(NUM_1);
        driver.findElement(By.cssSelector("input#f2")).sendKeys(ALPHABETIC_CHAR);
        driver.findElement(By.cssSelector("button[id*=save]")).click();

        WebElement error = driver.findElement(By.tagName("body"));
        Assert.assertEquals(error.getText(), ERROR_MESSAGE);
    }

}