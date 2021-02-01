package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ReferenceValuesEntityBaseEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceValuesPageEntityBase extends EntityBaseTablePage<ReferenceValuesPageEntityBase, ReferenceValuesEntityBaseEditPage> {

    public ReferenceValuesPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ReferenceValuesEntityBaseEditPage createEditPage() {
        return new ReferenceValuesEntityBaseEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 4);
    }
}
