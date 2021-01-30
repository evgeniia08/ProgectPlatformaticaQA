package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DefaultEmbeddedPage extends BaseEmbededPage<DefaultEmbeddedPage>{

    private static final String DATA_ROW = "data-row";
    private static final By BY_XPATH_TDS = By.tagName("td");
    private static final String STRING    = "t-11-r-%d-string";
    private static final String TEXT      = "t-11-r-%d-text";
    private static final String INT       = "t-11-r-%d-int";
    private static final String DECIMAL   = "t-11-r-%d-decimal";
    private static final String DATE      = "t-11-r-%d-date";
    private static final String DATE_TIME = "t-11-r-%d-datetime";
    private static final String USER      = "//select[@id='t-11-r-%d-user']/option[@value='0']";
    private static final String DROPDOWN  = "//select[@id='t-11-r-%d-user']";

    private void sendKeys(WebElement element, String newValue){
        element.clear();
        element.sendKeys(newValue);
        element.sendKeys("\t");
    }

    public DefaultEmbeddedPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getRow(int rowNumber) {
        List<String> result = new ArrayList<>();
        List<WebElement> cells = getRows().get(rowNumber).findElements(BY_XPATH_TDS);

        result.add(cells.get(1).findElement(By.tagName("input")).getAttribute(DATA_ROW));
        for (int i = 2; i < 8; i++ ) {
            result.add(cells.get(i).getText());
        }

        Select select = new Select(cells.get(10).findElement(By.tagName("select")));
        result.add(select.getFirstSelectedOption().getText());
        return result;
    }

    public void sendKeys(int rowNumber, String string_, String text, String int_,
                         String decimal, String date, String dateTime, String user) {
        WebElement row = getRows().get(rowNumber);

        sendKeys(row.findElement(By.id(String.format(STRING, rowNumber+1))), string_);
        sendKeys(row.findElement(By.id(String.format(TEXT, rowNumber+1))), text);
        sendKeys(row.findElement(By.id(String.format(INT, rowNumber+1))), int_);
        sendKeys(row.findElement(By.id(String.format(DECIMAL, rowNumber+1))), decimal);
        sendKeys(row.findElement(By.id(String.format(DATE, rowNumber+1))), date);
        sendKeys(row.findElement(By.id(String.format(DATE_TIME, rowNumber+1))), dateTime);

        Select userSelect = new Select(row.findElement(By.xpath(String.format(DROPDOWN, rowNumber+1))));
        userSelect.selectByVisibleText(user);
    }
}
