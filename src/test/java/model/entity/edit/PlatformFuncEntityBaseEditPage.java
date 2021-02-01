package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.PlatformFuncPageEntityBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static runner.ProjectUtils.fill;

public class PlatformFuncEntityBaseEditPage extends EntityBaseEditPage<PlatformFuncPageEntityBase> {

    @FindBy(css = "input#last_int")
    private WebElement inputLastInt;

    @FindBy(css = "input#last_string")
    private WebElement inputLastString;

    @FindBy(css = "input#constant")
    private WebElement inputConstant;


    public PlatformFuncEntityBaseEditPage(WebDriver driver) {
        super(driver);
    }

    protected PlatformFuncPageEntityBase createPage() {
        return new PlatformFuncPageEntityBase(getDriver());
    }

    public String getLastInt() {
        return inputLastInt.getAttribute("value");
    }

    public String getLastString() {
        return inputLastString.getAttribute("value");
    }

    public String getConstant() {
        return inputConstant.getAttribute("value");
    }

    public PlatformFuncEntityBaseEditPage fillLastInt(String int_) {
        fill(getWait(), inputLastInt, int_);
        return this;
    }

    public PlatformFuncEntityBaseEditPage fillLastString(String text) {
        fill(getWait(), inputLastString, text);
        return this;
    }

    public PlatformFuncEntityBaseEditPage fillLastConstant(String text) {
        fill(getWait(), inputConstant, text);
        return this;
    }

    public PlatformFuncEntityBaseEditPage fillValues(String int_, String text, String constant) {
        fillLastInt(int_);
        fillLastString(text);
        fillLastConstant(constant);
        return this;
    }

    public PlatformFuncEntityBaseEditPage fillValues(String int_, String text) {
        fillLastInt(int_);
        fillLastString(text);
        return this;
    }
}
