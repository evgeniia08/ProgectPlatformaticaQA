package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.InitEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitPage extends EntityBaseTablePage<InitPage, InitEditPage> {

    @FindBy(xpath = "//td[.='User 1 Demo']")
    private WebElement defaultUser;

    public InitPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected InitEditPage createEditPage(){
        return new InitEditPage(getDriver());
    };

    public String getDefaultUser(){
        return defaultUser.getText();
    }
}