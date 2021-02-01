package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.TemplateEditPage;
import model.portal.view.InstanceViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import runner.TestUtils;

import java.util.List;

public class InstancePage extends PortalBaseTablePage<InstancePage, TemplateEditPage, InstanceViewPage> {

    private static final By ROW_MENU_TEMPLATE =
            By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='Save as template']");

    public InstancePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected TemplateEditPage createPortalEditPage() {
        return new TemplateEditPage(getDriver());
    }

    @Override
    protected InstanceViewPage createPortalViewPage() {
        return new InstanceViewPage(getDriver());
    }

    public List<String> getRowData(int rowNumber) {
        return getWholeRowData(rowNumber).subList(1, 9);
    }

    public TemplateEditPage saveAsTemplate(int rowNumber) {
        clickRowMenuButton(rowNumber);
        getWait().until(TestUtils.movingIsFinished(ROW_MENU_TEMPLATE)).click();
        return createPortalEditPage();
    }
}
