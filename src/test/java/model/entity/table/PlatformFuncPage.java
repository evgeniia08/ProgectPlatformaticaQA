package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.PlatformFuncEditPage;
import model.entity.view.PlatformFuncViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class PlatformFuncPage extends EntityBaseTablePage<PlatformFuncPage, PlatformFuncEditPage, PlatformFuncViewPage> {

    public PlatformFuncPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PlatformFuncEditPage createEditPage() {
        return new PlatformFuncEditPage(getDriver());
    }

    @Override
    protected PlatformFuncViewPage createViewPage() {
        return new PlatformFuncViewPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1,4);
    }
}
