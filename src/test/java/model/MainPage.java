package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

public class MainPage extends BasePage {

    @FindBy(id = "navbarDropdownProfile")
    WebElement userProfileButton;

    @FindBy(xpath = "//a[contains(text(), 'Reset')]")
    WebElement resetButton;

    @FindBy(css = "a[href*=recycle] > i")
    private WebElement recycleBinIcon;

    @FindBy(xpath = "//li[@id = 'pa-menu-item-45']")
    private WebElement menuFields;

    @FindBy(xpath = "//p[contains(text(),'Import values')]")
    private WebElement menuImportValues;

    @FindBy(xpath = "//p[contains(text(),'Home')]")
    private WebElement leftMenu;

    @FindBy(xpath = "//p[contains(text(), 'Export')]")
    private WebElement menuExport;

    @FindBy(css = "#menu-list-parent>ul>li>a[href*='id=62")
    private WebElement menuEventsChain2;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private void clickMenu(WebElement element) {
        ProjectUtils.scroll(getDriver(), element);
        element.click();
    }

    public String getCurrentUser() {
        String profileButtonText = getWait().until(ExpectedConditions.visibilityOf(userProfileButton)).getText();
        return profileButtonText.substring(profileButtonText.indexOf(' ') + 1).toLowerCase();
    }

    public MainPage resetUserData() {
        ProjectUtils.click(getWait(), userProfileButton);
        ProjectUtils.click(getWait(), resetButton);
        return this;
    }

    public RecycleBinPage clickRecycleBin () {
        ProjectUtils.click(getWait(), recycleBinIcon);
        return new RecycleBinPage(getDriver());
    }

    public FieldsPage clickMenuFields() {
        clickMenu(menuFields);
        return new FieldsPage(getDriver());
    }

    public ImportValuesPage clickMenuImportValues() {
        clickMenu(menuImportValues);
        return new ImportValuesPage(getDriver());
    }
  
    public Chain2Page clickMenuEventsChain2() {
        clickMenu(menuEventsChain2);
        return new Chain2Page(getDriver());
    }

    public ExportPage clickMenuExport() {
        clickMenu(menuExport);
        return new ExportPage(getDriver());
    }
}