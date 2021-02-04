package model.entity.view;

import model.base.EntityBaseViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class DefaultViewPage extends EntityBaseViewPage {

    private static final By BY_TAG_TD = By.tagName("td");

    @FindBy(xpath = "//table[@class='pa-entity-table']/tbody/tr")
    private List<WebElement> trs;

    public DefaultViewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getEmbeddedRow(int rowNumber) {
        List<String> result = new ArrayList<>();

        for (WebElement cell : trs.get(rowNumber).findElements(BY_TAG_TD)) {
            result.add(cell.getText());
        }

        return result;
    }

}
