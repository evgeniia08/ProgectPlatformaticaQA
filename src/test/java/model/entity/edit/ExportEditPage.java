package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.ExportPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static runner.ProjectUtils.fill;

public final class ExportEditPage extends EntityBaseEditPage<ExportPage> {

    @FindBy(id = "int")
    private WebElement inputInt;

    @Override
    protected ExportPage createPage() {
        return new ExportPage(getDriver());
    }

    public ExportEditPage(WebDriver driver) {
        super(driver);
    }

    public ExportEditPage fillInt (String int_) {
        fill(getWait(),inputInt,int_);
        return this;
    }
}
