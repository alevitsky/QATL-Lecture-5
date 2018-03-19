package myprojects.automation.assignment5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class AllProductsPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//div[@class='product-description']")
    private List<WebElement> productList;

   @CacheLookup
    private WebElement product;

    public AllProductsPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AllProductsPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public ProductDetailsPage openRandomProduct() {
        Random random = new Random();
        product = productList.get(random.nextInt(productList.size()));
        scrollToElement(product);
        product.click();
        return PageFactory.initElements(getDriver(), ProductDetailsPage.class);
    }

}