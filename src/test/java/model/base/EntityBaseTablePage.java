package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class EntityBaseTablePage<TablePage, EditPage, ViewPage> extends EntityBasePage<TablePage, EditPage, ViewPage> {

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement buttonNew;

    public EntityBaseTablePage(WebDriver driver) {
        super(driver);
    }

    protected abstract EditPage createEditPage();

    public EditPage clickNewFolder() {
        buttonNew.click();
        return createEditPage();
    }
}
