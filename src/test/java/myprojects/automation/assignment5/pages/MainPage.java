package myprojects.automation.assignment5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//*[@id='content']/section/a")
    private WebElement allProductsLink;

    public MainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public MainPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public MainPage open() {
        openMainPage();
        return this;
    }

    public AllProductsPage viewAllProducts() {
//        scrollToElement(allProductsLink);
//        clickWithJS(allProductsLink);
        allProductsLink.click();
        return PageFactory.initElements(getDriver(), AllProductsPage.class);
    }

}