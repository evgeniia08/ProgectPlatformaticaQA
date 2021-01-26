import model.AssignPage;
import model.MainPage;
import model.MyAssignmentsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;

import java.util.UUID;

@Run(run = RunType.Multiple)
public class EntityAssign1Test extends BaseTest {

    private static final String STRING_INP = UUID.randomUUID().toString();
    private static final ProfileType PROFILE_TYPE = ProfileType.DEFAULT;
    private static final String FIRST_USER_NAME =  PROFILE_TYPE.getUserName();
    private static final String FIRST_USER_PASS =  PROFILE_TYPE.getPassword();
    private static final By ASSIGN_TAB = By.xpath("//a[contains(@href, 'id=37')]");
    private static final By ASSIGNMENTS_TAB = By.xpath("//li[@id='pa-menu-item-41']");
    private static final By ASSIGNMENTS_TAB_TEXT = By.xpath("//h3[contains(text(), 'Tasks for')]");
    private static final By RECORD = By.xpath("//tbody/tr");
    private static final By RECORD_TABLE = By.className("col-md-12");
    private static final By LOG_OUT = By.xpath("//a[text()='Log out']");


    private void changeUser(WebDriver driver, String user) {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        ProjectUtils.click(driver, driver.findElement(ASSIGN_TAB));

        Select changeUser = new Select(driver.findElement(By.className("pa-list-assignee")));

        changeUser.selectByVisibleText(user);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@style,'bisque')]")));

        driver.navigate().refresh();
    }

    @Test
    public void assignTest() {

        final String intInp = String.valueOf((int)(Math.random() * 100));
        final String decimalInp = String.valueOf(Math.random());

        AssignPage assignPage = new AssignPage(getDriver())
                .clickMenuAssign()
                .clickNewFolder()
                .fillOutForm(STRING_INP, "test text", intInp, decimalInp)
                .clickSaveButton()
                .selectUser(PROFILE_TYPE.getUserName());

        Assert.assertEquals(assignPage.getSelectedUser(), PROFILE_TYPE.getUserName());

        new MainPage(getDriver()).clickMenuMyAssignments();

        Assert.assertTrue(new MyAssignmentsPage(getDriver()).isTablePresent());
    }

    @Ignore
    @Test (dependsOnMethods = "assignTest")
    public void editTest() {

        WebDriver driver = getDriver();
        ProfileType.renewCredentials();

        changeUser(driver, PROFILE_TYPE.getUserName());

        WebElement selectedUser = driver.findElement(By.xpath
                (String.format("//option[@selected and text()='%s']", PROFILE_TYPE.getUserName())));
        Assert.assertTrue(selectedUser.isDisplayed());

        driver.findElement(ASSIGNMENTS_TAB).click();
        driver.findElement(ASSIGNMENTS_TAB_TEXT);

        Assert.assertFalse(driver.findElement(RECORD_TABLE).getText().contains(STRING_INP));

        ProjectUtils.click(driver, driver.findElement(LOG_OUT));
        PROFILE_TYPE.login(driver);

        driver.findElement(ASSIGNMENTS_TAB).click();

        Assert.assertTrue(driver.findElement(RECORD_TABLE).getText().contains(STRING_INP));
    }

    @Ignore
    @Test (dependsOnMethods = "editTest")
    public void deleteTest() {

        WebDriver driver = getDriver();

        ProjectUtils.click(driver, driver.findElement(LOG_OUT));
        PROFILE_TYPE.login(driver, FIRST_USER_NAME, FIRST_USER_PASS);

        changeUser(driver, FIRST_USER_NAME);

        ProjectUtils.click(driver, driver.findElement(By.xpath("//a[text()='delete']")));

        Assert.assertFalse(driver.findElement(RECORD_TABLE).getText().contains(STRING_INP));

        ProjectUtils.click(driver, driver.findElement(By.xpath("//a[contains(@href,'recycle_bin')]")));

        Assert.assertTrue(driver.findElement(RECORD).isDisplayed());

        driver.findElement(ASSIGNMENTS_TAB).click();

        Assert.assertFalse(driver.findElement(RECORD_TABLE).getText().contains(STRING_INP));
    }
}
