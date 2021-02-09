package model.work;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

public class FieldsPage extends ConfigurationPage{

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement createNewFolder;

    @FindBy(id = "pa-adm-new-fields-label")
    private WebElement inputLabelFields;

    @FindBy(className = "filter-option")
    private WebElement typeMenu;

    @FindBy(id = "pa-adm-create-fields-btn")
    private WebElement createButton;

    @FindBy(xpath = "//span[text() = 'string']/..")
    private WebElement typeFieldsString;

    @FindBy(xpath = "//span[text() = 'text']/..")
    private WebElement typeFieldsText;

    public FieldsPage(WebDriver driver) {
        super(driver);
    }

    public FieldsPage createFieldString(String label) {
        createNewFolder.click();
        inputLabelFields.sendKeys(label);
        typeMenu.click();
        getWait().until(TestUtils.movingIsFinished(typeFieldsString)).click();
        createButton.click();
        getWait().until(ExpectedConditions.elementToBeClickable(createNewFolder));

        return this;
    }

    public FieldsPage createFieldText(String label) {
        createNewFolder.click();
        inputLabelFields.sendKeys(label);
        typeMenu.click();
        getWait().until(TestUtils.movingIsFinished(typeFieldsText)).click();
        createButton.click();
        getWait().until(ExpectedConditions.elementToBeClickable(createNewFolder));

        return this;
    }
}
