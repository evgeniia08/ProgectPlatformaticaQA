package test.portal;

import model.base.LoginPage;
import model.base.WorkHomePage;
import model.entity.common.ErrorPage;
import model.portal.table.InstancePage;
import model.portal.table.TemplatePage;
import model.work.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;
import test.data.AppConstant;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class PortalPrivateTemplateCreationAndUsageTest extends BaseTest {

    private static final String NEW_APP_NAME = UUID.randomUUID().toString().substring(0, 10);
    private static final String NEW_APP_NAME2 = UUID.randomUUID().toString().substring(0, 10);
    private static final String ENTITY_NAME = "Project";
    private static final String ENTITY_FIELD_LABEL = "Label";
    private static final String ENTITY_FIELD_BODY = "Body";
    private static final String AUTHOR = "Burdishka";
    private static final String DESCRIPTION = "Description";
    private String WORK_URL;
    private String WORK_USER_NAME;
    private String WORK_PASSWORD;
    private static final List<String> EDIT_LABELS = Arrays.asList(ENTITY_FIELD_LABEL, ENTITY_FIELD_BODY);

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

    @Test(dependsOnMethods = "instanceCreateTest")
    public void instanceCreateSameNameNegativeTest() {

        ErrorPage errorPage = new InstancePage(getDriver())
                .clickListButton()
                .clickNewFolder()
                .inputName(NEW_APP_NAME)
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.PORTAL_ERROR_MESSAGE);
    }

    @Test(dependsOnMethods = "instanceCreateSameNameNegativeTest")
    public void instanceCreateCyrillicAlphabetNegativeTest() {

        ErrorPage errorPage = new InstancePage(getDriver())
                .clickNewFolder()
                .inputName("абвг")
                .clickSaveButtonErrorExpected();

        Assert.assertEquals(errorPage.getErrorMessage(), AppConstant.PORTAL_ERROR_MESSAGE);
    }
}