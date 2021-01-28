package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public final class AssignPage extends BaseTablePage<AssignPage, AssignEditPage>{

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
    protected AssignEditPage createEditPage() {
        return new AssignEditPage(getDriver());
    }
}
