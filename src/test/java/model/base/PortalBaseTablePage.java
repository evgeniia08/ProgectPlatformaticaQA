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
    private static final By ROW_MENU_DELETE = By.xpath(String.format(ROW_MENU_, "delete"));

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement buttonNew;

    @FindBy(className = "card-body")
    private WebElement body;

    @FindBy(xpath = "//table[@id='pa-all-entities-table']/tbody/tr")
    protected List<WebElement> rows;

    @FindBy(xpath = "//a[@class='nav-link active']")
    private WebElement listButton;

    @FindBy(xpath = "//h4[contains(text(), 'Password')]/b")
    private WebElement password;

    @FindBy(xpath = "//h4[contains(text(), 'Username')]/b")
    private WebElement username;

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

    public List<String> getEntireRowData(int rowNumber) {
        return rows.get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getRowData(int rowNumber) {
        return getEntireRowData(rowNumber).subList(1, 7);
    }

    protected void clickRowMenuButton(int rowNumber) {
        rows.get(rowNumber).findElement(By.xpath("//td//div//button")).click();
    }

    private void clickRowMenu(int rowNumber, By menu) {
        clickRowMenuButton(rowNumber);
        getWait().until(TestUtils.movingIsFinished(menu)).click();
    }

    public V viewRowDirectly(int rowNumber) {
        for (WebElement cellElement : rows.get(rowNumber).findElements(By.xpath("//td[2]//div")).subList(1,6)) {
            if (!Strings.isStringEmpty(cellElement.getText())) {
                cellElement.click();
                return createPortalViewPage();
            }
        }
        throw new RuntimeException("Unable to view record/draft by clicking row directly on table page");
    }

    public V viewRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_VIEW);
        return createPortalViewPage();
    }

    public S deleteRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_DELETE);
        return (S)this;
    }

    public S clickListButton() {
        listButton.click();
        return (S)this;
    }

    public String getPassword() {
        return password.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String getUrl(int rowNumber) {
        return getEntireRowData(rowNumber).get(4);
    }
}
