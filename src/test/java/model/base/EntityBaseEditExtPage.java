package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class EntityBaseEditExtPage<TablePage, EditPage> extends EntityBaseEditPage<TablePage> {

    @FindBy(css = "select#user > option")
    private List<WebElement> selectUserAllUsers;

    @FindBy(css = "div[class$=inner-inner]")
    private WebElement selectedUser;

    @FindBy(css = "select#user")
    private WebElement selectUser;

    public EntityBaseEditExtPage(WebDriver driver) {
        super(driver);
    }

    protected abstract EditPage createEditPage();

    public String getRandomUser() {
        List<WebElement> userList = selectUserAllUsers;
        String randomUser = userList.get(ThreadLocalRandom.current().nextInt(1, userList.size())).getText();

        return randomUser;
    }

    public EditPage selectUser(String user) {
        WebElement userText = getWait().until(ExpectedConditions.visibilityOf(selectedUser));
        getActions().moveToElement(userText).perform();
        new Select(selectUser).selectByVisibleText(user);

        return createEditPage();
    }

}
