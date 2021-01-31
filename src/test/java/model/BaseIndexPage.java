package model;

import model.entity.common.RecycleBinPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public abstract class BaseIndexPage extends BasePage {

    @FindBy(id = "navbarDropdownProfile")
    private WebElement userProfileButton;

    @FindBy(xpath = "//a[contains(text(), 'Reset')]")
    private WebElement resetButton;

    @FindBy(css = "a[href*=recycle] > i")
    private WebElement recycleBinIcon;

    public BaseIndexPage(WebDriver driver) {
        super(driver);
    }

    protected void clickMenu(WebElement element) {
        ProjectUtils.scroll(getDriver(), element);
        element.click();
    }

    public String getCurrentUser() {
        return userProfileButton.getAttribute("textContent").split("\n")[3].trim();
    }

    public void resetUserData() {
        ProjectUtils.click(getWait(), resetButton);
    }

    public RecycleBinPage clickRecycleBin () {
        ProjectUtils.click(getWait(), recycleBinIcon);
        return new RecycleBinPage(getDriver());
    }
}
