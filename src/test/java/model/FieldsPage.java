package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class FieldsPage extends BaseTablePage<FieldsPage, FieldsEditPage> {

    public FieldsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FieldsEditPage createEditPage() {
        return new FieldsEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public String getTitle(int rowNumber) {
        return getRows().get(rowNumber).findElement(By.xpath("//td[2]/a/div")).getText();
    }

    public String getDecimal(int rowNumber) {
        return getRows().get(rowNumber).findElement(By.xpath("//td[5]/a/div")).getText();
    }

}
