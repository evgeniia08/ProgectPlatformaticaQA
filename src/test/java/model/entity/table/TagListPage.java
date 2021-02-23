package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.TagEditPage;
import model.entity.view.TagViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TagListPage extends EntityBaseTablePage<TagListPage, TagEditPage, TagViewPage> {

    @FindBy(xpath = "//i[text()='loyalty']")
    private WebElement tagButton;

    public TagListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected TagEditPage createEditPage() {
        return new TagEditPage(getDriver());
    }

    @Override
    protected TagViewPage createViewPage() {
        return new TagViewPage(getDriver());
    }

    public TagPage clickTagButton() {
        tagButton.click();
        return new TagPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 9);
    }
}
