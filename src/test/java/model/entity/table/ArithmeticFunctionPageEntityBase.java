package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ArithmeticFunctionEntityBaseEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public final class ArithmeticFunctionPageEntityBase extends EntityBaseTablePage<ArithmeticFunctionPageEntityBase, ArithmeticFunctionEntityBaseEditPage> {

    public ArithmeticFunctionPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ArithmeticFunctionEntityBaseEditPage createEditPage() {
        return new ArithmeticFunctionEntityBaseEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 7);
    }
}
