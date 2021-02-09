package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.PlaceholderEditPage;
import model.entity.view.PlaceholderViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public final class PlaceholderPage extends EntityBaseTablePage<PlaceholderPage, PlaceholderEditPage, PlaceholderViewPage> {

    public PlaceholderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PlaceholderEditPage createEditPage() {
        return new PlaceholderEditPage(getDriver());
    }

    @Override
    protected PlaceholderViewPage createViewPage() {
        return new PlaceholderViewPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 10);
    }

    public String getTitle(int rowNumber) {
        return getRow(rowNumber).get(0);
    }

    public String getDecimal(int rowNumber) {
        return getRow(rowNumber).get(3);
    }
}
