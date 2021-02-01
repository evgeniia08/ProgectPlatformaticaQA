package model.entity.view;

import model.base.EntityBaseViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitViewPage extends EntityBaseViewPage {

    @FindBy(xpath = "//div//following-sibling::label[.='User']//following-sibling::p'")
    private WebElement userDemoView;

    public InitViewPage(WebDriver driver) {
        super(driver);
    }

    public String getDefaultUserView(){
        return userDemoView.getText();
    }
}
