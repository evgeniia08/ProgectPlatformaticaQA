package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.BoardEditPage;
import model.entity.view.BoardViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class BoardListPage extends EntityBaseTablePage<BoardListPage, BoardEditPage, BoardViewPage> {


    @FindBy(xpath = "//a[contains(@href, '31')]/i[text()='dashboard']")
    private WebElement boardButton;

    @FindBy(xpath = "//tr[2]/td[7]")
    private WebElement timeSecondRow;

    @FindBy(xpath = "//span[normalize-space()='Showing 1 to 1 of 1 rows']")
    private WebElement confirmationText;

    public BoardListPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 9);
    }

    @Override
    protected BoardEditPage createEditPage() {
        return new BoardEditPage(getDriver());
    }

    @Override
    protected BoardViewPage createViewPage() {
        return new BoardViewPage(getDriver());
    }

    public void clickBoardButton() {
        boardButton.click();
    }

    public String viewConfirmationText() {
        return confirmationText.getText();
    }

    public String[] getCreatedTime() {
        return timeSecondRow.getText().split(" ");
    }

}
