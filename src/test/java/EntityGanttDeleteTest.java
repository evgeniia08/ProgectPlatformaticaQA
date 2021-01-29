import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;
import runner.type.Run;
import runner.type.RunType;

import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityGanttDeleteTest extends BaseTest {

    private static final String STRING = UUID.randomUUID().toString();

    @Test
    public void recordCreateTest() {

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//p[contains (text(), 'Gantt')]")).click();
        driver.findElement(By.xpath("//i[text() = 'create_new_folder']")).click();
        driver.findElement(By.xpath("//input[@data-field_name='string']")).sendKeys(STRING);
        driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']")).click();
        driver.findElement(By.xpath("//a[@class='nav-link ']/i")).click();

        Assert.assertEquals(driver.findElements(By.xpath("//tbody/tr")).size(), 1);
    }

    @Test(dependsOnMethods = "recordCreateTest")
    public void recordDeleteTest() {

        WebDriver driver = getDriver();

        driver.findElement(By.xpath("//p[contains (text(), 'Gantt')]")).click();
        driver.findElement(By.xpath("//a[@class='nav-link ']/i")).click();
        driver.findElement(By.xpath("//button/i[@class='material-icons']")).click();
        getWebDriverWait().until(TestUtils.movingIsFinished(By.xpath("//a[text()='delete']"))).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'card-body')]")).getText(), "");

        driver.findElement(By.xpath("//i[text() = 'delete_outline']/..")).click();

        Assert.assertEquals(driver.findElements(By.xpath("//tbody/tr")).size(), 1);
    }

}