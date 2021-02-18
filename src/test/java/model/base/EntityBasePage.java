package model.base;

import com.beust.jcommander.Strings;
import model.entity.common.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityBasePage <TablePage, EditPage, ViewPage> extends MainPage {

    private static final String ROW_MENU_ = "//button[@data-toggle='dropdown']/../ul/li/a[text()='%s']";

    private static final By ROW_MENU_VIEW = By.xpath(String.format(ROW_MENU_, "view"));
    private static final By ROW_MENU_EDIT = By.xpath(String.format(ROW_MENU_, "edit"));
    private static final By ROW_MENU_DELETE = By.xpath(String.format(ROW_MENU_, "delete"));

    @FindBy(className = "card-body")
    private WebElement body;

    @FindBy(xpath = "//table[@id='pa-all-entities-table']/tbody/tr")
    private List<WebElement> trs;

    @FindBy(xpath = "//a[contains(@href, 'action_list')]/i[text()='list']")
    private WebElement listButton;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchBox;

    @FindBy(className = "pagination-info")
    private WebElement paginationInfo;

    public EntityBasePage(WebDriver driver) {
        super(driver);
    }

    protected abstract EditPage createEditPage();

    protected abstract ViewPage createViewPage();

    public WebElement getBody(){
        return body;
    }

    protected List<WebElement> getRows() {
        return trs;
    }

    public int getRowCount() {
        if (Strings.isStringEmpty(body.getText())) {
            return 0;
        } else {
            return getRows().size();
        }
    }

    public void waitRowCountToBe(int count) {
        getWait().until(i -> getRowCount() == count);
    }

    public String getRowIconClass(int rowNumber) {
        return getRows().get(rowNumber).findElement(By.cssSelector("td > i")).getAttribute("class");
    }

    public List<String> getRow(int rowNumber) {
        return getRow(rowNumber, "//td/a");
    }

    public List<String> getRow(int rowNumber, String xpath) {
        return getRows().get(rowNumber).findElements(By.xpath(xpath)).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickRowMenu(int rowNumber, By menu) {
        trs.get(rowNumber).findElement(By.xpath("//td//div//button")).click();
        getWait().until(TestUtils.movingIsFinished(menu)).click();
    }

    public ViewPage viewRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_VIEW);
        return createViewPage();
    }

    public ViewPage viewRow() {
        return viewRow(getRows().size() - 1);
    }

    public EditPage editRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_EDIT);
        return createEditPage();
    }

    public EditPage editRow() {
        return editRow(getRows().size() - 1);
    }

    public TablePage deleteRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_DELETE);
        return (TablePage)this;
    }

    public TablePage deleteRow() {
        return deleteRow(getRows().size() - 1);
    }

    public TablePage clickListButton() {
        listButton.click();
        return (TablePage)this;
    }

    public TablePage fillSearchBox(String text) {
        searchBox.sendKeys(text);
        return (TablePage) this;
    }

    public String getPaginationInfo() {
        return paginationInfo.getText();
    }
}
