package test.entity;

import model.entity.edit.FootersEditPage;
import model.entity.common.MainPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;
import runner.type.Run;
import runner.type.RunType;
import java.util.List;

import static org.testng.Assert.assertThrows;
import static runner.ProjectUtils.fill;

@Run(run = RunType.Multiple)

@Ignore
public class EntityFootersTest extends BaseTest {

    private static final String INT_VALUE_1 = "10";
    private static final String INT_VALUE_2 = "-1";
    private static final String INT_VALUE_3 = "5";

    private static final String DEC_VALUE_1 = "2.0";
    private static final String DEC_VALUE_2 = "-0.1";
    private static final String DEC_VALUE_3 = "1";

    private static final String STR_VALUE_1 = "AAA";
    private static final String STR_VALUE_2 = "aaa";
    private static final String STR_VALUE_3 = "2AAA";

    private static final String TXT_VALUE_1 = "bbb";
    private static final String TXT_VALUE_2 = "BBB";
    private static final String TXT_VALUE_3 = "2BBB";

    private static final String ROW_NUM_1 = "1";
    private static final String ROW_NUM_2 = "2";
    private static final String ROW_NUM_3 = "3";

    private static final String SUM_TABLE_NUM = "70";
    private static final String MIN_TABLE_NUM = "71";
    private static final String MAX_TABLE_NUM = "72";
    private static final String COUNT_TABLE_NUM = "73";
    private static final String AVE_TABLE_NUM = "74";

    private static final String SUM_RESULT = "14,2.9";
    private static final String SUM_RESULT_EDITED = "16,2.9";
    private static final String SUM_RESULT_DELETED_ROW = "9,1.9";

    private static final String MIN_RESULT = "-1,-0.1,2AAA,2BBB,,";
    private static final String MIN_RESULT_EDITED = "-1,,2AAA,2BBB,,";
    private static final String MIN_RESULT_DELETED_ROW = "-1,-0.1,AAA,BBB,";

    private static final String MAX_RESULT = "5,2.0,aaa,bbb,,";
    private static final String MAX_RESULT_EDITED = "5,2.0,abc,bbb,,";
    private static final String MAX_RESULT_DELETED_ROW = "10,2.0,aaa,bbb,,";

    private static final String COUNT_RESULT = "3";
    private static final String COUNT_RESULT_EDITED = "2";

    private static final String AVE_RESULT = "4.67,0.97";
    private static final String AVE_RESULT_EDITED = "4.67,0.80";
    private static final String AVE_RESULT_DELETED_ROW = "7.5,1.25";


