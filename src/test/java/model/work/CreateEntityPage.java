package model.work;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateEntityPage extends ConfigurationPage{

    @FindBy(id = "pa-adm-new-entity-label")
    private WebElement inputName;

    @FindBy(id = "pa-adm-create-entity-btn")
    private WebElement createButton;

    @FindBy(xpath = "//button[contains(text(), 'Cancel')]")
    private WebElement cancelButton;

    public CreateEntityPage(WebDriver driver) {
        super(driver);
    }

    public FieldsPage createEntity(String name) {
        inputName.sendKeys(name);
        createButton.click();
        return new FieldsPage(getDriver());
    }
}
