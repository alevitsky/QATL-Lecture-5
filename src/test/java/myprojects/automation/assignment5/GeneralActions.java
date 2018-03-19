package myprojects.automation.assignment5;


import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.pages.*;
import myprojects.automation.assignment5.utils.logging.CustomReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final MainPage mainPage;
    private AllProductsPage allProducts;
    private ProductDetailsPage productDetails;
    private PopUpWindow popUpWindow;
    private ShoppingCartPage cart;
    private OrderCheckoutPage orderCheckout;
    private OrderConfirmationPage orderConfirmation;

    public GeneralActions(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.mainPage = new MainPage(driver);
    }

    public void openMainPage() {
        CustomReporter.logAction("Open the main page of the shop.");
        mainPage.open().waitLoadFinished();
    }

    public void viewAllProducts() {
        CustomReporter.logAction("Open the list of all products.");
        this.allProducts = mainPage.viewAllProducts();
    }

    public void openRandomProduct() {
        CustomReporter.logAction("Open a product from the list of all products at random.");
        this.productDetails = allProducts.openRandomProduct();
    }

    /**
     * Extracts product information from opened product details page.
     *
     * @return
     */
    public ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");
        return productDetails.getProductData();
    }

    public void addToCart() {
        CustomReporter.logAction("Add product to the shopping cart.");
        this.popUpWindow = productDetails.addToCart();
    }

    public void proceedToCart() {
        CustomReporter.logAction("Proceed to the shopping cart.");
        this.cart = popUpWindow.proceedToCart();
    }

    public int cartSize() {
        CustomReporter.logAction("Get the size of the shopping cart.");
        return cart.size();
    }

    public String getCartProductName() {
        return cart.getProductName();
    }

    public float getCartProductPrice() {
        return cart.getProductPrice();
    }

    public void checkoutOrder() {
        CustomReporter.logAction("Place the chosen order.");
        this.orderCheckout = cart.checkoutOrder();
    }

    public void fillPersonalInfo(final String firstname, final String lastname, final String email) {
        orderCheckout.fillFirstName(firstname)
                     .fillLastName(lastname)
                     .fillEmail(email);
    }

    public void proceedToAddress() {
        orderCheckout.proceedToAddress();
    }

    public void fillAddressInfo(final String address, final String postcode, final String city) {
        orderCheckout.fillAddress(address)
                     .fillPostcode(postcode)
                     .fillCity(city);
    }

    public void confirmAddress() {
        orderCheckout.confirmAddress();
    }

    public void confirmDeliveryOption() {
        orderCheckout.confirmDeliveryOption();
    }

    public void checkPaymentOption() {
        orderCheckout.checkPaymentOption();
    }

    public void checkConditionsToApprove() {
        orderCheckout.checkConditionsToApprove();
    }

    public void confirmPayment() {
        this.orderConfirmation = orderCheckout.confirmPayment();
    }

    public String getConfirmationMessage() {
        return orderConfirmation.getConfirmationMessage();
    }

    public String getProductName() {
        return orderConfirmation.getProductName();
    }

    public float getProductPrice() {
        return orderConfirmation.getProductPrice();
    }

    public int numberOfPositions() {
        return orderConfirmation.numberOfPositions();
    }

    public void returnToPreviousPage() {
        orderConfirmation.returnToPreviousPage();
    }

    public void goToProductPage() {
        this.productDetails = orderCheckout.goToProductPage();
    }

    public int getUpdatedQty() {
        return productDetails.getProductDetailsQty();
    }

    public boolean isMobileVersion() {
        return mainPage.isMobileVersion();
    }
}
