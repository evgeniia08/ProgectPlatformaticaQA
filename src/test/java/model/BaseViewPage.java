package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class BaseViewPage extends BasePage {

    @FindBy(css = ".pa-view-field")
    private List<WebElement> labeledValues;

    @FindAll({
            @FindBy(css = "div.card-body p"),
            @FindBy(css = ".pa-view-field")
    })
    private List<WebElement> allValues;

    public BaseViewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getValues() {
        return labeledValues.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getReferenceValues() {
        return allValues.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
