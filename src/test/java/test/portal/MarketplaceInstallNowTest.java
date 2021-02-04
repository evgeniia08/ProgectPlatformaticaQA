package test.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class MarketplaceInstallNowTest extends BaseTest {

    private static final String NAME = "First purchase";

    @Test
    public void installNowTest() {

        WebDriver driver = getDriver();

        ProjectUtils.click(driver, driver.findElement(By.xpath("//p[contains (text(), 'Marketplace')]/preceding-sibling::i")));

        WebElement clickBuyCart = driver.findElement(By.xpath("//div[@class='card']/a"));
        ProjectUtils.click(driver, clickBuyCart);

        WebElement clickBuyNow = driver.findElement(By.xpath("//button[.='Buy now']"));
        ProjectUtils.click(driver, clickBuyNow);

        WebElement clickGet = driver.findElement(By.xpath("//button[text()='Get with credit']"));
        ProjectUtils.click(driver, clickGet);

        WebElement installNow = driver.findElement(By.xpath("//button[contains(text(), 'Now')]"));
        ProjectUtils.click(driver, installNow);

        WebElement inputName = driver.findElement(By.id("name"));
        inputName.sendKeys(NAME);

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveButton);

        String confirm = driver.findElement(By.xpath("//h3[contains(text(),'Congratulations! Your instance was successfully created')]")).getText();
        Assert.assertEquals(confirm, "Congratulations! Your instance was successfully created");
    }
}




