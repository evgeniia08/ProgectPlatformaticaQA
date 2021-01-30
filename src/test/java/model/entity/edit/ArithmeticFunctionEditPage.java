package model.entity.edit;

import model.entity.table.ArithmeticFunctionPage;
import model.BaseEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

import java.util.Arrays;
import java.util.List;

public final class ArithmeticFunctionEditPage extends BaseEditPage<ArithmeticFunctionPage> {

    @FindBy(id = "f1")
    private WebElement F1;

    @FindBy(id = "f2")
    private WebElement F2;

    @FindBy(id = "sum")
    private WebElement sum;

    @FindBy(id = "sub")
    private WebElement sub;

    @FindBy(id = "mul")
    private WebElement mul;

    @FindBy(id = "div")
    private WebElement div;

    public ArithmeticFunctionEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ArithmeticFunctionPage createPage() {
        return new ArithmeticFunctionPage(getDriver());
    }

    public ArithmeticFunctionEditPage inputInitialValue(int value1, int value2) {
        String divInitialValue = div.getAttribute("value");
        ProjectUtils.fill(getWait(), F1, String.valueOf(value1));
        ProjectUtils.fill(getWait(), F2, String.valueOf(value2));
        getWait().until(d -> !div.getAttribute("value").equals(divInitialValue));

        return this;
    }

    public ArithmeticFunctionEditPage inputInitialValue(String value1, String value2) {

        ProjectUtils.fill(getWait(), F1, String.valueOf(value1));
        ProjectUtils.fill(getWait(), F2, String.valueOf(value2));

        return this;
    }

    public List<String> getValues() {

        String[] actual = {F1.getAttribute("value"), F2.getAttribute("value"),
                sum.getAttribute("value"), sub.getAttribute("value"),
                mul.getAttribute("value"), div.getAttribute("value")};

        return Arrays.asList(actual);
    }
}