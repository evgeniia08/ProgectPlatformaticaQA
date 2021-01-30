package model.entity.edit;

import model.BasePage;
import model.entity.table.PlaceholderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class PlaceholderEdit1Page extends BasePage {

    public PlaceholderEdit1Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='entity_form_data[string]']")
    WebElement strValue;

    @FindBy(xpath = "//textarea[@name='entity_form_data[text]']")
    WebElement txtValue;

    @FindBy(xpath = "//input[@name='entity_form_data[int]']")
    WebElement intValue;

    @FindBy(xpath = "//input[@name='entity_form_data[decimal]']")
    WebElement decValue;

    @FindBy(xpath = "//div/button[@id='pa-entity-form-save-btn']")
    WebElement saveButton;

    public String getStrValue() {
        return strValue.getAttribute("placeholder");
    }

    public String getTxtValue() {
        return txtValue.getAttribute("placeholder");
    }

    public String getIntValue() {
        return intValue.getAttribute("placeholder");
    }

    public String getDecValue() {
        return decValue.getAttribute("placeholder");
    }

    public PlaceholderEdit1Page fillFields (){
        ProjectUtils.fill(getWait(), strValue, getStrValue());
        ProjectUtils.fill(getWait(), txtValue, getTxtValue());
        ProjectUtils.fill(getWait(), intValue, getIntValue());
        ProjectUtils.fill(getWait(), decValue, getDecValue());
        return this;
    }

    public PlaceholderPage clickSaveButton (){
        ProjectUtils.click(getDriver(), saveButton);
        return new PlaceholderPage(getDriver());
    }
}