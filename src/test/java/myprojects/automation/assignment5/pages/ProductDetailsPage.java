package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProductDetailsPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//button[@data-button-action='add-to-cart']")
    private WebElement addToCartButton;

    @CacheLookup
    @FindBy(xpath = "//h1[@itemprop = 'name']")
    private WebElement productTitle;

    @CacheLookup
    @FindBy(xpath = "//div[@class='product-quantities']/span")
    private WebElement productQty;

    @CacheLookup
    @FindBy(xpath = "//span[@itemprop = 'price']")
    private WebElement productPrice;
    
    @CacheLookup
    @FindBy(xpath = "//a[@href='#product-details']")
    private WebElement details;

    public ProductDetailsPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductDetailsPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public PopUpWindow addToCart() {
        addToCartButton.click();
        return PageFactory.initElements(getDriver(), PopUpWindow.class);
    }

    public ProductData getProductData() {
        getWait().until(elementToBeClickable(details));
        details.click();
        getWait().until(visibilityOf(productQty));
        return extractProductData(productTitle, productQty, productPrice);
    }

    public int getProductDetailsQty() {
        getWait().until(elementToBeClickable(details));
        details.click();
        getWait().until(visibilityOf(productQty));
        return DataConverter.parseStockValue(productQty.getText());
    }

}