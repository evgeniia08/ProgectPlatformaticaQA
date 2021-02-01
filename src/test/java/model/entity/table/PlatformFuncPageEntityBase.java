package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.PlatformFuncEntityBaseEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class PlatformFuncPageEntityBase extends EntityBaseTablePage<PlatformFuncPageEntityBase, PlatformFuncEntityBaseEditPage> {

    public PlatformFuncPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PlatformFuncEntityBaseEditPage createEditPage() {
        return new PlatformFuncEntityBaseEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1,4);
    }
}
