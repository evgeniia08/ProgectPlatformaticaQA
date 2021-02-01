package model.entity.common;

import com.beust.jcommander.Strings;
import model.base.EntityBaseTablePage;
import model.entity.edit.BoardEntityBaseEditPage;
import model.entity.table.BoardListPageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BoardPageEntityBase extends EntityBaseTablePage {

    @FindBy(xpath = "//div[@class = 'kanban-item']/div[2]")
    private WebElement boardRow;

    @FindBy(css = "div[data-id=Pending] main.kanban-drag")
    private WebElement kanbanPendingContainer;

    @FindBy(xpath = "//div[@data-id='Pending']//div[@class='kanban-item']")
    private List<WebElement> pendingCardItems;

    @FindBy(xpath = "//div[@data-id='On track']//div[@class='kanban-item']")
    private List<WebElement> onTrackCardItem;

    @FindBy(xpath = "//div[@data-id='Done']//div[@class='kanban-item']")
    private List<WebElement> doneCardItem;

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

    @Override
    protected BoardEntityBaseEditPage createEditPage() {
        return new BoardEntityBaseEditPage(getDriver());
    }


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

    public BoardEntityBaseEditPage clickNewFolder() {
        buttonNew.click();
        return new BoardEntityBaseEditPage(getDriver());
    }

    public BoardListPageEntityBase clickListButton() {
        listButton.click();
        return new BoardListPageEntityBase(getDriver());
    }

    public BoardPageEntityBase moveFromPedingToOntrack() {
        getActions().dragAndDrop(pendingCardItems.get(0), onTrackKanbanItem).build().perform();
        return this;
    }

    public BoardPageEntityBase moveFromOntrackToDone() {
        getActions().dragAndDrop(onTrackCardItem.get(0), doneKanbanItem).build().perform();
        return this;
    }

    public BoardPageEntityBase moveFromDoneToOnTrack() {
        getActions().dragAndDrop(doneCardItem.get(0), onTrackKanbanItem).build().perform();
        return this;
    }

    public BoardPageEntityBase moveFromOnTrackToPending() {
        getActions().dragAndDrop(onTrackCardItem.get(0), pendingKanbanItem).build().perform();
        return this;
    }
}


