package model.base;

import com.beust.jcommander.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BaseTableModule extends BasePage {

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

    BaseTableModule(WebDriver driver) {
        super(driver);
    }

    void clickNewFolder() {
        buttonNew.click();
    }

    WebElement getBody() {
        return body;
    }

    List<WebElement> getRows() {
        return rows;
    }

    int getRowCount() {
        if (Strings.isStringEmpty(body.getText())) {
            return 0;
        } else {
            return getRows().size();
        }
    }

    String getRowIconClass(int rowNumber) {
        return getRows().get(rowNumber).findElement(By.cssSelector("td > i")).getAttribute("class");
    }

    List<String> getRow(int rowNumber) {
        return getRow(rowNumber, "//td/a/div");
    }

    List<String> getRow(int rowNumber, String xpath) {
        return getRows().get(rowNumber).findElements(By.xpath(xpath)).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    void clickRowMenu(int rowNumber, By menu) {
        rows.get(rowNumber).findElement(By.xpath("//td//div//button")).click();
        getWait().until(TestUtils.movingIsFinished(menu)).click();
    }

    void clickRowMenuViewRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_VIEW);
    }

    void clickRowMenuEditRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_EDIT);
    }

    void clickRowMenuDeleteRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_DELETE);
    }

    void clickListButton() {
        listButton.click();
    }
}
