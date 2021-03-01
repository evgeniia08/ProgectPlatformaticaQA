package model.entity.view;

import model.base.EntityBaseViewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentViewPage extends EntityBaseViewPage {

    @FindBy(xpath = "//div[2]//div[1]/div[1]/div[1]/div[1]/div[2]")
    private WebElement tab;

    public ParentViewPage(WebDriver driver) {
        super(driver);
    }

    public String viewTitle(){
        return tab.findElement(By.xpath("//label[text()='String']/../div[1]//span")).getText();
    }

    public String viewComment(){
        return tab.findElement(By.xpath("//label[text()='String']/../div[2]//span")).getText();
    }

    public String viewInt(){
        return tab.findElement(By.xpath("//label[text()='String']/../div[3]//span")).getText();
    }

    public String viewDecimal(){
        return tab.findElement(By.xpath("//label[text()='String']/../div[4]//span")).getText();
    }

    public String viewDate() {
        return tab.findElement(By.xpath("//label[text()='String']/../div[5]//span")).getText();
    }

    public String viewUser(){
            return tab.findElement(By.xpath("//div[@class='row']//p")).getText();
    }
}
