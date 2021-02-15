package test.entity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.text.SimpleDateFormat;
import java.util.*;

@Run(run = RunType.Multiple)
public class EntityGanttTest extends BaseTest {

    private static final By ACTIONS_BUTTON = By.xpath("//td/div/button");
    private static final By GANTT_MENU = By.xpath("//p[contains(text(),'Gantt')]");
    private static final By CREATE_FOLDER = By.xpath("//i[contains(text(),'create_new_folder')]");
    private static final By LIST_BUTTON = By.xpath("//a[contains(@href,'35')]/i[text()='list']");
    private static final By DRAFT_BUTTON = By.cssSelector("button[id*='draft']");
    private static final By SAVE_BUTTON = By.cssSelector("button[id*='save']");
    private static final By CANCEL_BUTTON = By.xpath("//button[text() = 'Cancel']");
    private static final By TABLE = By.xpath("//div[contains(@class,'card-body')]");
    private static final By RESET = By.xpath("//a[contains(text(), 'Reset')]");
    private String[] record_values = new String[6];

    private WebDriver clickMenuGantt() {
        WebDriver driver = getDriver();
        ProjectUtils.scroll(driver, driver.findElement(GANTT_MENU));
        driver.findElement(GANTT_MENU).click();
        return driver;
    }

    private int getRandomInteger() {
        Random r = new Random();
        return r.nextInt(Integer.MAX_VALUE);
    }

    private void createGanttChart(WebDriver driver, String mode) {
        if (mode.equals("cancel")) {
            driver.findElement(CANCEL_BUTTON).click();
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String time = dateFormat.format(new Date());

            record_values = new String[]
                    {ProjectUtils.createUUID(), ProjectUtils.createUUID(), String.valueOf(getRandomInteger()),
                            String.format("%.2f", getRandomInteger() * 0.01), "", "", "apptest1@tester.com"};

            List<WebElement> record_fields = driver.findElements
                    (By.xpath("//div[@class='card-body']//input[@type='text']|//textarea[@id='text']"));
            for (int i = 0; i < record_values.length - 1; i++) {
                if (record_fields.get(i).toString().toLowerCase().contains("date")) {
                    record_fields.get(i).click();
                } else {
                    if (!record_fields.get(i).getAttribute("value").isEmpty()) {
                        record_fields.get(i).clear();
                    }
                    record_fields.get(i).sendKeys(record_values[i]);
                }
            }
            new Select(driver.findElement(By.id("user"))).selectByVisibleText(record_values[6]);

            if (mode.equals("draft")) {
//                driver.findElement(DRAFT_BUTTON).click();
                ProjectUtils.scroll(driver, driver.findElement(DRAFT_BUTTON));
                ProjectUtils.click(driver, driver.findElement(DRAFT_BUTTON));
            } else if (mode.equals("save")) {
                ProjectUtils.scroll(driver, driver.findElement(SAVE_BUTTON));
                ProjectUtils.click(driver, driver.findElement(SAVE_BUTTON));
//                driver.findElement(SAVE_BUTTON).click();
            }
            record_values[4] = time;
            record_values[5] = time;
        }
    }

    private void actionsClick(WebDriver driver, String mode) {
        driver.findElement(ACTIONS_BUTTON).click();
        getWebDriverWait().until(TestUtils.movingIsFinished
                (By.xpath(String.format("//a[contains(text(),'%s')]", mode)))).click();
    }

    private void assertGanttRecord(WebDriver driver) {
        WebElement string_table_element = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//td[contains(@class,'e-rowcell')][2]")));
        Assert.assertEquals(string_table_element.getText(), record_values[0]);

        WebElement date_table_field = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//td[contains(@class,'e-rowcell')][3]")));
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String date = dateFormat.format(new Date());
        Assert.assertEquals(date_table_field.getText(), date);

        WebElement progress_element = driver.findElement
                (By.xpath("//tbody[@id='GanttContainerGanttTaskTableBody']//div[2]/div[3]"));
        Assert.assertNull(progress_element.getAttribute("data-tooltip-id"));
        Actions builder = new Actions(driver);
        builder.moveToElement(progress_element).perform();
        Assert.assertEquals(progress_element.getAttribute("data-tooltip-id"), "GanttContainer_0_content");
    }

    private void assertRecordView(WebDriver driver) {
        actionsClick(driver, "view");
        List<WebElement> actual_record = driver.findElements
                (By.xpath("//span[@class='pa-view-field']|//label[text()='User']/../p"));
        for (int i = 0; i < record_values.length; i++) {
            if (i == 5) {
                Assert.assertEquals(actual_record.get(i).getText().substring(0, 10), record_values[i]);
            } else {
                Assert.assertEquals(actual_record.get(i).getText(), record_values[i]);
            }
        }
    }

    @Test
    public void cancelGanttTest() {
        WebDriver driver = clickMenuGantt();

        driver.findElement(CREATE_FOLDER).click();
        createGanttChart(driver, "cancel");

        WebElement record_table = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//td[@colspan='3']")));
        Assert.assertEquals(record_table.getText(), "No records to display");
    }

    @Test (dependsOnMethods = "cancelGanttTest")
    public void draftGanttTest() {
        WebDriver driver = clickMenuGantt();

        driver.findElement(CREATE_FOLDER).click();
        createGanttChart(driver, "draft");
        assertGanttRecord(driver);

        driver.findElement(LIST_BUTTON).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td/i")).getAttribute("class"), AppConstant.DRAFT_ICON_CLASS);
        ProjectUtils.click(driver, driver.findElement(RESET));
    }

    @Test (dependsOnMethods = "draftGanttTest")
    public void createGanttTest() {
        WebDriver driver = clickMenuGantt();

        driver.findElement(CREATE_FOLDER).click();
        createGanttChart(driver, "save");
        assertGanttRecord(driver);
    }

    @Test (dependsOnMethods = "createGanttTest")
    public void viewGanttTest() {
        WebDriver driver = clickMenuGantt();

        driver.findElement(LIST_BUTTON).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td/i")).getAttribute("class"), AppConstant.RECORD_ICON_CLASS);
        assertRecordView(driver);
    }

    @Test (dependsOnMethods = "viewGanttTest")
    public void editGanttTest() {
        WebDriver driver = clickMenuGantt();

        driver.findElement(LIST_BUTTON).click();
        Assert.assertEquals(driver.findElements(By.xpath("//tbody/tr")).size(), 1);
        actionsClick(driver, "edit");
        createGanttChart(driver, "save");
        driver.findElement(LIST_BUTTON).click();
        assertRecordView(driver);
    }

    @Test(dependsOnMethods = "editGanttTest")
    public void deleteGanttTest() {
        WebDriver driver = clickMenuGantt();

        driver.findElement(LIST_BUTTON).click();
        actionsClick(driver, "delete");
        Assert.assertTrue(driver.findElement(TABLE).getText().isEmpty());

        driver.findElement(By.xpath("//i[text()='delete_outline']/..")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//tbody/tr")).size(), 1);
    }
}