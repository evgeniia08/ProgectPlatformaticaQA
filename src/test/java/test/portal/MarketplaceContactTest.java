package test.portal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;
import runner.type.Profile;
import runner.type.ProfileType;
import runner.type.Run;
import runner.type.RunType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class MarketplaceContactTest extends BaseTest {

    private static String getDate() {
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormatGmt.format(new Date());
    }

    private static final String MESSAGE = ProjectUtils.createUUID();
    private static final String[] FIELD_INPUTS = {"Help", "John Johnson", "john@gmail.com", MESSAGE, getDate(), "Submitted"};
    private static final By CONTACT_SUPPORT = By.xpath("//i[.='contact_support']");
    private static final By CREATE_NEW_RECORD = By.xpath("//i[.='create_new_folder']");
    private static final By SAVE_BTN = By.xpath("//button[.='Save']");

    private void fillContactForm(WebDriver driver) {
        List<WebElement> allFields = driver.findElements(By.xpath("//input[@type='text']|//textarea[@id='message']"));
        for (int i = 0; i < FIELD_INPUTS.length - 2; i++) {
            allFields.get(i).sendKeys(FIELD_INPUTS[i]);
        }
    }

    private void navigateToContactAndCreateNewRecord(WebDriver driver) {
        ProjectUtils.click(driver, getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (CONTACT_SUPPORT)));
        ProjectUtils.click(driver, getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated
                (CREATE_NEW_RECORD)));
    }

    @Test
    public void successfulContact() {
        WebDriver driver = getDriver();
        navigateToContactAndCreateNewRecord(driver);

        fillContactForm(driver);
        ProjectUtils.click(driver, driver.findElement(SAVE_BTN));

        List<WebElement> trs = driver.findElements(By.xpath("//table[@id='pa-all-entities-table']/tbody/tr"));
        Assert.assertEquals(trs.size(), 1);

        List<WebElement> allLines = driver.findElements(By.xpath("//td/a"));
        for (int i = 0; i < allLines.size(); i++) {
                  Assert.assertEquals(allLines.get(i).getText(), FIELD_INPUTS[i]);
       }
    }
}
