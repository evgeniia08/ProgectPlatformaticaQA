package model.portal.common;

import model.base.PortalBaseIndexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends PortalBaseIndexPage {

    @FindBy(xpath = "//h4[contains(text(), 'Balance')]")
    private WebElement userBalance;

    @FindBy(xpath = "//button[text()='Get with credit']")
    private WebElement getWithCreditButton;

    @FindBy(css = "div.card-body b:nth-of-type(2)")
    private WebElement price;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public AfterPurchasePage clickGetWithCreditButton() {
        getWithCreditButton.click();
        return new AfterPurchasePage(getDriver());
    }

    public String getBalance() {
        String balance = userBalance.getText();
        return balance.substring(balance.indexOf("$") + 1).trim();
    }

    public String getPrice() {
        return String.format("%.2f", Double.valueOf(price.getText()));
    }
}
