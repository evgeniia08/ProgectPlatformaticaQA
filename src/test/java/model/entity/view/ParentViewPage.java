package model.entity.view;

import com.beust.jcommander.Strings;
import model.base.EntityBaseViewPage;
import model.entity.edit.ChildEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ParentViewPage extends EntityBaseViewPage {

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

    public ParentViewPage(WebDriver driver) {
        super(driver);
    }

    protected List<WebElement> getChildRows() {
        return rows;
    }

    public ChildEditPage clickNewFolder() {
        buttonNew.click();
        return new ChildEditPage(getDriver());
    }

    public int getRowCount() {
        if (Strings.isStringEmpty(body.getText())) {
            return 0;
        } else {
            return getChildRows().size();
        }
    }

    public String getRowIconClass(int rowNumber) {
        return getChildRows().get(rowNumber).findElement(By.cssSelector("td > i")).getAttribute("class");
    }

    public List<String> getRow(int rowNumber) {
        return getRow(rowNumber, "//td/a");
    }

    public List<String> getRow(int rowNumber, String xpath) {
        return getChildRows().get(rowNumber).findElements(By.xpath(xpath)).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickRowMenu(int rowNumber, By menu) {
        rows.get(rowNumber).findElement(By.xpath("//td//div//button")).click();
        getWait().until(TestUtils.movingIsFinished(menu)).click();
    }

    public ChildViewPage viewRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_VIEW);
        return new ChildViewPage(getDriver());
    }

    public ChildEditPage editRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_EDIT);
        return new ChildEditPage(getDriver());
    }

    public ParentViewPage deleteRow(int rowNumber) {
        clickRowMenu(rowNumber, ROW_MENU_DELETE);
        return this;
    }

}
