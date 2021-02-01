package model.base;

import model.entity.common.ErrorPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import runner.ProjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PortalBaseEditPage<T> extends PortalBaseIndexPage {

    @FindBy(css = "button[id*='save']")
    protected WebElement saveButton;

    @FindBy(css = "button[id*='draft']")
    protected WebElement saveDraftButton;

    @FindBy(xpath = "//button[text() = 'Cancel']")
    protected WebElement cancelButton;

    @FindBy(css = "input[type=text]")
    private List<WebElement> inputFields;

    public PortalBaseEditPage(WebDriver driver) {
        super(driver);
    }

    protected abstract T createPortalPage();

    public T clickSaveButton() {
        ProjectUtils.scroll(getDriver(), saveButton);
        ProjectUtils.click(getWait(), saveButton);
        return createPortalPage();
    }

    public T clickSaveDraftButton() {
        ProjectUtils.scroll(getDriver(), saveButton);
        ProjectUtils.click(getWait(), saveDraftButton);
        return createPortalPage();
    }

    public ErrorPage clickSaveButtonErrorExpected() {
        ProjectUtils.scroll(getDriver(), saveButton);
        ProjectUtils.click(getWait(), saveButton);
        return new ErrorPage(getDriver());
    }

    public ErrorPage clickSaveDraftButtonErrorExpected() {
        ProjectUtils.scroll(getDriver(), saveButton);
        ProjectUtils.click(getWait(), saveDraftButton);
        return new ErrorPage(getDriver());
    }

    public T clickCancelButton() {
        ProjectUtils.scroll(getDriver(), cancelButton);
        ProjectUtils.click(getWait(), cancelButton);
        return createPortalPage();
    }

    public List<String> getInputValues() {
        return inputFields.stream().map(e -> e.getAttribute("value")).collect(Collectors.toList());
    }
}
