package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.ExportPageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static runner.ProjectUtils.fill;

public final class ExportEntityBaseEditPage extends EntityBaseEditPage<ExportPageEntityBase> {

    @FindBy(id = "int")
    private WebElement inputInt;

    @Override
    protected ExportPageEntityBase createPage() {
        return new ExportPageEntityBase(getDriver());
    }

    public ExportEntityBaseEditPage(WebDriver driver) {
        super(driver);
    }

    public ExportEntityBaseEditPage fillInt (String int_) {
        fill(getWait(),inputInt,int_);
        return this;
    }
}
