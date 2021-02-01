package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ArithmeticInlineEntityBaseEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ArithmeticInlinePageEntityBase extends EntityBaseTablePage<ArithmeticInlinePageEntityBase, ArithmeticInlineEntityBaseEditPage> {

    @FindBy(css = "input#sum")
    private WebElement sum;

    @FindBy(css = "input#sub")
    private WebElement sub;

    @FindBy(css = "input#mul")
    private WebElement mul;

    @FindBy(css = "input#div")
    private WebElement div;

    public ArithmeticInlinePageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ArithmeticInlineEntityBaseEditPage createEditPage() {
        return new ArithmeticInlineEntityBaseEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 7);
    }
}
