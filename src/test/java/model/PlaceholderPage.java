
package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import runner.ProjectUtils;

public final class PlaceholderPage extends BasePage{

    @FindBy(xpath = "//i[contains(text(),'create_new_folder')]")
    private WebElement newFolderButton;

    @FindBy(xpath = "//tr/td[2]/a/div")
    WebElement el1;

    @FindBy(xpath = "//tr/td[3]/a/div")
    WebElement el2;

    @FindBy(xpath = "//tr/td[4]/a/div")
    WebElement el3;

    @FindBy(xpath = "//tr/td[5]/a/div")
    WebElement el4;

    @FindBy(xpath = "//tr[@data-index='0']//button/i[text()='menu']")
    WebElement actions;

    @FindBy(xpath = "//tr[@data-index='0']//div//li/a[text()='delete']")
    WebElement delete;

    public PlaceholderPage(WebDriver driver) {
        super(driver);
    }

    public PlaceholderEditPage createNewRecord(){
        newFolderButton.click();

        return new PlaceholderEditPage(getDriver());
    }

    public PlaceholderEdit1Page goPlaceholderEdit1Page() {
        newFolderButton.click();
        return new PlaceholderEdit1Page(getDriver());
    }

    public void verify (String stringValue, String textValue, String integerValue, String decimalValue) {
        Assert.assertEquals(el1.getText(), stringValue);
        Assert.assertEquals(el2.getText(), textValue);
        Assert.assertEquals(el3.getText(), integerValue);
        Assert.assertEquals(el4.getText(), decimalValue);
    }

    public void deleteRecord (){
        ProjectUtils.click(getDriver(), actions);
        ProjectUtils.click(getDriver(), delete);
    }
}