package model.entity.table;

import model.base.EntityBaseTablePage;
import model.entity.edit.ChevronEditPage;
import model.entity.view.ChevronViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public final class ChevronPage extends EntityBaseTablePage<ChevronPage, ChevronEditPage, ChevronViewPage> {

    public ChevronPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr//td[4]//a")
    private List <WebElement> element;

    @Override
    protected ChevronEditPage createEditPage() {
        return new ChevronEditPage(getDriver());
    }

    @Override
    public List<String> getRow(int rowNumber) {
        return getRows().get(rowNumber).findElements(By.tagName("td")).stream()
                .map(WebElement::getText).collect(Collectors.toList()).subList(1, 7);
    }

    @Override
    protected ChevronViewPage createViewPage() {
        return new ChevronViewPage(getDriver());
    }

    public ChevronPage clickRowToView(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
        return new ChevronPage(getDriver());
    }

    public List<String> getColumn() {
        List<WebElement> list = getDriver().findElements(By.xpath("//span[@class='pa-view-field']"));
        return list.stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public ChevronPage orderBy() {
        getDriver().findElement(By.xpath("//a[@class='nav-link active']")).click();
        return this;

    }

    public ChevronPage drugUp() throws InterruptedException {
        getDriver().findElement(By.xpath("//ul[@role='tablist']/li[2]")).click();
        WebElement bottom = getDriver().findElement(By.xpath("//tbody/tr[4]"));
        WebElement up = getDriver().findElement(By.xpath("//tr[@id='customId_0']"));
        Actions act = new Actions(getDriver());
        act.dragAndDrop(bottom, up).build().perform();
        return this;
    }

    public String getCellData() {
        return getDriver().findElement(By.xpath("//tr[@data-index='4']/td[3]")).getText();
    }

    public int getSum() {
        int sum = 0;
        for (WebElement li1 : element) {
            String s = li1.getText().replace("(", "");
            sum += Integer.parseInt(s.replace(")", ""));
        } return sum;
    }

    public int getAvr() {
        int avrSum = getSum()/ element.size();
        return avrSum;
    }
}
