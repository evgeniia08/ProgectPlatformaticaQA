package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.Chain1PageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.ProjectUtils;

public class Chain1EntityBaseEditPage extends EntityBaseEditPage<Chain1PageEntityBase> {

    @FindBy(id = "f1")
    WebElement f1;

    @FindBy(id = "f10")
    WebElement f10;

    @FindBy(id = "pa-entity-form-save-btn")
    WebElement saveButton;


    public Chain1EntityBaseEditPage(WebDriver driver) {
        super(driver);
    }
    @Override
    protected Chain1PageEntityBase createPage() {
        return new Chain1PageEntityBase(getDriver());
    }



    public Chain1EntityBaseEditPage inputInitialValue() {
        ProjectUtils.fill(getWait(), f1, "1");
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(f10, "value"));
        return this;
    }


}


