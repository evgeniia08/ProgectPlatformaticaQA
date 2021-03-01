package model.entity.view;

import model.base.EntityBaseViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChevronViewPage extends EntityBaseViewPage {

    @FindBy(xpath = "//a[@class = 'pa-chev-active']")
    private WebElement activeChevrone;

    public ChevronViewPage(WebDriver driver) {
        super(driver);
    }

    public String getActiveChevronText() {
        return activeChevrone.getText();
    }
}
