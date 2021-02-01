package model.portal.table;

import model.base.PortalBaseTablePage;
import model.portal.edit.ContactEditPage;
import model.portal.view.ContactViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import runner.TestUtils;

public class ContactPage extends PortalBaseTablePage<ContactPage, ContactEditPage, ContactViewPage> {

    private static final By ROW_MENU_EDIT = By.xpath("//button[@data-toggle='dropdown']/../ul/li/a[text()='edit']");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ContactEditPage createPortalEditPage() {
        return new ContactEditPage(getDriver());
    }

    @Override
    protected ContactViewPage createPortalViewPage() {
        return new ContactViewPage(getDriver());
    }

    public ContactEditPage editRow(int rowNumber) {
        clickRowMenuButton(rowNumber);
        getWait().until(TestUtils.movingIsFinished(ROW_MENU_EDIT)).click();
        return createPortalEditPage();
    }
}
