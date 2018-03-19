package myprojects.automation.assignment5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class PopUpWindow extends Page {
    @CacheLookup
    @FindBy(xpath = "//a[contains(@class, 'btn') and contains(@class, 'btn-primary')]")
    private WebElement checkout;

    public PopUpWindow(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public PopUpWindow waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public ShoppingCartPage proceedToCart() {
        getWait().until(visibilityOf(checkout));
        checkout.click();
        return PageFactory.initElements(getDriver(), ShoppingCartPage.class);
    }
}
