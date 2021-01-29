package model;

import org.openqa.selenium.WebDriver;

public class FootersPage extends BaseTablePage<FootersPage, FootersEditPage> {

    public FootersPage(WebDriver driver){
        super(driver);
    }

    @Override
    protected FootersEditPage createEditPage(){
        return new FootersEditPage(getDriver());
    }
}
