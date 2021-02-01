package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.BoardEntityBaseEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class BoardListPageEntityBase extends EntityBaseTablePage<BoardListPageEntityBase, BoardEntityBaseEditPage> {

    @FindBy(xpath = "//a[contains(@href, '31')]/i[text()='dashboard']")
    private WebElement boardButton;

    public BoardListPageEntityBase(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 9);
    }

    @Override
    protected BoardEntityBaseEditPage createEditPage() {
        return new BoardEntityBaseEditPage(getDriver());
    }

    public void clickBoardButton() {
        boardButton.click();
    }
}
