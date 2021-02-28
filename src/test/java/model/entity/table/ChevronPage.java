package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ChevronEditPage;
import model.entity.view.ChevronViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public final class ChevronPage extends EntityBaseTablePage<ChevronPage, ChevronEditPage, ChevronViewPage> {

    public ChevronPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr//td[4]//a")
    private List <WebElement> element;

    @FindBy(xpath = "//tr//td[2]//a")
    private WebElement fieldString;

    @FindBy(xpath = "//div/div/a[text() = 'All']")
    private WebElement filterAll;

    @FindBy(xpath = "//div/div/a[text() = 'Pending']")
    private WebElement filterPending;

    @FindBy(xpath = "//div/div/a[text() = 'Fulfillment']")
    private WebElement filterFulfillment;

    @FindBy(xpath = "//div/div/a[text() = 'Sent']")
    private WebElement filterSent;

    private static final String STRING_FIELD = "//tr[%d]/td[2]/a";

    @Override
    protected ChevronEditPage createEditPage() {
        return new ChevronEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 7);
    }

    @Override
    protected ChevronViewPage createViewPage() {
        return new ChevronViewPage(getDriver());
    }

    private void sleep(int miliSec){
        try {
            Thread.sleep(miliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ChevronPage clickRowToView(int rowNumber) {
        getDriver().findElement(By.xpath(String.format(STRING_FIELD, rowNumber))).click();
        return new ChevronPage(getDriver());
    }

    public List<String> getColumn() {
        List<WebElement> list = getDriver().findElements(By.xpath("//span[@class='pa-view-field']"));
        return list.stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public ChevronPage orderBy() {
        getDriver().findElement(By.xpath("//ul[@role='tablist']/li[2]")).click();
        return this;
    }

    public ChevronPage dragAndDrop(int rowFrom, int rowTo) {
        WebElement bottom = getDriver().findElement(By.xpath(String.format("//tbody/tr[%d]/td[2]/a", rowFrom)));
        WebElement up = getDriver().findElement(By.xpath(String.format("//tbody/tr[%d]/td[2]/a", rowTo)));
        new Actions(getDriver())
                .dragAndDrop(bottom, up)
                .build()
                .perform();
        return this;
    }

    public String getCellData(int rowNumber) {
        return getDriver().findElement(By.xpath(String.format("//tbody/tr[%d]/td[3]", rowNumber))).getText();
    }

    public String getStringValue() {
        return getDriver().findElement(By.xpath("//tr//td[2]//a")).getText();
    }

    public int getSum() {
        int sum = 0;
        for (WebElement li1 : element) {
            String s = li1.getText().replace("(", "");
            sum += Integer.parseInt(s.replace(")", ""));
        } return sum;
    }

    public int getAvr() {
        int avrSum = getSum()/ element.size();
        return avrSum;
    }


    /**
     * Click on one of the 4 filters at the top of the page
     * @param filter possible values: "Fulfillment", "Pending", "Sent", or "All"
     * @return ChevronPage for chaining methods
     */
    public ChevronPage clickFilter(String filter) {
        switch(filter)
        {
            case "Fulfillment":
                ProjectUtils.click(getDriver(), filterFulfillment);
                break;
            case "Pending":
                ProjectUtils.click(getDriver(),filterPending);
                break;
            case "Sent":
                ProjectUtils.click(getDriver(), filterSent);
                break;
            case "All":
                ProjectUtils.click(getDriver(), filterAll);
                break;
        }
        return this;
    }
}
