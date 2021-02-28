package model.entity.edit;
import model.base.EntityBaseEditPage;
import model.entity.table.ChevronPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import static runner.ProjectUtils.fill;

public final class ChevronEditPage extends EntityBaseEditPage<ChevronPage> {

    public ChevronEditPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(text(),'Pending')]")
    private WebElement statusButton;

    @FindBy(xpath = "//span[contains(text(),'Fulfillment')]")
    private WebElement fulfillmentButton;

    @FindBy(xpath = "//span[contains(text(),'Pending')]")
    private WebElement pendingButton;

    @FindBy(xpath = "//span[contains(text(),'Sent')]")
    private WebElement sentButton;

    @FindBy(xpath = "//textarea[@id = 'text']")
    private WebElement inputString;

    @FindBy(xpath = "//input[@id = 'int']")
    private WebElement inputInt;

    @FindBy(xpath = "//input[@id = 'decimal']")
    private WebElement inputDecimal;

    @FindBy(xpath = "//input[@id = 'date']")
    private WebElement inputDate;

    @FindBy(xpath = "//input[@id = 'datetime']")
    private WebElement inputDateTime;

    @Override
    protected ChevronPage createPage() {
        return new ChevronPage(getDriver());
    }

    public ChevronEditPage chooseRecordStatus(String status) {
        statusButton.click();
        switch(status)
        {
            case "Fulfillment":
                getActions().moveToElement(fulfillmentButton).perform();
                fulfillmentButton.click();
                break;
            case "Pending":
                getActions().moveToElement(pendingButton).perform();
                pendingButton.click();
                break;
            case "Sent":
                getActions().moveToElement(sentButton).perform();
                sentButton.click();
                break;
        }

        return this;
    }

    public ChevronEditPage sendKeys(String comments, String int_, String decimal, String Time, String Data) {
        fill(getWait(), inputString, comments);
        fill(getWait(), inputInt, int_);
        fill(getWait(), inputDecimal, decimal);
        fill(getWait(), inputDate, Data);
        fill(getWait(), inputDateTime, Time);
        return this;
    }

    public ChevronEditPage ChooseValues() {
        List<WebElement> list = getDriver().findElements(By.xpath("//select[@name='entity_form_data[user]']//option"));
        for (WebElement listOfValues : list) {
            if (listOfValues.getText().equals("User 1")) {
                listOfValues.click();
                break;
            }
        }return this;
    }
}