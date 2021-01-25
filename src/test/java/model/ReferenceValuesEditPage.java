package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static runner.ProjectUtils.fill;

public class ReferenceValuesEditPage extends BaseEditPage<ReferenceValuesPage> {

    @FindBy(id = "label")
    private WebElement inputLabel;

    @FindBy(id = "filter_1")
    private WebElement inputFilter_1;

    @FindBy(id = "filter_2")
    private WebElement inputFilter_2;

    public ReferenceValuesEditPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ReferenceValuesPage createPage() {
        return new ReferenceValuesPage(getDriver());
    }

    public ReferenceValuesEditPage fillLabel(String text) {
        fill(getWait(), inputLabel, text);
        return this;
    }

    public ReferenceValuesEditPage fillFilter_1(String text) {
        fill(getWait(), inputFilter_1, text);
        return this;
    }

    public ReferenceValuesEditPage fillFilter_2(String text) {
        fill(getWait(), inputFilter_2, text);
        return this;
    }

    public ReferenceValuesEditPage fillData(String label, String filter_1, String filter_2) {
        fillLabel(label);
        fillFilter_1(filter_1);
        fillFilter_2(filter_2);
        return this;
    }
}

