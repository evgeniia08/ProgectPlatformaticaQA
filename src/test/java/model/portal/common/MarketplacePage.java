package model.portal.common;

import model.base.PortalBaseIndexPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class MarketplacePage extends PortalBaseIndexPage {

    @FindBy(css = "div.card")
    private List<WebElement> allCards;

    @FindBy(css = "h4 a")
    private List<WebElement> allAppNames;

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

    public List<String> getAllAppNames() {
        return allAppNames.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
