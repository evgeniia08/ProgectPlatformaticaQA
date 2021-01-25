package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ReferenceValuesPage extends BaseTablePage<ReferenceValuesPage, ReferenceValuesEditPage> {

    public ReferenceValuesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ReferenceValuesEditPage createEditPage() {
        return new ReferenceValuesEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 4);
    }
}
