package test.portal;

import model.base.LoginPage;
import model.base.WorkHomePage;
import model.portal.table.InstancePage;
import model.portal.table.TemplatePage;
import model.work.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class PortalPrivateTemplateCreationAndUsageTest extends BaseTest {

    private static final String NEW_APP_NAME = UUID.randomUUID().toString().substring(0, 10);
    private static final String NEW_APP_NAME2 = UUID.randomUUID().toString().substring(0, 10);
    private String WORK_URL;
    private String WORK_USER_NAME;
    private String WORK_PASSWORD;
    private static final String ERROR_MESSAGE = "Unable to create instance";
    private static final String ENTITY_NAME = "Project";
    private static final String ENTITY_FIELD_LABEL = "Label";
    private static final String ENTITY_FIELD_BODY = "Body";
    private static final String AUTHOR = "Burdishka";
    private static final String DESCRIPTION = "Description";

    private static final By CREATE_NEW_FOLDER = By.xpath("//i[text() = 'create_new_folder']");
    private static final By INSTANCE_INPUT_NAME = By.id("name");
    private static final By SAVE_BUTTON = By.id("pa-entity-form-save-btn");
    private static final By ERROR = By.tagName("body");

    private static final List<String> EDIT_LABELS = Arrays.asList(ENTITY_FIELD_LABEL, ENTITY_FIELD_BODY);

    private void instanceCreate(WebDriver driver, String name) {

        ProjectUtils.click(driver, driver.findElement((CREATE_NEW_FOLDER)));
        driver.findElement(INSTANCE_INPUT_NAME).sendKeys(name);
        driver.findElement(SAVE_BUTTON).click();
    }

    @Test
    public void instanceCreateTest() {

        InstancePage instancePage = new InstancePage(getDriver())
                .clickNewFolder()
                .inputName(NEW_APP_NAME)
                .clickSaveButton();

        //validation
        WORK_URL = instancePage.getUrl(0);
        WORK_PASSWORD = instancePage.getPassword();
        WORK_USER_NAME = instancePage.getUsername();
    }

    @Test(dependsOnMethods = "instanceCreateTest")
    public void entityCreateTest() {

        LoginPage loginPage = new LoginPage(getDriver())
                .goToLoginPage(WORK_URL);

        loginPage.login(WORK_USER_NAME, WORK_PASSWORD);

        ConfigurationPage configurationPage = new ConfigurationPage(getDriver())
                .clickConfiguration()
                .clickNewEntity();

        WorkHomePage workHomePage = new CreateEntityPage(getDriver())
                .createEntity(ENTITY_NAME)
                .createFieldString(ENTITY_FIELD_LABEL)
                .createFieldText(ENTITY_FIELD_BODY)
                .goHomePage();

        EntityEditPage entityEditPage = new EntityEditPage(getDriver())
                .clickProject()
                .clickCreateNewFolder();

        Assert.assertEquals(entityEditPage.getInputValues(), EDIT_LABELS);
    }

    @Test(dependsOnMethods = "entityCreateTest")
    public void saveAsTemplateTest() {

        TemplatePage templatePage = new InstancePage(getDriver())
                .saveAsTemplate(0)
                .fillOutInputFields(AUTHOR, DESCRIPTION)
                .saveAsTemplateClickSaveButton()
                .clickMenuTemplates();

        Assert.assertEquals(templatePage.getRowCount(), 1);
        Assert.assertEquals(templatePage.getRowData(0).get(0), NEW_APP_NAME);
        Assert.assertEquals(templatePage.getRowData(0).get(1), AUTHOR);
    }

    @Test(dependsOnMethods = "saveAsTemplateTest")
    public void installFromTemplateTest() {

        InstancePage instancePage = new InstancePage(getDriver())
                .clickMenuTemplates()
                .clickInstallButton(0)
                .inputName(NEW_APP_NAME2)
                .clickSaveButton();

        //validation
        WORK_URL = instancePage.getUrl(1);
        WORK_PASSWORD = instancePage.getPassword();
        WORK_USER_NAME = instancePage.getUsername();

        LoginPage loginPage = new LoginPage(getDriver())
                .goToLoginPage(WORK_URL);

        loginPage.login(WORK_USER_NAME, WORK_PASSWORD);

        //validation
        new EntityPage(getDriver()).clickProject();
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

        instanceCreate(driver, "абвг");

        Assert.assertEquals(driver.findElement(ERROR).getText(), ERROR_MESSAGE);
    }
}