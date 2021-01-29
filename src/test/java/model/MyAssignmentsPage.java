package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAssignmentsPage extends BasePage{

    @FindBy(xpath = "//table[@data-context='list-screen-40']")
    private WebElement table;

    public MyAssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTablePresent() {
        return table.isDisplayed();
    }
}
