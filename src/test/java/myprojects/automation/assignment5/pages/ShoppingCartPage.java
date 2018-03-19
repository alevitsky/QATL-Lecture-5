package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ShoppingCartPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//a[contains(@class, 'btn') and contains(@class, 'btn-primary')]")
    private WebElement checkout;

    @CacheLookup
    @FindBy(xpath = "//div[@class='product-line-info']//a")
    private WebElement productTitle;

    @CacheLookup
    @FindBy(xpath = "//span[@class='product-price']//strong")
    private WebElement productPrice;

    @CacheLookup
    @FindBy(xpath = "//ul[@class='cart-items']")
    private List<WebElement> productsOnCart;

    public ShoppingCartPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ShoppingCartPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public OrderCheckoutPage checkoutOrder() {
        checkout.click();
        return PageFactory.initElements(getDriver(), OrderCheckoutPage.class);
    }

    public String getProductName() {
        return productTitle.getText();
    }

    public float getProductPrice() {
        return DataConverter.parsePriceValue(productPrice.getText());
    }

    public int size() {
        return productsOnCart.size();
    }
}
