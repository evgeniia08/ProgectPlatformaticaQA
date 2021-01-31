package model.entity.edit;

import model.BaseEmbeddedPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultEmbeddedEditPage extends BaseEmbeddedPage<DefaultEmbeddedEditPage> {

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

    public DefaultEmbeddedEditPage(WebDriver driver) {
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

        String[] fields = {STRING, TEXT, INT, DECIMAL, DATE, DATE_TIME};
        String[] data = {string_, text, int_, decimal, date, dateTime};

        WebElement row = getRows().get(rowNumber);

        for (int i = 0; i < fields.length; i++) {
           ProjectUtils.fill(getWait(), row.findElement(By.id(String.format(fields[i], rowNumber+1))), data[i]);
        }

        Select userSelect = new Select(row.findElement(By.xpath(String.format(DROPDOWN, rowNumber+1))));
        userSelect.selectByVisibleText(user);
    }
}
