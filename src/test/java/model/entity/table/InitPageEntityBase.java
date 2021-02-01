package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.InitEntityBaseEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitPageEntityBase extends EntityBaseTablePage<InitPageEntityBase, InitEntityBaseEditPage> {

    @FindBy(xpath = "//td[.='User 1 Demo']")
    private WebElement defaultUser;

    public InitPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InitEntityBaseEditPage createEditPage(){
        return new InitEntityBaseEditPage(getDriver());
    };

    public String getDefaultUser(){
        return defaultUser.getText();
    }
}