package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.AssignEditPage;
import model.entity.view.AssignViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public final class AssignPage extends EntityBaseTablePage<AssignPage, AssignEditPage, AssignViewPage> {

    @FindBy(xpath = "//select[@class='pa-list-assignee']")
    private WebElement assignee;

    @FindBy(xpath = "//option[@selected]")
    private WebElement selectedUser;

    public AssignPage(WebDriver driver) {
        super(driver);
    }

    public AssignPage selectUser(String user) {
        new Select(assignee).selectByVisibleText(user);
        getDriver().navigate().refresh();
        return this;
    }

    public String getSelectedUser() {
        return selectedUser.getText();
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRow(rowNumber, "//td/a");
    }

    @Override
    protected AssignEditPage createEditPage() {
        return new AssignEditPage(getDriver());
    }

    @Override
    protected AssignViewPage createViewPage() {
        return new AssignViewPage(getDriver());
    }
}
