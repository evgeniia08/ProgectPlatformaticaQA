package model.base;

import model.entity.common.RecycleBinPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;
import runner.TestUtils;

public abstract class BaseIndexPage extends BasePage {

    @FindBy(id = "navbarDropdownProfile")
    protected WebElement userProfileButton;

    @FindBy(xpath = "//a[contains(text(), 'Reset')]")
    private WebElement resetButton;

    @FindBy(xpath = "//a[text()='Log out']")
    private WebElement logoutButton;

    @FindBy(css = "a[href*=recycle] > i")
    private WebElement recycleBinIcon;

    public BaseIndexPage(WebDriver driver) {
        super(driver);
    }

    protected void clickMainMenu(WebElement element) {
        ProjectUtils.scroll(getDriver(), element);
        element.click();
    }

    public String getCurrentUser() {
        return userProfileButton.getAttribute("textContent").split("\n")[3].trim();
    }

    public void resetUserData() {
        ProjectUtils.click(getDriver(), resetButton);
    }

    public RecycleBinPage clickRecycleBin() {
        ProjectUtils.click(getWait(), recycleBinIcon);
        return new RecycleBinPage(getDriver());
    }

    public LoginPage logout() {
        userProfileButton.click();
        getWait().until(TestUtils.movingIsFinished(logoutButton)).click();
        return new LoginPage(getDriver());
    }
}
