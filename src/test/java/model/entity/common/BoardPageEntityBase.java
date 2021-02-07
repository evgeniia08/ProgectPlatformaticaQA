package model.entity.common;

import com.beust.jcommander.Strings;
import model.base.BaseIndexPage;
import model.entity.edit.BoardEditPage;
import model.entity.table.BoardListPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BoardPageEntityBase extends BaseIndexPage {

    @FindBy(xpath = "//div[@class = 'kanban-item']/div[2]")
    private WebElement boardRow;

    @FindBy(css = "div[data-id=Pending] main.kanban-drag")
    private WebElement kanbanPendingContainer;

    @FindBy(xpath = "//div[@data-id='Pending']//div[@class='kanban-item']/div[1]")
    private List<WebElement> pendingCardItems;

    @FindBy(xpath = "//div[@data-id='On track']//div[@class='kanban-item']/div[1]")
    private List<WebElement> onTrackCardItems;

    @FindBy(xpath = "//div[@data-id='Done']//div[@class='kanban-item']/div[1]")
    private List<WebElement> doneCardItems;

    @FindBy(xpath = "//div[1]/main[@class='kanban-drag']")
    private WebElement pendingKanbanItem;

    @FindBy(xpath = "//div[2]/main[@class='kanban-drag']")
    private WebElement onTrackKanbanItem;

    @FindBy(xpath = "//div[3]/main[@class='kanban-drag']")
    private WebElement doneKanbanItem;

    @FindBy(xpath = "//i[text() = 'create_new_folder']")
    private WebElement buttonNew;

    @FindBy(xpath = "//a[contains(@href, '31')]/i[text()='list']")
    private WebElement listButton;

    public BoardPageEntityBase(WebDriver driver) {
        super(driver);
    }

    public String getPendingText() {
        return boardRow.getText();
    }

    public int getPendingItemsCount() {
        if (Strings.isStringEmpty(kanbanPendingContainer.getText())) {
            return 0;
        }
        return pendingCardItems.size();
    }

    public BoardEditPage clickNewFolder() {
        buttonNew.click();
        return new BoardEditPage(getDriver());
    }

    public BoardListPage clickListButton() {
        listButton.click();
        return new BoardListPage(getDriver());
    }

    public BoardPageEntityBase moveFromPedingToOntrack() {
        getActions().dragAndDrop(pendingCardItems.get(0), onTrackKanbanItem).build().perform();
        return this;
    }

    public BoardPageEntityBase moveFromOntrackToDone() {
        getActions().dragAndDrop(onTrackCardItems.get(0), doneKanbanItem).build().perform();
        return this;
    }

    public BoardPageEntityBase moveFromDoneToOnTrack() {
        getActions().dragAndDrop(doneCardItems.get(0), onTrackKanbanItem).build().perform();
        return this;
    }

    public BoardPageEntityBase moveFromOnTrackToPending() {
        getActions().dragAndDrop(onTrackCardItems.get(0), pendingKanbanItem).build().perform();
        return this;
    }
}
