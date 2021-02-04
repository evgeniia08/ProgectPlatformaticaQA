package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.InstanceEditPage;
import model.portal.edit.TemplateEditPage;
import model.portal.view.InstanceViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import runner.TestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class InstancePage extends PortalBaseTablePage<InstancePage, InstanceEditPage, InstanceViewPage> {

    private static final By ROW_MENU_TEMPLATE =
            By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='Save as template']");

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "subdomain")
    private WebElement inputSubdomain;

    @FindBy(id = "primary_language")
    private WebElement inputPrimaryLanguage;

    @FindBy(xpath = "//tbody//tr//td[2]")
    private List<WebElement> allInstanceNames;

    public InstancePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text() = 'Name']")
    private WebElement columnName;

    @Override
    protected InstanceEditPage createPortalEditPage() {
        return new InstanceEditPage(getDriver());
    }

    @Override
    protected InstanceViewPage createPortalViewPage() {
        return new InstanceViewPage(getDriver());
    }

    @Override
    public List<String> getRowData(int rowNumber) {
        return getEntireRowData(rowNumber).subList(1, 9);
    }

    public TemplateEditPage saveAsTemplate(int rowNumber) {
        clickRowMenuButton(rowNumber);
        getWait().until(TestUtils.movingIsFinished(ROW_MENU_TEMPLATE)).click();
        return new TemplateEditPage(getDriver());
    }

    public InstancePage clickColumnHeader(String columnName) {
        ProjectUtils.click(getWait(), getDriver().findElement(By.xpath(String.format("//div[text() = '%s']", columnName))));
        return this;
    }

    public List<String> getNames() {
        return allInstanceNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
