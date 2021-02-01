package model.entity.edit;

import model.entity.table.ArithmeticFunctionPageEntityBase;
import model.base.EntityBaseEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

import java.util.Arrays;
import java.util.List;

public final class ArithmeticFunctionEntityBaseEditPage extends EntityBaseEditPage<ArithmeticFunctionPageEntityBase> {

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

    public ArithmeticFunctionEntityBaseEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ArithmeticFunctionPageEntityBase createPage() {
        return new ArithmeticFunctionPageEntityBase(getDriver());
    }

    public ArithmeticFunctionEntityBaseEditPage inputInitialValue(int value1, int value2) {
        String divInitialValue = div.getAttribute("value");
        ProjectUtils.fill(getWait(), F1, String.valueOf(value1));
        ProjectUtils.fill(getWait(), F2, String.valueOf(value2));
        getWait().until(d -> !div.getAttribute("value").equals(divInitialValue));

        return this;
    }

    public ArithmeticFunctionEntityBaseEditPage inputInitialValue(String value1, String value2) {

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

    public ArithmeticFunctionEntityBaseEditPage waitSumToBe(String value) {
        getWait().until(f -> sum.getAttribute("value").equals(value));
        return this;
    }

    public ArithmeticFunctionEntityBaseEditPage waitSubToBe(String value) {
        getWait().until(f -> sub.getAttribute("value").equals(value));
        return this;
    }

    public ArithmeticFunctionEntityBaseEditPage waitMulToBe(String value) {
        getWait().until(f -> mul.getAttribute("value").equals(value));
        return this;
    }

    public ArithmeticFunctionEntityBaseEditPage waitDivToBe(String value) {
        getWait().until(f -> div.getAttribute("value").equals(value));
        return this;
    }
}
