package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BoardPage extends BaseTablePage {

    @FindBy(xpath = "//div[@class = 'kanban-item']/div[2]")
    private WebElement boardRow;

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
    protected BoardEditPage createEditPage() {
        return new BoardEditPage(getDriver());
    }


    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public String getPendingText() {
        return boardRow.getText();
    }

    public int getPendingItemsCount() {
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

    public BoardPage moveFromPedingToOntrack() {
        getActions().dragAndDrop(pendingCardItems.get(0), onTrackKanbanItem).build().perform();
        return this;
    }

    public BoardPage moveFromOntrackToDone() {
        getActions().dragAndDrop(onTrackCardItem.get(0), doneKanbanItem).build().perform();
        return this;
    }

    public BoardPage moveFromDoneToOnTrack() {
        getActions().dragAndDrop(doneCardItem.get(0), onTrackKanbanItem).build().perform();
        return this;
    }

    public BoardPage moveFromOnTrackToPending() {
        getActions().dragAndDrop(onTrackCardItem.get(0), pendingKanbanItem).build().perform();
        return this;
    }
}


