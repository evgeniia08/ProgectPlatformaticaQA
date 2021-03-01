package model.entity.edit;

import model.base.EntityBaseEditExtPage;
import model.entity.table.ImportValuesPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import runner.ProjectUtils;

public class ImportValuesEditPage extends EntityBaseEditExtPage<ImportValuesPage, ImportValuesEditPage> {

    public ImportValuesEditPage(WebDriver driver) {
        super(driver);
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

    @Override
    protected ImportValuesPage createPage() {
        return new ImportValuesPage(getDriver());
    }

    @Override
    protected ImportValuesEditPage createEditPage() {
        return new ImportValuesEditPage(getDriver());
    }
}