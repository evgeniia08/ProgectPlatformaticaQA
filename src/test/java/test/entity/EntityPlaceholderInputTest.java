package test.entity;

import model.entity.common.MainPage;
import model.entity.table.Placeholder1Page;
import model.entity.edit.PlaceholderEdit1Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.UUID;

public class EntityPlaceholderInputTest extends BaseTest {

    @Test
    public void newRecord() {

        final String title = UUID.randomUUID().toString();

        WebDriver driver = getDriver();

        WebElement tab = driver.findElement(By.xpath("//p[contains(text(),'Placeholder')]"));
        tab.click();

        WebElement createNewFolder = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFolder.click();

        WebElement titleElement = driver.findElement(By.xpath("//input[@name='entity_form_data[string]']"));
        titleElement.sendKeys(title);

        WebElement textPlaceholder = driver.findElement(By.xpath("//textarea[@name='entity_form_data[text]']"));
        textPlaceholder.sendKeys("test ");

        WebElement fieldInt = driver.findElement(By.xpath("//input[@name='entity_form_data[int]']"));
        fieldInt.sendKeys(String.valueOf(55));

        WebElement fieldDecimal = driver.findElement(By.xpath("//input[@name='entity_form_data[decimal]']"));
        fieldDecimal.sendKeys(String.valueOf(0));

        WebElement fieldData = driver.findElement(By.id("date"));
        fieldData.click();

        WebElement saveBtn = driver.findElement(By.id("pa-entity-form-save-btn"));
        ProjectUtils.click(driver,saveBtn);

        WebElement orderBtn = driver.findElement(By.xpath("//i[contains(text(),'format_line_spacing')]"));
        orderBtn.click();

        driver.findElement(By.xpath("//div[contains(text(),'" + title + "')]"));
    }


    @Test
    public void newRecordPV() {

        WebDriver driver = getDriver();

        WebElement placeholder = driver.findElement(By.xpath("//p[text()=' Placeholder ']"));
        ProjectUtils.click(driver, placeholder);

        WebElement newRec = driver.findElement(By.xpath("//i[text()='create_new_folder']"));
        ProjectUtils.click(driver, newRec);

        WebElement stringValue = driver.findElement(By.xpath("//input[@name='entity_form_data[string]']"));
        String string_ph = stringValue.getAttribute("placeholder");
        ProjectUtils.fill(getWebDriverWait(), stringValue, string_ph);

        WebElement textValue = driver.findElement(By.xpath("//textarea[@name='entity_form_data[text]']"));
        String text_ph = textValue.getAttribute("placeholder");
        ProjectUtils.fill(getWebDriverWait(), textValue, text_ph);

        WebElement intValue = driver.findElement(By.xpath("//input[@name='entity_form_data[int]']"));
        String int_ph = intValue.getAttribute("placeholder");
        ProjectUtils.fill(getWebDriverWait(), intValue, int_ph);

        WebElement decimalValue = driver.findElement(By.xpath("//input[@name='entity_form_data[decimal]']"));
        String decimal_ph = decimalValue.getAttribute("placeholder");
        ProjectUtils.fill(getWebDriverWait(), decimalValue, decimal_ph);

        WebElement saveButton = driver.findElement(By.xpath("//div/button[@id='pa-entity-form-save-btn']"));
        ProjectUtils.click(driver, saveButton);

        Assert.assertEquals(driver.findElement(By.xpath("//tr/td[2]/a/div")).getText(), string_ph);
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td[3]/a/div")).getText(), text_ph);
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td[4]/a/div")).getText(), int_ph);
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td[5]/a/div")).getText(), decimal_ph);

        WebElement actions = driver.findElement(By.xpath("//tr[@data-index='0']//button/i[text()='menu']"));
        ProjectUtils.click(driver, actions);
        WebElement delete = driver.findElement(By.xpath("//tr[@data-index='0']//div//li/a[text()='delete']"));
        ProjectUtils.click(driver, delete);
    }

    @Test
    public void PomNewRecordPV(){

        WebDriver driver = getDriver();

        MainPage mainPage = new MainPage(driver);
        PlaceholderEdit1Page placeholderEdit1Page = mainPage.clickMenuPlaceholder1().clickNewFolder();

        //Get an array of default placeholder values
        String[] arrayOfDefaultValues = {placeholderEdit1Page.getStrValue(), placeholderEdit1Page.getTxtValue(),
                                         placeholderEdit1Page.getIntValue(), placeholderEdit1Page.getDecValue()};

        Placeholder1Page placeholder1Page = placeholderEdit1Page.fillFields().clickSaveButton();

        for (int i = 0; i < arrayOfDefaultValues.length; i++) {
            Assert.assertEquals(placeholder1Page.newRecordElements().get(i).getText(), arrayOfDefaultValues[i]);
        }

        placeholder1Page.deleteRow();
    }
}
