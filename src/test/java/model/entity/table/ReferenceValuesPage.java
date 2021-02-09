package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ReferenceValuesEditPage;
import model.entity.view.ReferenceValuesViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceValuesPage extends EntityBaseTablePage<ReferenceValuesPage, ReferenceValuesEditPage, ReferenceValuesViewPage> {

    public ReferenceValuesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ReferenceValuesEditPage createEditPage() {
        return new ReferenceValuesEditPage(getDriver());
    }

    @Override
    protected ReferenceValuesViewPage createViewPage() {
        return new ReferenceValuesViewPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 4);
    }
}
