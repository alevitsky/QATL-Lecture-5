package myprojects.automation.assignment5.pages;

import com.google.common.base.Predicate;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import myprojects.automation.assignment5.utils.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * Place for the common behavior
 */
public abstract class Page {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @CacheLookup
    @FindBy(id="_mobile_logo")
    private WebElement mobileLogo;

    public Page(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public abstract Page waitLoadFinished();

    public MainPage openMainPage() {
        driver.get(Properties.getBaseUrl());
        return PageFactory.initElements(getDriver(), MainPage.class);
    }

    public boolean isMobileVersion() {
        return mobileLogo.isDisplayed();
    }

    protected void scrollToElement(final WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void clickWithJS(final WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected void inputWithJS(final WebElement element, final String value) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        String expr = String.format("arguments[0].value = '%s';", value);
        executor.executeScript(expr, element);
    }

    protected void waitPageReady() {
        wait.until((Predicate<WebDriver>) wd -> ((JavascriptExecutor)wd)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    protected ProductData extractProductData(WebElement productTitle, WebElement productQty, WebElement productPrice) {
        String name = productTitle.getText();
        int qty     = DataConverter.parseStockValue(productQty.getText());
        float price = DataConverter.parsePriceValue(productPrice.getText());
        return new ProductData(name, qty, price);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        return wait;
    }

}