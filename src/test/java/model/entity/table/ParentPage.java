package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ParentEditPage;
import model.entity.view.ParentViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public final class ParentPage extends EntityBaseTablePage<ParentPage, ParentEditPage, ParentViewPage> {

    @FindBy(xpath = "//table/tbody/tr/td[9]")
    private WebElement demoUser;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> rowList;

    @FindBy(xpath = "//table[@id = 'pa-all-entities-table']/tbody")
    private WebElement table;

    public ParentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ParentEditPage createEditPage() {
        return new ParentEditPage(getDriver());
    }

    @Override
    protected ParentViewPage createViewPage() {
        return new ParentViewPage(getDriver());
    }

    public String getDefaultUser() { return demoUser.getText(); }

    public String getTitleText(){
        return table.findElement(By.xpath("//tr/td[2]/a")).getText();
    }

    public String getCommentsText(){
        return table.findElement(By.xpath("//tr/td[3]/a")).getText();
    }

    public String getNumberText(){
        return table.findElement(By.xpath("//tr/td[4]/a")).getText();
    }

    public String getNumber1Text(){
        return table.findElement(By.xpath("//tr/td[5]/a")).getText();
    }

    public String getDataText(){
        return table.findElement(By.xpath("//tr/td[6]/a")).getText();
    }

    public ParentPage clickOrderParent() {
        getDriver().findElement(By.xpath("//a[@class='nav-link active']")).click();

        return this;
    }

    public ParentPage dragUp() {

        getDriver().findElement(By.xpath("//ul[@role='tablist']/li[2]")).click();
        WebElement down = getDriver().findElement(By.xpath("//tbody/tr[2]"));
        WebElement up = getDriver().findElement(By.xpath("//tbody/tr[1]"));
        Actions move = new Actions(getDriver());
        move.clickAndHold(down).moveToElement(up).release().build().perform();

        return this;
    }

    public String checkCell() {
        return getDriver().findElement(By.xpath("//tr[@data-index='0']/td[2]")).getText();
    }
}
