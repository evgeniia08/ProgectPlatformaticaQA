package test.portal;

import model.portal.table.InstancePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.TestUtils;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class MarketplaceInstanceTest extends BaseTest {

    private static final By CREATE_FOLDER = By.xpath("//i[contains(text(),'create_new_folder')]");
    private static final By ACTIONS_BUTTON = By.xpath("//td/div/button");
    private static final By DRAFT_BUTTON = By.xpath("//button[@id='pa-entity-form-draft-btn']");
    private static final By SAVE_BUTTON = By.xpath("//button[@id='pa-entity-form-save-btn']");
    private static final By CANCEL_BUTTON = By.xpath("//button[contains(text(),'Cancel')]");
    private static final By TABLE = By.xpath("//div[contains(@class,'card-body')]");
    private static final By RESET = By.xpath("//a[contains(text(), 'Reset')]");
    private static final By CONGRATS_TEXT = By.xpath("//div[@class='card-body ']/child::div/child::h3[1]");
    private static final String PRIMARY_LANGUAGE = "English";
    private static final List<String> ascNames = Arrays.asList("aaaa", "bbbb");
    private String[] app_values = new String[7];

    private Boolean isUnableCreateApp() {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//body"))).getText().equalsIgnoreCase(AppConstant.PORTAL_ERROR_MESSAGE);
    }

    private String[] getInstanceValues() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10).toLowerCase();
        return new String[] {name, name, String.format("https://%s.eteam.work", name), "admin", "2", "1", PRIMARY_LANGUAGE};
    }

    private String[] createInstance(WebDriver driver) {
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(CREATE_FOLDER)).click();
        String[] instance_values;
        do {
            instance_values = getInstanceValues();
            if (isUnableCreateApp()) {
                driver.navigate().back();
            }
            WebElement app_name = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//input[@id='name']")));
            ProjectUtils.inputKeys(driver, app_name, instance_values[0]);
            getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON)).click();
        } while (isUnableCreateApp());
        return instance_values;
    }

    private void actionsClick(WebDriver driver, String mode) {
        driver.findElement(ACTIONS_BUTTON).click();
        getWebDriverWait().until(TestUtils.movingIsFinished
                (By.xpath(String.format("//a[contains(text(),'%s')]", mode)))).click();
    }

    private String[] createTemplate(WebDriver driver, String mode) {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = dateFormatGmt.format(new Date());
        String[] template_values =
                {ProjectUtils.createUUID(), ProjectUtils.createUUID(), time, ProjectUtils.createUUID(), "Own", "0.00"};

        List<WebElement> template_edit_fields = driver.findElements
                (By.xpath("//input[@id='title']|//input[@id='author']"));
        for (int i = 0; i < 2; i++) {
            template_edit_fields.get(i).clear();
            template_edit_fields.get(i).sendKeys(template_values[i]);
        }
        driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys(template_values[3]);

        if (mode.equals("draft")) {
            driver.findElement(DRAFT_BUTTON).click();
        } else if (mode.equals("save")) {
            driver.findElement(SAVE_BUTTON).click();
        } else {
            driver.findElement(CANCEL_BUTTON).click();
            driver.findElement(By.xpath("//p[contains(text(),'Templates')]")).click();
            return null;
        }
        driver.findElement(By.xpath("//p[contains(text(),'Templates')]")).click();
        return template_values;
    }

    private void assertInstanceValues (String[] instance_values) {
        if (instance_values != null) {
            List<WebElement> actual_instance_record = getDriver().findElements(By.xpath("//tbody/tr/td/a"));
            for (int i = 0; i < instance_values.length; i++){
                String actual_value = String.valueOf(actual_instance_record.get(i).getText());
                Assert.assertEquals(actual_value, instance_values[i]);
            }
        }
    }

    @Test
    public void instanceCancelTest() {
        WebDriver driver = getDriver();
        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(CREATE_FOLDER)).click();
        WebElement app_name = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='name']")));
        ProjectUtils.inputKeys(driver, app_name, getInstanceValues()[0]);
        driver.findElement(CANCEL_BUTTON).click();

        WebElement record_table = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(TABLE));
        Assert.assertTrue(record_table.getText().isEmpty());
    }

    @Test (dependsOnMethods = "instanceCancelTest")
    public void instanceDraftTest() {
        WebDriver driver = getDriver();

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(CREATE_FOLDER)).click();
        WebElement app_name = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='name']")));
        app_values = getInstanceValues();
        ProjectUtils.inputKeys(driver, app_name, app_values[0]);
        driver.findElement(DRAFT_BUTTON).click();

        assertInstanceValues(app_values);
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td/i")).getAttribute("class"), AppConstant.DRAFT_ICON_CLASS);
        ProjectUtils.click(driver, driver.findElement(RESET));
    }

    @Test (dependsOnMethods = "instanceDraftTest")
    public void instanceUniquenessTest() {
        WebDriver driver = getDriver();

        app_values = createInstance(driver);

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(CREATE_FOLDER)).click();
        WebElement subdomain = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='subdomain']")));
        ProjectUtils.inputKeys(driver, subdomain, app_values[0]);
        String field_note = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//label[@id='_field-note-subdomain']"))).getText();

        Assert.assertEquals(field_note, AppConstant.INSTANCE_UNIQUENESS_ERROR);
        ProjectUtils.click(driver, driver.findElement(RESET));
    }

    @Test (dependsOnMethods = "instanceUniquenessTest")
    public void instancePasswordTest() {
        WebDriver driver = getDriver();

        createInstance(driver);
        Assert.assertEquals(driver.findElement(CONGRATS_TEXT).getText(), AppConstant.INSTANCE_CREATED_TEXT);

        Assert.assertFalse(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(@class,'card-body')]//h4[2]/b"))).getText().isEmpty());
        driver.navigate().refresh();
        Assert.assertEquals(getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[contains(@class,'card-body')]//h4[2]/b"))).getText(), "[[notfound]]");
        ProjectUtils.click(driver, driver.findElement(RESET));
    }

    @Test(dependsOnMethods = {"instanceUniquenessTest", "instancePasswordTest"})
    public void instanceCreateTest() {
        WebDriver driver = getDriver();

        app_values = createInstance(driver);
        assertInstanceValues(app_values);
        Assert.assertEquals(driver.findElement(CONGRATS_TEXT).getText(), AppConstant.INSTANCE_CREATED_TEXT);
    }

    @Test (dependsOnMethods = "instanceCreateTest")
    public void instanceViewTest() {
        WebDriver driver = getDriver();

        actionsClick(driver,"view");
        List<WebElement> instance_elements = driver.findElements
                (By.xpath("//div[contains(@class,'card-body')]//span"));
        for (int i = 0; i < 4; i++) {
            Assert.assertEquals(instance_elements.get(i).getText(), app_values[i]);
        }
    }

    @Test (dependsOnMethods = "instanceViewTest")
    public void instanceTemplateCancelTest() {
        WebDriver driver = getDriver();

        actionsClick(driver, "Save as template");
        assertInstanceValues(createTemplate(driver, "cancel"));
        WebElement record_table = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(TABLE));
        Assert.assertTrue(record_table.getText().isEmpty());
    }

    @Test (dependsOnMethods = "instanceTemplateCancelTest")
    public void instanceTemplateDraftTest() {
        WebDriver driver = getDriver();

        actionsClick(driver,"Save as template");
        assertInstanceValues(createTemplate(driver, "draft"));
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td/i")).getAttribute("class"), AppConstant.DRAFT_ICON_CLASS);
        actionsClick(driver,"delete");
        WebElement record_table = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(TABLE));
        Assert.assertTrue(record_table.getText().isEmpty());
    }

    @Test (dependsOnMethods = "instanceTemplateDraftTest")
    public void instanceTemplateSaveTest() {
        WebDriver driver = getDriver();

        actionsClick(driver,"Save as template");
        assertInstanceValues(createTemplate(driver, "save"));
        Assert.assertEquals(driver.findElement(By.xpath("//tr/td/i")).getAttribute("class"), AppConstant.RECORD_ICON_CLASS);
        actionsClick(driver,"delete");
        WebElement record_table = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(TABLE));
        Assert.assertTrue(record_table.getText().isEmpty());
    }

    @Test (dependsOnMethods = "instanceTemplateSaveTest")
    public void instanceDeleteTest() {
        WebDriver driver = getDriver();

        actionsClick(driver,"delete");
        WebElement record_table = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(TABLE));
        Assert.assertTrue(record_table.getText().isEmpty());
    }

    @Test (dependsOnMethods = "instanceDeleteTest")
    public void ascOrder() {
        InstancePage instancePage = new InstancePage(getDriver())
                        .clickNewFolder()
                        .fillOutInstanceForm("bbbb", "bbbb", PRIMARY_LANGUAGE)
                        .clickSaveButton()
                        .clickNewFolder()
                        .fillOutInstanceForm("aaaa", "aaaa", PRIMARY_LANGUAGE)
                        .clickSaveButton()
                        .clickColumnHeader("Name");

        Assert.assertEquals(instancePage.getNames(), ascNames);
    }
}