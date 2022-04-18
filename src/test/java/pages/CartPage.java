package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;


public class CartPage {
    protected WebDriver driver;
    private static final String REMOVE_TO_CART_LOCATOR = "//*[@id='remove-%s']";


    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(className = "inventory_item_name")
    private WebElement itemName;

    @FindBy(id = "cart_contents_container")
    private WebElement containerCart;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutInformationPage checkout() {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5));
        fluentWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        checkoutBtn.click();
        return new CheckoutInformationPage(driver);
    }

    public void checkCart(ArrayList<String> items, ArrayList<String> idList, ArrayList<String> priceList) {
            for (String idItem : idList) {
                WebElement itemId = driver.findElement(By.id(idItem));
                Assert.assertTrue(itemId.isDisplayed(), "Because we added this product to cart.");
            }

            for (String item : items){
                WebElement itemName = driver.findElement(By.className("inventory_item_name"));
                if (itemName.getText()==item)
                    Assert.assertEquals(itemName.getText(),item,"Because we added this product to cart");
            }

            for (String price : priceList){
                WebElement priceItem = driver.findElement(By.className("inventory_item_price"));
                if (priceItem.getText()==price)
                    Assert.assertEquals(itemName.getText(),price,"Because we added this product to cart");
            }
    }

    public void removeItem(String itemRemove, String idItemRemove,String itemPriceRemove,ArrayList<String> items, ArrayList<String> idList, ArrayList<String> priceList) {
                String xpathOfItemToBeRemoved = String.format(REMOVE_TO_CART_LOCATOR, itemRemove.toLowerCase().replaceAll("\\s+", "-"));
                WebElement removeBtn = driver.findElement(By.xpath(xpathOfItemToBeRemoved));
                FluentWait fluentWait = new FluentWait(driver)
                        .withTimeout(Duration.ofSeconds(5));
                fluentWait.until(ExpectedConditions.elementToBeClickable(removeBtn));
                Assert.assertTrue(removeBtn.isDisplayed());
                System.out.println(xpathOfItemToBeRemoved);
                removeBtn.click();
                items.remove(itemRemove);
                idList.remove(idItemRemove);
                priceList.remove(itemPriceRemove);
    }
}

