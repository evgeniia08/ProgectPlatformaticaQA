package test.portal;

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

import java.util.List;
import java.util.UUID;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class PortalPrivateTemplateCreationAndUsageTest extends BaseTest {

    private static final String NEW_APP_NAME = UUID.randomUUID().toString().substring(0, 10);
    private static final String NEW_APP_NAME2 = UUID.randomUUID().toString().substring(0, 10);
    private String WORK_URL;
    private String WORK_URL2;
    private static final String WORK_LOGIN_NAME = "admin";
    private String WORK_PASSWORD;
    private String WORK_PASSWORD2;
    private static final String ERROR_MESSAGE = "Unable to create instance";
    private static final String ENTITY_NAME = "Project";
    private static final String ENTITY_FIELD_STRING = "Label";
    private static final String ENTITY_FIELD_TEXT = "Body";

    private static final By CREATE_NEW_FOLDER = By.xpath("//i[text() = 'create_new_folder']");
    private static final By TEMPLATES = By.xpath("//p[contains(text(), 'Templates')]");
    private static final By INSTANCE_INPUT_NAME = By.id("name");
    private static final By SAVE_BUTTON = By.id("pa-entity-form-save-btn");
    private static final By PASSWORD_LOCATOR = By.xpath("//h4[contains(text(), 'Password')]/b");
    private static final By INPUT_LOGIN = By.xpath("//input[@name='login_name']");
    private static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");
    private static final By SIGN_IN_BUTTON = By.xpath("//button[@type='submit']");
    private static final By ERROR = By.tagName("body");
    private static final By PROFILE_DROPDOWN_MENU = By.xpath("//a[@id='navbarDropdownProfile']");
    private static final By CONFIGURATION = By.xpath("//a[contains(text(), 'Conf')]");
    private static final By ENTITY = By.xpath("//li//p[contains(text(), 'Entities')]");
    private static final By ENTITY_NEW = By.xpath("//span[contains(text(), 'New')]");
    private static final By ENTITY_NAME_INPUT = By.id("pa-adm-new-entity-label");
    private static final By ENTITY_CREATE_BUTTON = By.id("pa-adm-create-entity-btn");
    private static final By HOME = By.xpath("//a[@class = 'simple-text logo-normal']");
    private static final By PROJECT_ENTITY = By.xpath("//p[contains(text(), 'Project')]/..");
    private static final By ROW_MENU_SAVE_AS_TEMPLATE = By.xpath("//a[text() = 'Save as template']");
    private static final By TABLE = By.xpath("//table[@id='pa-all-entities-table']/tbody/tr");
    private static final By INSTALL_BUTTON = By.xpath("//button[contains(text(), 'Install')]");

    private void loginToNewWebApp(WebDriver driver, String url, String password) {

        driver.get(url);

        driver.findElement(INPUT_LOGIN).sendKeys(WORK_LOGIN_NAME);
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
        driver.findElement(SIGN_IN_BUTTON).click();
    }

    private void instanceCreate(WebDriver driver, String name) {

        ProjectUtils.click(driver, driver.findElement((CREATE_NEW_FOLDER)));
        driver.findElement(INSTANCE_INPUT_NAME).sendKeys(name);
        driver.findElement(SAVE_BUTTON).click();
    }

    private void entityCreate(WebDriver driver) {

        driver.findElement(PROFILE_DROPDOWN_MENU).click();
        getWebDriverWait().until((ExpectedConditions.visibilityOfElementLocated(CONFIGURATION))).click();
        driver.findElement(ENTITY).click();
        driver.findElement(ENTITY_NEW).click();

        driver.findElement(ENTITY_NAME_INPUT).sendKeys(ENTITY_NAME);
        driver.findElement(ENTITY_CREATE_BUTTON).click();

        createFields(driver, ENTITY_FIELD_STRING, "string");
        createFields(driver, ENTITY_FIELD_TEXT, "text");

        driver.findElement(By.className("visible-on-sidebar-mini")).click();
        driver.findElement(HOME).click();
    }

    private void createFields(WebDriver driver, String label, String typeOfLabel) {

        driver.findElement(CREATE_NEW_FOLDER).click();

        driver.findElement(By.id("pa-adm-new-fields-label")).sendKeys(label);

        driver.findElement(By.className("filter-option")).click();
        getWebDriverWait().until(TestUtils.movingIsFinished(
                By.xpath(String.format("//span[text() = '%s']/..", typeOfLabel.toLowerCase())))).click();
        driver.findElement(By.id("pa-adm-create-fields-btn")).click();

        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(CREATE_NEW_FOLDER));
    }

    private void saveAsTemplate(WebDriver driver) {

        List<WebElement> trs = driver.findElements(TABLE);

        trs.get(0).findElement(By.xpath("//td//div//button")).click();
        getWebDriverWait().until(TestUtils.movingIsFinished(ROW_MENU_SAVE_AS_TEMPLATE)).click();

        driver.findElement(By.id("author")).sendKeys("Burdishka");
        driver.findElement(By.id("description")).sendKeys("Description");
        driver.findElement(SAVE_BUTTON).click();
    }

    @Test
    public void instanceCreateTest() {

        WebDriver driver = getDriver();

        instanceCreate(driver, NEW_APP_NAME);

        List<WebElement> trs = driver.findElements(TABLE);

        //validation
        WORK_URL = trs.get(0).findElements(By.tagName("td")).get(4).getText();
        WORK_PASSWORD = driver.findElement(PASSWORD_LOCATOR).getText();
    }

    @Test(dependsOnMethods = "instanceCreateTest")
    public void entityCreateTest() {

        WebDriver driver = getDriver();

        loginToNewWebApp(driver, WORK_URL, WORK_PASSWORD);

        entityCreate(driver);

        driver.findElement(PROJECT_ENTITY).click();
        driver.findElement(CREATE_NEW_FOLDER).click();

        WebElement inputField1 =
                driver.findElement(By.id(String.format("_field_container-%s", ENTITY_FIELD_STRING.toLowerCase())));
        Assert.assertEquals(inputField1.getText(), ENTITY_FIELD_STRING);

        WebElement inputField2 =
                driver.findElement(By.id(String.format("_field_container-%s", ENTITY_FIELD_TEXT.toLowerCase())));
        Assert.assertEquals(inputField2.getText(), ENTITY_FIELD_TEXT);
    }

    @Test(dependsOnMethods = "entityCreateTest")
    public void saveAsTemplateTest() {

        WebDriver driver = getDriver();

        saveAsTemplate(driver);

        driver.findElement(TEMPLATES).click();

        List<WebElement> trs = driver.findElements(TABLE);

        Assert.assertEquals(trs.size(), 1);
        Assert.assertEquals(driver.findElement(
                By.xpath("//table[@id='pa-all-entities-table']/tbody/tr/td[2]")).getText(), NEW_APP_NAME);
    }

    @Test(dependsOnMethods = "saveAsTemplateTest")
    public void installFromTemplateTest() {

        WebDriver driver = getDriver();

        driver.findElement(TEMPLATES).click();
        driver.findElement(INSTALL_BUTTON).click();
        driver.findElement(By.id("name")).sendKeys(NEW_APP_NAME2);
        driver.findElement(SAVE_BUTTON).click();

        List<WebElement> trs = driver.findElements(TABLE);

        //validation
        WORK_URL2 = trs.get(1).findElements(By.tagName("td")).get(4).getText();
        WORK_PASSWORD2 = driver.findElement(PASSWORD_LOCATOR).getText();


        loginToNewWebApp(driver, WORK_URL2, WORK_PASSWORD2);

        //validation
        driver.findElement(PROJECT_ENTITY).click();
    }

    @Test(dependsOnMethods = "installFromTemplateTest")
    public void instanceCreateSameNameNegativeTest() {

        WebDriver driver = getDriver();

        instanceCreate(driver, NEW_APP_NAME);

        Assert.assertEquals(driver.findElement(ERROR).getText(), ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "instanceCreateSameNameNegativeTest")
    public void instanceCreateCyrillicAlphabetNegativeTest() {

        WebDriver driver = getDriver();

        instanceCreate(driver, "абв");

        Assert.assertEquals(driver.findElement(ERROR).getText(), ERROR_MESSAGE);
    }
}