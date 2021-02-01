package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.AssignEntityBaseEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public final class AssignPageEntityBase extends EntityBaseTablePage<AssignPageEntityBase, AssignEntityBaseEditPage> {

    @FindBy(xpath = "//select[@class='pa-list-assignee']")
    private WebElement assignee;

    @FindBy(xpath = "//option[@selected]")
    private WebElement selectedUser;

    public AssignPageEntityBase(WebDriver driver) {
        super(driver);
    }

    public AssignPageEntityBase selectUser(String user) {
        new Select(assignee).selectByVisibleText(user);
        getDriver().navigate().refresh();
        return this;
    }

    public String getSelectedUser() {
        return selectedUser.getText();
    }

    @Override
    protected AssignEntityBaseEditPage createEditPage() {
        return new AssignEntityBaseEditPage(getDriver());
    }
}
