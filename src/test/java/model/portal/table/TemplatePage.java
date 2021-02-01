package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.InstanceEditPage;
import model.portal.edit.TemplateEditPage;
import model.portal.view.TemplateViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TemplatePage extends PortalBaseTablePage<TemplatePage, TemplateEditPage, TemplateViewPage> {

    public TemplatePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected TemplateEditPage createPortalEditPage() {
        return new TemplateEditPage(getDriver());
    }

    @Override
    protected TemplateViewPage createPortalViewPage() {
        return new TemplateViewPage(getDriver());
    }

    public InstanceEditPage clickInstallButton(int rowNumber) {
        rows.get(rowNumber).findElement(By.xpath("//button[text()='Install']")).click();
        return new InstanceEditPage(getDriver());
    }
}
