package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class WorkBaseTablePage<S, E, V> extends WorkBaseIndexPage{

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement createNewFolder;


    public WorkBaseTablePage(WebDriver driver) {
        super(driver);
    }

    protected abstract E createWorkEditPage();

    protected abstract V createWorkViewPage();

    public E clickCreateNewFolder() {
        createNewFolder.click();
        return createWorkEditPage();
    }
}
