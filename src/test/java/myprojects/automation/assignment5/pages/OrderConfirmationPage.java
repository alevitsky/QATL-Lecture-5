package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderConfirmationPage extends Page {

    @CacheLookup
    @FindBy(xpath = "//h3[contains(@class, 'h1') and contains(@class, 'card-title')]")
    private WebElement confirmationMessage;

    @CacheLookup
    @FindBy(xpath = "//div[@class='order-line row']")
    private List<WebElement> products;

    @CacheLookup
    @FindBy(xpath = "//div[@class='order-line row']//div[2]/span")
    private WebElement productName;

    @CacheLookup
    @FindBy(xpath = "//div[@class='col-xs-5 text-sm-right text-xs-left']")
    private WebElement details;

    public OrderConfirmationPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OrderConfirmationPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public String getConfirmationMessage() {
        String rawText = confirmationMessage.getText();
        return rawText.substring(1, rawText.length()).toUpperCase();
    }

    public int numberOfPositions() {
        return products.size();
    }

    public String getProductName() {
        String rawText = productName.getText();
        return rawText.substring(0, rawText.indexOf(" - Size"));
    }

    public float getProductPrice() {
        return DataConverter.parsePriceValue(details.getText());
    }

    public OrderCheckoutPage returnToPreviousPage() {
        getDriver().navigate().back();
        return PageFactory.initElements(getDriver(), OrderCheckoutPage.class);
    }
}
