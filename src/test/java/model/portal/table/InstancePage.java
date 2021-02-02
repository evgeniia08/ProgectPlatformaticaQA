package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.InstanceEditPage;
import model.portal.edit.TemplateEditPage;
import model.portal.view.InstanceViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import runner.TestUtils;

import java.util.List;

public class InstancePage extends PortalBaseTablePage<InstancePage, InstanceEditPage, InstanceViewPage> {

    private static final By ROW_MENU_TEMPLATE =
            By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='Save as template']");

    public InstancePage(WebDriver driver) {
        super(driver);
    }

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
}
