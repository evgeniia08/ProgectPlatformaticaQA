import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;
import java.util.UUID;

public class EntityAssignRecordTest extends BaseTest {

    @Test
    public void newRecord() throws InterruptedException {
        WebDriver driver = getDriver();
        String url = "https://ref.eteam.work";
        String user = "user116@tester.com";
        driver.get(url);
        ProjectUtils.login(driver, user, "Dfp3BMkaLi");

        WebElement assignBtn = driver.findElement(By.xpath("//*[@id='menu-list-parent']/ul/li/a[contains(@href,'id=37')]"));
        assignBtn.click();

        WebElement nr = driver.findElement(By.xpath("//*[@class='card-header card-header-rose card-header-icon']/a[contains(@href, 'entity_id=37')]/div"));
        nr.click();

        String testId = UUID.randomUUID().toString();
        WebElement stringField = driver.findElement(By.xpath("//*[@id='string']"));
        stringField.sendKeys(testId);

        WebElement textField = driver.findElement(By.xpath("//*[@id='text']"));
        textField.sendKeys("tratatyshechki");

        WebElement intField = driver.findElement(By.xpath("//*[@id='int']"));
        intField.sendKeys("23");

        WebElement decimalField = driver.findElement(By.xpath("//*[@id='decimal']"));
        decimalField.sendKeys("19.99");

        WebElement dateField = driver.findElement(By.xpath("//*[@id='date']"));
        dateField.click();

        WebElement datetimeField = driver.findElement(By.xpath("//*[@id='datetime']"));
        datetimeField.click();

        WebElement user1Field = driver.findElement(By.xpath(String.format("//option[text()='%s']", user)));
        user1Field.click();

        WebElement saveBtn = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveBtn.click();

        String template = String.format("//tbody/tr/td/a/div[contains(text(), '%s')]//following::td/select/option[text()='%s']", testId, user);
        System.out.println(template);
        List<WebElement> rows = driver.findElements(By.xpath(template));
        Assert.assertEquals(rows.size(), 1);
        for (WebElement row : rows) {
            row.click();
        }

        WebElement myAssignments = driver.findElement(By.xpath("//*[@id='pa-menu-item-41']/a/p"));
        myAssignments.click();
        template = String.format("//tbody/tr[descendant::div[contains(text(), '%s')] and descendant::td[text()='%s']]", testId, user);
        System.out.println(template);
        rows = driver.findElements(By.xpath(template));
        Assert.assertEquals(rows.size(), 1);
    }
}
