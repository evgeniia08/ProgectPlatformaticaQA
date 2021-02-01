package model.base;

import com.beust.jcommander.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PortalBaseTablePage<S, E, V> extends PortalBaseIndexPage {

    private static final String ROW_MENU_ = "//button[@data-toggle='dropdown']/../ul/li/a[text()='%s']";

    private static final By ROW_MENU_VIEW = By.xpath(String.format(ROW_MENU_, "view"));
    private static final By ROW_MENU_EDIT = By.xpath(String.format(ROW_MENU_, "edit"));
    private static final By ROW_MENU_DELETE = By.xpath(String.format(ROW_MENU_, "delete"));

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement buttonNew;

    @FindBy(className = "card-body")
    private WebElement body;

    @FindBy(xpath = "//table[@id='pa-all-entities-table']/tbody/tr")
    private List<WebElement> rows;

    @FindBy(xpath = "//a[contains(@href, '31')]/i[text()='list']")
    private WebElement listButton;

    public PortalBaseTablePage(WebDriver driver) {
        super(driver);
    }

    protected abstract E createPortalEditPage();

    protected abstract V createPortalViewPage();

    protected List<WebElement> getRows() {
        return rows;
    }

    public E clickNewFolder() {
        buttonNew.click();
        return createPortalEditPage();
    }

    public int getRowCount() {
        if (Strings.isStringEmpty(body.getText())) {
            return 0;
        } else {
            return getRows().size();
        }
    }

    public String getRowIconClass(int rowNumber) {
        return getRows().get(rowNumber).findElement(By.cssSelector("td > i")).getAttribute("class");
    }

    public List<String> getRowData(int rowNumber) {
        return rows.get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    private void clickRowMenu(int rowNumber, By menu) {
        rows.get(rowNumber).findElement(By.xpath("//button[@data-toggle]")).click();
        getWait().until(TestUtils.movingIsFinished(menu)).click();
    }

    public V viewRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_VIEW);
        return createPortalViewPage();
    }

    public V viewRow() {
        return viewRow(getRows().size() - 1);
    }

    public E editRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_EDIT);
        return createPortalEditPage();
    }

    public E editRow() {
        return editRow(getRows().size() - 1);
    }

    public S deleteRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_DELETE);
        return (S)this;
    }

    public S deleteRow() {
        return deleteRow(getRows().size() - 1);
    }

    public S clickListButton() {
        listButton.click();
        return (S)this;
    }
}
