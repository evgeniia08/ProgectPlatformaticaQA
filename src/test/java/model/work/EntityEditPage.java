package model.work;

import model.base.WorkBaseEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class EntityEditPage extends WorkBaseEditPage {

    @FindBy(xpath = "//label")
    private List<WebElement> inputFields;

    public EntityEditPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getInputValues() {
        return inputFields.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
