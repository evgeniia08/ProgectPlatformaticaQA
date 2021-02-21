package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ReadOnlyEditPage;
import model.entity.view.ReadOnlyViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReadOnlyPage extends EntityBaseTablePage<ReadOnlyPage, ReadOnlyEditPage, ReadOnlyViewPage> {

    private static final By BY_CARD_BODY = By.className("card-body");
    private static final By BY_LAST = By.xpath("//tr[last()]");

    @FindBy(xpath = "//a[@href='index.php?action=action_list&list_type=table&entity_id=6']")
    private WebElement buttonList;

    @FindBy(xpath = "//i[contains(text(),'format_line_spacing')]")
    private WebElement buttonOrder;

    @Override
    protected ReadOnlyEditPage createEditPage() {
        return new ReadOnlyEditPage(getDriver());
    }

    @Override
    protected ReadOnlyViewPage createViewPage() {
        return new ReadOnlyViewPage(getDriver());
    }

    public ReadOnlyPage(WebDriver driver) {
        super(driver);
    }

    public String getCardBodyText() {
        return getDriver().findElement(BY_CARD_BODY).getText();
    }

    public ReadOnlyPage clickButtonOrder() {
        buttonOrder.click();
        return this;
    }

    public ReadOnlyPage clickButtonList() {
        buttonList.click();
        return this;
    }

    public String getLastRowText() {
        WebElement lastRow = getDriver().findElement(BY_LAST);
        return lastRow.getText();
    }
}
