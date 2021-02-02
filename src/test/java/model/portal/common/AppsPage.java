package model.portal.common;


import model.base.EntityBaseTablePage;
import model.portal.edit.AppsEditPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

public class AppsPage extends EntityBaseTablePage<AppsPage, AppsEditPage> {

    @FindBy(xpath = "//div[text() = 'Name']")
    private WebElement columnName;

    public void clickColumnHeader(String column) {
        ProjectUtils.click(getDriver(),getDriver().findElement(By.xpath(String.format("//div[text() = '%s']", column))));

    }

    @Override
    protected AppsEditPage createEditPage() {
        return new AppsEditPage(getDriver());
    }

    public AppsPage(WebDriver driver) {
        super(driver);
    }


}
