package model.portal.common;

import model.base.PortalBaseIndexPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MarketplacePage extends PortalBaseIndexPage {

    public MarketplacePage(WebDriver driver) {
        super(driver);
    }

    public ItemDetailsPage clickItemByName(String itemName) {
        By itemLocator = By.xpath(String.format("//a[text()='%s']", itemName));
        getWait().until(ExpectedConditions.elementToBeClickable(itemLocator)).click();
        return new ItemDetailsPage(getDriver());
    }

    public String getItemPriceByName(String itemName) {
        By itemPriceLocator = By.xpath(String
                .format("//a[text()='%s']/ancestor::div[@class='card']//div[@class='price']", itemName));
        String price = getWait().until(ExpectedConditions.visibilityOfElementLocated(itemPriceLocator)).getText();
        return price.substring(price.indexOf("$") + 1).trim();
    }
}
