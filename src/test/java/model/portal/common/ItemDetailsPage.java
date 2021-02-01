package model.portal.common;

import model.base.PortalBaseIndexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemDetailsPage extends PortalBaseIndexPage {

    @FindBy(xpath = "//div[@class='card-body']//button/div")
    private WebElement buyNowButton;

    public ItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickBuyNowButton() {
        buyNowButton.click();
        return new CheckoutPage(getDriver());
    }
}
