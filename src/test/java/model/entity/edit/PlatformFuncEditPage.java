package model.entity.edit;

import model.base.EntityBaseEditPage;
import model.entity.table.PlatformFuncPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static runner.ProjectUtils.fill;

public class PlatformFuncEditPage extends EntityBaseEditPage<PlatformFuncPage> {

    @FindBy(css = "input#last_int")
    private WebElement inputLastInt;

    @FindBy(css = "input#last_string")
    private WebElement inputLastString;

    @FindBy(css = "input#constant")
    private WebElement inputConstant;


    public PlatformFuncEditPage(WebDriver driver) {
        super(driver);
    }

    protected PlatformFuncPage createPage() {
        return new PlatformFuncPage(getDriver());
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

    public PlatformFuncEditPage fillLastInt(String int_) {
        fill(getWait(), inputLastInt, int_);
        return this;
    }

    public PlatformFuncEditPage fillLastString(String text) {
        fill(getWait(), inputLastString, text);
        return this;
    }

    public PlatformFuncEditPage fillLastConstant(String text) {
        fill(getWait(), inputConstant, text);
        return this;
    }

    public PlatformFuncEditPage fillValues(String int_, String text, String constant) {
        fillLastInt(int_);
        fillLastString(text);
        fillLastConstant(constant);
        return this;
    }

    public PlatformFuncEditPage fillValues(String int_, String text) {
        fillLastInt(int_);
        fillLastString(text);
        return this;
    }

    public PlatformFuncEditPage clickSaveButtonNoRedirectionExpected() {
        getActions().moveToElement(saveButton).click().build().perform();
        return this;
    }

    public boolean isFocusOnLastIntField() {
        return getDriver().switchTo().activeElement().equals(inputLastInt);
    }
}
