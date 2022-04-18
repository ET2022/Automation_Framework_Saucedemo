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

public class CheckoutOverviewPage {
    protected WebDriver driver;


    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[5]")
    private WebElement totalPrice;



    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CheckoutCompletePage finish(){
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5));
        fluentWait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        finishBtn.click();
        return new CheckoutCompletePage(driver);
    }

    public void checkOverview(ArrayList<String> items, ArrayList<String> idList, ArrayList<String> priceList){

            for (String idItem : idList) {
                WebElement itemId = driver.findElement(By.id(idItem));
                Assert.assertTrue(itemId.isDisplayed(), "Because we added this product to the purchase");
                //System.out.println(itemId + " is dislayed in page-overview!");
            }
            for (String item : items){
                WebElement itemName = driver.findElement(By.className("inventory_item_name"));
                if (itemName.getText()==item)
                    Assert.assertEquals(itemName.getText(),item,"Because we added this product to the purchase");
                System.out.println(item + " is dislayed in page-overview");
            }

            for (String price : priceList){
            WebElement priceItem = driver.findElement(By.className("inventory_item_price"));
                if (priceItem.getText()==price)
                   Assert.assertEquals(priceItem.getText(),price,"Because we added this product to cart");
                System.out.println(price + " is dislayed in page-overview!");
            }
    }

    public void checkTotal(ArrayList<String> priceList){
        Double total = 0.0;
        for (String price : priceList) {
            try {
                Double priceTotal = Double.valueOf(price);
                total += priceTotal;
            } catch (NumberFormatException e) {
                System.err.println("Invalid string format!");
            }
        }
        System.out.println(total);
        String str = Double.toString(total);

        String s1 = String.format("Item total: $" + str);
        Assert.assertEquals(totalPrice.getText(),s1,"Because that's the total cost.");
    }
}
