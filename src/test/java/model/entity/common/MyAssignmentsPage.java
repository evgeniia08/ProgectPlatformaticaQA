package model.entity.common;

import model.base.EntityBaseTablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAssignmentsPage extends EntityBaseTablePage {

    @FindBy(xpath = "//b[text()='My Assignments']")
    private WebElement pageName;

    @FindBy(className = "col-md-12")
    private WebElement body;

    public MyAssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAssignmentsPageOpen() {
        return pageName.isDisplayed();
    }

    @Override
    public int getRowCount() {
        if (!body.getText().contains("Assign")) {
            return 0;
        } else {
            return getRows().size();
        }
    }

    @Override
    protected Object createEditPage() {
        return null;
    }

    @Override
    protected Object createViewPage() {
        return null;
    }
}
