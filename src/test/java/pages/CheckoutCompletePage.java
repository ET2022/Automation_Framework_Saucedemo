package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckoutCompletePage {
    protected WebDriver driver;

    @FindBy(id = "checkout_complete_container")
    private WebElement message;

    @FindBy(id = "back-to-products")
    private WebElement backBtn;



    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkMessage(){
        Assert.assertTrue(message.isDisplayed());
        System.out.println("Loading complete-page.");
    }

    public ProductsPage goHome(){
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20));
        fluentWait.until(ExpectedConditions.elementToBeClickable(backBtn));
        backBtn.click();
        return new ProductsPage(driver);
    }
}
