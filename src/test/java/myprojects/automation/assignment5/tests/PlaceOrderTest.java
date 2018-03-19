package myprojects.automation.assignment5.tests;

import myprojects.automation.assignment5.BaseTest;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.model.UserData;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlaceOrderTest extends BaseTest {

    @Test
    @Parameters("selenium.browser")
    public void checkSiteVersion() {
        actions.openMainPage();
        assertEquals(actions.isMobileVersion(), isMobileTesting);
    }

    @Test(dependsOnMethods = "checkSiteVersion")
    public void createNewOrder() {
        actions.viewAllProducts();

        // open random product
        actions.openRandomProduct();

        // save product parameters
        ProductData pickedProduct = actions.getOpenedProductInfo();

        // add product to Cart and validate product information in the Cart
        actions.addToCart();

        // proceed to order creation, fill required information
        actions.proceedToCart();

        // place new order and validate order summary
        assertEquals(actions.cartSize(), 1);
        assertEquals(actions.getCartProductName().toLowerCase(), pickedProduct.getName().toLowerCase());
        assertEquals(actions.getCartProductPrice(), pickedProduct.getPrice());

        actions.checkoutOrder();

        UserData testUser = UserData.generate();

        actions.fillPersonalInfo(testUser.getFirstname(), testUser.getLastname(), testUser.getEmail());
        actions.proceedToAddress();
        actions.fillAddressInfo("Test Street", "33000", "Test");
        actions.confirmAddress();
        actions.confirmDeliveryOption();
        actions.checkPaymentOption();
        actions.checkConditionsToApprove();
        actions.confirmPayment();

        assertEquals(actions.getConfirmationMessage(), "ВАШ ЗАКАЗ ПОДТВЕРЖДЁН");
        assertEquals(actions.numberOfPositions(), 1);
        assertEquals(actions.getProductName().toLowerCase(), pickedProduct.getName().toLowerCase());
        assertEquals(actions.getProductPrice(), pickedProduct.getPrice());

        actions.returnToPreviousPage();
        actions.goToProductPage();

        // check updated In Stock value
        assertEquals(actions.getUpdatedQty(), pickedProduct.getQty() - 1);
    }

}
