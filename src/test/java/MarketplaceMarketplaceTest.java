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
import java.util.List;


@Profile(profile = ProfileType.MARKETPLACE)
@Run(run = RunType.Multiple)
public class MarketplaceMarketplaceTest extends BaseTest {

    private static final By MARKETPLACE = By.xpath("//p[contains (text(), 'Marketplace')]/preceding-sibling::i");
    private static final By BUY_NOW_BTN = By.xpath("//button[.='Buy now']");
    private static final By GET_WITH_CREDIT_BTN = By.xpath("//a/button");
    private static String productPrice;
    private static double balanceLeftover;
    private static List<WebElement> allProductsPrices;
    private static List<WebElement> listOfAllProducts;
    private static List<WebElement> productDescriptionAndPrice;
    private static List<WebElement> userEmailAndBalance;


    @Test
    public void buyProduct() {
        WebDriver driver = getDriver();
        ProjectUtils.click(driver, driver.findElement(MARKETPLACE));

        listOfAllProducts = getDriver().findElements(By.xpath("//div[@class='card']/a"));
        ProjectUtils.scroll(driver, listOfAllProducts.get(listOfAllProducts.size() - 1));
        allProductsPrices = driver.findElements(By.xpath("//div[@class='price']/h3"));
        productPrice = allProductsPrices.get(allProductsPrices.size() - 1).getText().substring(2);
        ProjectUtils.click(driver, listOfAllProducts.get(listOfAllProducts.size() - 1));
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(), '" + productPrice + "')]")).isDisplayed());

        ProjectUtils.click(driver, driver.findElement(BUY_NOW_BTN));
        productDescriptionAndPrice = driver.findElements(By.xpath("//div[@class='card-body']"));
        Assert.assertTrue(productDescriptionAndPrice.get(1).getText().contains(productPrice));

        userEmailAndBalance = driver.findElements(By.xpath("//div[@class='card-body']/div//h4"));
        double originalBalance = Double.parseDouble(userEmailAndBalance.get(1).getText().substring(11));
        double productPrice$ = Double.parseDouble(productPrice);
        balanceLeftover = originalBalance - productPrice$;
        Assert.assertTrue(originalBalance > productPrice$);

        ProjectUtils.click(driver, driver.findElement(GET_WITH_CREDIT_BTN));
        Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Thank you for your purchase");
    }

    @Test (dependsOnMethods = "buyProduct")
    public void checkTheRemainingBalance() {
        WebDriver driver = getDriver();
        ProjectUtils.click(driver, driver.findElement(MARKETPLACE));
        listOfAllProducts = getDriver().findElements(By.xpath("//div[@class='card']/a"));
        ProjectUtils.click(driver, listOfAllProducts.get(0));
        ProjectUtils.click(driver, driver.findElement(BUY_NOW_BTN));
        userEmailAndBalance = driver.findElements(By.xpath("//div[@class='card-body']/div//h4"));
        Assert.assertEquals(userEmailAndBalance.get(1).getText().substring(11), String.valueOf(balanceLeftover));
    }
}
