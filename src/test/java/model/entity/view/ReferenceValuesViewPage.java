package model.entity.view;

import model.base.EntityBaseViewPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceValuesViewPage extends EntityBaseViewPage {

    @FindAll({
            @FindBy(css = "div.card-body p"),
            @FindBy(css = ".pa-view-field")
    })
    private List<WebElement> allValues;

    public ReferenceValuesViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getValues() {
        return allValues.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
