package qa.automation;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import java.util.ArrayList;



public class AddProductsInCartTest extends TestUtil {

    @Test
    public void removeItemFromCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user", "secret_sauce");

        productPage.addItemToCart("Sauce Labs Backpack","item_4_title_link","29.99");
        productPage.addItemToCart("Sauce Labs Bike Light","item_0_title_link","9.99");

        Assert.assertEquals(productPage.getItemInCart(), 2, "Because we have added two items in the cart.");

        productPage.removeItemFromCart("Sauce Labs Backpack","item_4_title_link","29.99");
        Assert.assertEquals(productPage.getItemInCart(), 1, "Because we have removed one item from the cart.");

        productPage.addItemToCart("Sauce Labs Bolt T-Shirt", "item_1_title_link","15.99");
        Assert.assertEquals(productPage.getItemInCart(), 2, "Because we have removed one item from the cart.");

        ArrayList<String> items = productPage.getProductPriceList();
        ArrayList<String> idList = productPage.getProductIdList();
        ArrayList<String> priceList = productPage.getProductPriceList();

        CartPage cartPage = productPage.openCart();
        cartPage.checkCart(items,idList,priceList);
    }
}
