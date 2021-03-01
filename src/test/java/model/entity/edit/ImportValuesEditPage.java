package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.ImportValuesPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import runner.ProjectUtils;

public class ImportValuesEditPage extends EntityBaseEditPage<ImportValuesPage> {

    public ImportValuesEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected ImportValuesPage createPage() {
        return new ImportValuesPage(getDriver());
    }

    public ImportValuesEditPage sendKeys(String string) {
        ProjectUtils.fill(getWait(), inputString, string);

        return this;
    }

    public ImportValuesEditPage fillOutForm(String string, String text, String int_, String decimal) {
        inputString.sendKeys(string);
        inputText.sendKeys(text);
        inputInt.sendKeys(int_);
        inputDecimal.sendKeys(decimal);
        inputDate.click();
        inputDateTime.click();
        inputDateTime.sendKeys(Keys.ENTER);

        return this;
    }

    public String getDate() {
        return inputDate.getAttribute("value");
    }

    public String getDateTime() {
        return inputDateTime.getAttribute("value");
    }
}