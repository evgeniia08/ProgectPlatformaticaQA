package model.entity.table;

import model.BaseTablePage;
import model.entity.edit.PlaceholderEdit1Page;
import model.entity.edit.PlaceholderEditPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import runner.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

public class Placeholder1Page extends BaseTablePage<Placeholder1Page, PlaceholderEdit1Page> {

    @FindBy(xpath = "//i[contains(text(),'create_new_folder')]")
    private WebElement newFolderButton;

    @FindBy(xpath = "//tr/td[2]/a/div")
    WebElement el1;

    @FindBy(xpath = "//tr/td[3]/a/div")
    WebElement el2;

    @FindBy(xpath = "//tr/td[4]/a/div")
    WebElement el3;

    @FindBy(xpath = "//tr/td[5]/a/div")
    WebElement el4;

    @FindBy(xpath = "//tr[@data-index='0']//button/i[text()='menu']")
    WebElement actions;

    @FindBy(xpath = "//tr[@data-index='0']//div//li/a[text()='delete']")
    WebElement delete;

    public Placeholder1Page(WebDriver driver) {
        super(driver);
    }

    public PlaceholderEditPage createNewRecord(){
        newFolderButton.click();

        return new PlaceholderEditPage(getDriver());
    }

    public PlaceholderEdit1Page goPlaceholderEdit1Page() {
        newFolderButton.click();
        return new PlaceholderEdit1Page(getDriver());
    }


    @Override
    protected PlaceholderEdit1Page createEditPage() {
        return new PlaceholderEdit1Page(getDriver());
    }

    public Placeholder1Page verify (String stringValue, String textValue, String integerValue, String decimalValue) {
        Assert.assertEquals(el1.getText(), stringValue);
        Assert.assertEquals(el2.getText(), textValue);
        Assert.assertEquals(el3.getText(), integerValue);
        Assert.assertEquals(el4.getText(), decimalValue);
        return this;
    }

    public List<WebElement> newRecordElements() {
        List<WebElement> listOfElements = new ArrayList<>();
        listOfElements.add(el1);
        listOfElements.add(el2);
        listOfElements.add(el3);
        listOfElements.add(el4);
        return listOfElements;
    }

    public void deleteRecord(){
        ProjectUtils.click(getDriver(), actions);
        ProjectUtils.click(getDriver(), delete);
    }
}
