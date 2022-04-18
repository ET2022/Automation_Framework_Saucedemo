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


public class ProductsPage {
    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-%s']";
    private static final String REMOVE_TO_CART_LOCATOR = "//button[@id='remove-%s']";
    private static String imageLink = "//*[@id=\"%S\"]/img ";

    ArrayList<String> productNameList = new ArrayList<>();
    ArrayList<String> productIdList = new ArrayList<>();
    ArrayList<String> productPriceList = new ArrayList<>();


    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartCounter;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartBtn;

    @FindBy(xpath = "//*[@id='react-burger-menu-btn']")
    private WebElement burgerMenuBtn;

    @FindBy(className = "inventory_item_img")
    private WebElement productImage;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkBurgerMenu(){
        Assert.assertTrue(burgerMenuBtn.isDisplayed());
    }

    public void checkImage(String productName, String productId, String productSrc){

        String xpathOfItemImage = String.format(imageLink,productId.replace("title", "img"));
        System.out.println(xpathOfItemImage.toLowerCase());

        WebElement image = driver.findElement(By.xpath(xpathOfItemImage.toLowerCase()));
        Assert.assertTrue(image.isDisplayed());

        String src = image.getAttribute("src");
        System.out.println(src);

        Assert.assertEquals(src, productSrc, "The photo is them item.");
        System.out.println(productName + " is really photo.");
    }

    public void addItemToCart(String productName, String productId, String productPrice){
        String xpathOfItemToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName.toLowerCase().replaceAll("\\s+", "-"));
        WebElement addToCartBtn = driver.findElement(By.xpath(xpathOfItemToBeAdded));

        System.out.println(productName + xpathOfItemToBeAdded + " added");

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5));
        fluentWait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

        if (addToCartBtn.getText().contains("ADD")){
            addToCartBtn.click();
        productNameList.add(productName);
        productIdList.add(productId);
        productPriceList.add(productPrice);
        }else {
            System.out.println(productName + " not remove to cart");
        }
    }

    public void removeItemFromCart(String productName, String productId, String productPrice){
        String xpathOfItemToBeRemoved = String.format(REMOVE_TO_CART_LOCATOR, productName.toLowerCase().replaceAll("\\s+", "-"));
        WebElement removeBtn = driver.findElement(By.xpath(xpathOfItemToBeRemoved));

        System.out.println(productName + xpathOfItemToBeRemoved + " removed");

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeBtn));

          if (removeBtn.getText().contains("REMOVE")) {
              removeBtn.click();
              productNameList.remove(productName);
              productIdList.remove(productId);
              productPriceList.remove(productPrice);
          }
    }

    public int getItemInCart(){
       if (shoppingCartCounter.getText().isEmpty()){
            return 0;
       }else {
            return Integer.parseInt(shoppingCartCounter.getText());
       }
    }

    public CartPage openCart(){
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5));
        fluentWait.until(ExpectedConditions.elementToBeClickable(shoppingCartBtn));
        shoppingCartBtn.click();

        return new CartPage(driver);
    }

    public ArrayList<String> getProductNameList(){return this.productNameList;}

    public ArrayList<String> getProductIdList(){return this.productIdList;}

    public ArrayList<String> getProductPriceList(){return this.productPriceList;}
}
