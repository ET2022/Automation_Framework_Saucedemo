package qa.automation;

import base.TestUtil;
import org.testng.annotations.Test;
import pages.*;

import java.util.ArrayList;


public class RemoveItemCartTest extends TestUtil {

    @Test
    public void removeItem() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user", "secret_sauce");

        productPage.addItemToCart("Sauce Labs Backpack","item_4_title_link","29.99");
        productPage.addItemToCart("Sauce Labs Bike Light","item_0_title_link","9.99");
        productPage.addItemToCart("Sauce Labs Bolt T-Shirt","item_1_title_link","15.99");

        ArrayList<String> items = productPage.getProductNameList();
        ArrayList<String> idList = productPage.getProductIdList();
        ArrayList<String> priceList = productPage.getProductPriceList();

        CartPage cartPage = productPage.openCart();
        cartPage.removeItem("Sauce Labs Bike Light", "item_0_title_link","9.99",items, idList, priceList);

        cartPage.checkCart(items,idList,priceList);
        CheckoutInformationPage checkoutInformationPage = cartPage.checkout();
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.fillingInForm("Klava", "Koka","7200");
        checkoutOverviewPage.checkOverview(items,idList,priceList);
        checkoutOverviewPage.checkTotal(priceList);
    }
}
