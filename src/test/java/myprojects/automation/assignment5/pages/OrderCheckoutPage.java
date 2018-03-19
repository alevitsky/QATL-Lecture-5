package myprojects.automation.assignment5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class OrderCheckoutPage extends Page {
    @CacheLookup
    @FindBy(name = "firstname")
    private WebElement firstnameInput;

    @CacheLookup
    @FindBy(name = "lastname")
    private WebElement lastnameInput;

    @CacheLookup
    @FindBy(name = "email")
    private WebElement emailInput;

    @CacheLookup
    @FindBy(name = "continue")
    private WebElement continueButton;

    @CacheLookup
    @FindBy(name = "address1")
    private WebElement addressInput;

    @CacheLookup
    @FindBy(name = "postcode")
    private WebElement postcodeInput;

    @CacheLookup
    @FindBy(name = "city")
    private WebElement cityInput;

    @CacheLookup
    @FindBy(xpath = "//button[@name='confirm-addresses']")
    private WebElement confirmAddressesButton;

    @CacheLookup
    @FindBy(xpath = "//*[@id='js-delivery']/button")
    private WebElement confirmDeliveryOptionButton;

    @CacheLookup
    @FindBy(id = "payment-option-1")
    private WebElement paymentOption;

    @CacheLookup
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement conditionsToApprove;

    @CacheLookup
    @FindBy(xpath = "//div[@id='payment-confirmation']//button[@type='submit']")
    private WebElement paymentConfirmationButton;


    @CacheLookup
    @FindBy(xpath = "//div[@class='cart-summary-products']/p/a")
    private WebElement detailsLink;

    @CacheLookup
    @FindBy(xpath = "//*[@class='media-left']")
    private WebElement productLink;

    public OrderCheckoutPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OrderCheckoutPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public OrderCheckoutPage fillFirstName(final String firstname) {
        firstnameInput.sendKeys(firstname);
        return this;
    }

    public OrderCheckoutPage fillLastName(final String lastname) {
        lastnameInput.sendKeys(lastname);
        return this;
    }


    public OrderCheckoutPage fillEmail(final String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public OrderCheckoutPage fillAddress(final String address) {
        addressInput.sendKeys(address);
        return this;
    }

    public OrderCheckoutPage fillPostcode(final String postcode) {
        postcodeInput.sendKeys(postcode);
        return this;
    }

    public OrderCheckoutPage fillCity(final String city) {
        cityInput.sendKeys(city);
        return this;
    }

    public OrderCheckoutPage proceedToAddress() {
        continueButton.click();
        return this;
    }

    public OrderCheckoutPage confirmAddress() {
        confirmAddressesButton.click();
        return this;
    }

    public OrderCheckoutPage confirmDeliveryOption() {
        confirmDeliveryOptionButton.click();
        return this;
    }

    public OrderCheckoutPage checkPaymentOption() {
        paymentOption.click();
        return this;
    }

    public OrderCheckoutPage checkConditionsToApprove() {
        conditionsToApprove.click();
        return this;
    }

    public OrderConfirmationPage confirmPayment() {
        getWait().until(elementToBeClickable(paymentConfirmationButton));
        paymentConfirmationButton.click();
        return PageFactory.initElements(getDriver(), OrderConfirmationPage.class);
    }

    public ProductDetailsPage goToProductPage() {
        detailsLink.click();
        productLink.click();
        return PageFactory.initElements(getDriver(), ProductDetailsPage.class);
    }
}