    private void createNewRow(String tableId) {

        getWebDriverWait().until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@data-table_id=" + tableId + "]"))).click();
    }

    private void populateSumAveRow(WebDriver driver, String intValue, String decValue, String rowNum, String tableNum) {

        WebDriverWait wait = getWebDriverWait();
        createNewRow(tableNum);

        driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-int")).clear();
        fill(wait, driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-int")), intValue);

        driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-decimal")).clear();
        fill(wait, driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-decimal")), decValue);

    }

    private void populateMinMaxRow(WebDriver driver, String intValue, String decValue, String strValue, String txtValue, String rowNum, String tableNum) {

        WebDriverWait wait = getWebDriverWait();
        createNewRow(tableNum);

        driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-int")).clear();
        fill(wait, driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-int")), intValue);

        driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-decimal")).clear();
        fill(wait, driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-decimal")), decValue);

        driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-string"));
        fill(wait, driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-string")), strValue);

        driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-text"));
        fill(wait, driver.findElement(By.id("t-" + tableNum + "-r-" + rowNum + "-text")), txtValue);
    }

    private void assertTableValues(WebDriver driver, String[] values, String xpath) {

        List<WebElement> rows = driver.findElements(new By.ByXPath(xpath));
        Assert.assertEquals(rows.size(), 1);
        List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
        for (int i = 0; i < columns.size(); i++)
            Assert.assertEquals(columns.size(), values.length);

        for (int i = 0; i < columns.size(); i++) {
            if (values[i] != null) {
                Assert.assertEquals(columns.get(i).getText(), values[i]);
            }
        }
    }

    @Test
    public void sumFooter() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        WebElement createNewFooter = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFooter.click();

        populateSumAveRow(driver, INT_VALUE_1, DEC_VALUE_1, ROW_NUM_1, SUM_TABLE_NUM);
        populateSumAveRow(driver, INT_VALUE_2, DEC_VALUE_2, ROW_NUM_2, SUM_TABLE_NUM);
        populateSumAveRow(driver, INT_VALUE_3, DEC_VALUE_3, ROW_NUM_3, SUM_TABLE_NUM);

        WebElement randomClick = driver.findElement(By.id("t-70-r-3-control"));
        randomClick.click();

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-70-control"), SUM_RESULT));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='sum_control']"), SUM_RESULT));

        //change last record
        WebElement inputInt = driver.findElement(By.id("t-70-r-3-int"));
        inputInt.clear();
        inputInt.sendKeys("7");
        randomClick.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-70-control"), SUM_RESULT_EDITED));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='sum_control']"), SUM_RESULT_EDITED));

        //delete last row
        WebElement deleteRow = driver.findElement(By.xpath("//i[@data-row='3']"));
        deleteRow.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-70-control"), SUM_RESULT_DELETED_ROW));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='sum_control']"), SUM_RESULT_DELETED_ROW));
    }

    @Test
    public void minFooter() throws InterruptedException {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        WebElement createNewFooter = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFooter.click();

        //Values of Date and Datetime fields are NOT displayed yet in the result fields, so not passing them
        populateMinMaxRow(driver, INT_VALUE_1, DEC_VALUE_1, STR_VALUE_1, TXT_VALUE_1, ROW_NUM_1, MIN_TABLE_NUM);
        populateMinMaxRow(driver, INT_VALUE_2, DEC_VALUE_2, STR_VALUE_2, TXT_VALUE_2, ROW_NUM_2, MIN_TABLE_NUM);
        populateMinMaxRow(driver, INT_VALUE_3, DEC_VALUE_3, STR_VALUE_3, TXT_VALUE_3, ROW_NUM_3, MIN_TABLE_NUM);

        WebElement randomClick = driver.findElement(By.id("t-71-r-1-control"));
        randomClick.click();

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-71-control"), MIN_RESULT));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='min_control']"), MIN_RESULT));

        //change last record
        WebElement inputDec = driver.findElement(By.id("t-71-r-3-decimal"));
        inputDec.clear();
        inputDec.sendKeys("2.1");
        randomClick.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-71-control"), MIN_RESULT_EDITED));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='min_control']"), MIN_RESULT_EDITED));

        //delete last row
        WebElement deleteRow = driver.findElement(By.xpath("//i[@data-row='3']"));
        deleteRow.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-71-control"), MIN_RESULT_DELETED_ROW));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='min_control']"), MIN_RESULT_DELETED_ROW));
    }

    @Test
    public void maxFooter() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        WebElement createNewFooter = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFooter.click();

        //Values of Date and Datetime fields are NOT displayed yet in the result fields, so not passing them
        populateMinMaxRow(driver, INT_VALUE_1, DEC_VALUE_1, STR_VALUE_1, TXT_VALUE_1, ROW_NUM_1, MAX_TABLE_NUM);
        populateMinMaxRow(driver, INT_VALUE_2, DEC_VALUE_2, STR_VALUE_2, TXT_VALUE_2, ROW_NUM_2, MAX_TABLE_NUM);
        populateMinMaxRow(driver, INT_VALUE_3, DEC_VALUE_3, STR_VALUE_3, TXT_VALUE_3, ROW_NUM_3, MAX_TABLE_NUM);

        WebElement randomClick = driver.findElement(By.id("t-72-r-1-control"));
        randomClick.click();

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-72-control"), MAX_RESULT));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='max_control']"), MAX_RESULT));

        //change last record
        WebElement inputStr = driver.findElement(By.id("t-72-r-3-string"));
        inputStr.clear();
        inputStr.sendKeys("abc");
        randomClick.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-72-control"), MAX_RESULT_EDITED));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='max_control']"), MAX_RESULT_EDITED));

        //delete last row
        WebElement deleteRow = driver.findElement(By.xpath("//i[@data-row='3']"));
        deleteRow.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-72-control"), MAX_RESULT_DELETED_ROW));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='max_control']"), MAX_RESULT_DELETED_ROW));
    }

    @Ignore
    @Test
    public void countFooter() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        WebElement createNewFooter = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFooter.click();

        //create three rows
        createNewRow(COUNT_TABLE_NUM);
        createNewRow(COUNT_TABLE_NUM);
        createNewRow(COUNT_TABLE_NUM);

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-73-control"), COUNT_RESULT));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='count_control']"), COUNT_RESULT));

        //delete last row
        WebElement deleteRow = driver.findElement(By.xpath("//i[@data-row='3']"));
        deleteRow.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-73-control"), COUNT_RESULT_EDITED));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='count_control']"), COUNT_RESULT_EDITED));
    }


    @Test
    public void aveFooter() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        WebElement createNewFooter = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFooter.click();

        populateSumAveRow(driver, INT_VALUE_1, DEC_VALUE_1, ROW_NUM_1, AVE_TABLE_NUM);
        populateSumAveRow(driver, INT_VALUE_2, DEC_VALUE_2, ROW_NUM_2, AVE_TABLE_NUM);
        populateSumAveRow(driver, INT_VALUE_3, DEC_VALUE_3, ROW_NUM_3, AVE_TABLE_NUM);

        WebElement randomClick = driver.findElement(By.id("t-74-r-3-control"));
        randomClick.click();

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-74-control"), AVE_RESULT));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='average_control']"), AVE_RESULT));

        //change last record
        WebElement inputDec = driver.findElement(By.id("t-74-r-3-decimal"));
        inputDec.clear();
        inputDec.sendKeys("0.5");
        randomClick.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-74-control"), AVE_RESULT_EDITED));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='average_control']"), AVE_RESULT_EDITED));

        //delete last row
        WebElement deleteRow = driver.findElement(By.xpath("//i[@data-row='2']"));
        deleteRow.click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-74-control"), AVE_RESULT_DELETED_ROW));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='average_control']"), AVE_RESULT_DELETED_ROW));
    }

    @Test
    public void saveFooter() throws InterruptedException {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        WebElement createNewFooter = driver.findElement(By.xpath("//i[contains(text(),'create_new_folder')]"));
        createNewFooter.click();

        populateSumAveRow(driver, INT_VALUE_1, DEC_VALUE_1, ROW_NUM_1, SUM_TABLE_NUM);
        populateMinMaxRow(driver, INT_VALUE_1, DEC_VALUE_1, STR_VALUE_1, TXT_VALUE_1, ROW_NUM_1, MIN_TABLE_NUM);
        populateMinMaxRow(driver, INT_VALUE_1, DEC_VALUE_1, STR_VALUE_1, TXT_VALUE_1, ROW_NUM_1, MAX_TABLE_NUM);
        createNewRow(COUNT_TABLE_NUM);
        populateSumAveRow(driver, INT_VALUE_1, DEC_VALUE_1, ROW_NUM_1, AVE_TABLE_NUM);

        WebElement randomClick = driver.findElement(By.id("t-74-r-1-control"));
        randomClick.click();

        //Needed to add sleep here otherwise average decimal value
        //is NOT displayed on next page (expected 10,2.0 but found 10,NaN) and adding wait at this line doesn't help
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("pa-all-entities-table")));

        String[] expectedValues = {null, "10,2", "10,2.0,AAA,bbb,,", "10,2.0,AAA,bbb,,", "1", "10,2", null};

        assertTableValues(driver, expectedValues, "//tbody/tr");
    }

    @Test(dependsOnMethods = "saveFooter")
    public void viewFooter() throws InterruptedException {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        driver.findElement(By.xpath("//i[normalize-space()='menu']")).click();
        wait.until(TestUtils.movingIsFinished(By.xpath("//a[text()='view']"))).click();

        //check that impossible to edit data
        assertThrows(ElementNotInteractableException.class,
                () -> driver.findElement(By.xpath("//*[@id='pa-all-entities-table']/tbody/tr/td[2]")).sendKeys("5")
        );

        //check that values in each table stay as expected
        String[] sumValues = {null, "10", "2.00", null};
        assertTableValues(driver, sumValues, "//h4[contains( text(), 'Sum Ftrs')]/parent::div//table/tbody/tr");

        String[] minValues = {null, "10", "2.00", "AAA", "bbb", null, null, null};
        assertTableValues(driver, minValues, "//h4[contains( text(), 'Min Ftrs')]/parent::div//table/tbody/tr");

        String[] maxValues = {null, "10", "2.00", "AAA", "bbb", null, null, null};
        assertTableValues(driver, maxValues, "//h4[contains( text(), 'Max Ftrs')]/parent::div//table/tbody/tr");

        String[] countValues = {null, "0", null};
        assertTableValues(driver, countValues, "//h4[contains(text(), 'Count Ftrs')]/parent::div//table/tbody/tr");

        String[] aveValues = {null, "10", "2.00", null};
        assertTableValues(driver, aveValues, "//h4[contains(text(), 'Average Ftrs')]/parent::div//table/tbody/tr");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Sum control')]/parent::div//span")));

        String[] expectedLabels = {"Sum control", "Min control", "Max control", "Count control", "Average control"};
        String[] expectedValues = {"10,2", "10,2.0,AAA,bbb,,", "10,2.0,AAA,bbb,,", "1", "10,2"};
        WebElement containingDiv = driver.findElement(By.xpath("//label[contains(text(), 'Sum control')]/parent::div"));
        List<WebElement> labels = containingDiv.findElements(By.tagName("label"));
        List<WebElement> divSpans = containingDiv.findElements(By.xpath("//div/span"));
        Assert.assertEquals(labels.size(), divSpans.size());
        Assert.assertEquals(labels.size(), expectedLabels.length);

        for (int i = 0; i < expectedLabels.length; i++) {
            Assert.assertEquals(labels.get(i).getText(), expectedLabels[i]);
            Assert.assertEquals(divSpans.get(i).getText(), expectedValues[i]);
        }
    }

    @Test(dependsOnMethods = "viewFooter")
    public void editFooter() throws InterruptedException {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        driver.findElement(By.xpath("//i[normalize-space()='menu']")).click();
        wait.until(TestUtils.movingIsFinished(By.xpath("//a[text()='edit']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-table_id=70]"))).click();

        populateSumAveRow(driver, "100", "0.23", ROW_NUM_2, SUM_TABLE_NUM);
        WebElement randomClick = driver.findElement(By.id("t-70-r-1-control"));
        randomClick.click();

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("f-70-control"), "110,2.23"));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//input[@data-field_name='sum_control']"), "110,2.23"));

        //Needed to add sleep here otherwise average decimal value
        //is NOT displayed on next page (expected 10,2.0 but found 10,NaN) and adding wait at this line doesn't help
        Thread.sleep(3000);

        WebElement saveButton = driver.findElement(By.xpath("//button[@id='pa-entity-form-save-btn']"));
        saveButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pa-all-entities-table")));

        String[] expectedValues = {null, "110,2.23", "10,2.0,AAA,bbb,,", "10,2.0,AAA,bbb,,", "1", "10,2", null};
        assertTableValues(driver, expectedValues, "//tbody/tr");
    }


    @Test(dependsOnMethods = "editFooter")
    public void deleteFooter() {

        WebDriver driver = getDriver();
        WebDriverWait wait = getWebDriverWait();

        WebElement footerTab = driver.findElement(By.xpath("//p[contains(text(),'Footers')]"));
        footerTab.click();

        driver.findElement(By.xpath("//i[normalize-space()='menu']")).click();
        wait.until(TestUtils.movingIsFinished(By.xpath("//a[text()='delete']"))).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class, 'card-body')]")).getText(), "");
    }

    @Test
    public void createNewRecord() throws InterruptedException {

        final int int_ = 1000;
        final double decimal = 10.5;
        final String firstRowControl = String.format("%d,%.1f", int_, decimal);

        FootersEditPage footersEditPage = new MainPage(getDriver())
                .clickMenuFooters()
                .clickNewFolder()
                .clickPlusSumButton()
                .fillSumFtrsRowData(0, int_, decimal)
                .waitSumFtrsToBe(firstRowControl)
                .waitSumFtrsControlToBe(firstRowControl);
    }
}
