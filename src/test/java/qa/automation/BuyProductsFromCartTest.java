package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;
import java.io.IOException;
import java.util.ArrayList;

public class BuyProductsFromCartTest extends TestUtil {

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/users.csv");
    }

    @Test(dataProvider = "csvUserList")
    public void buyProductsFromCart(String userName, String password, String firstName, String lastName, String postCode){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login(userName, password);

        productPage.addItemToCart("Test.allTheThings() T-Shirt (Red)","item_3_title_link","15.99");
        productPage.addItemToCart("Sauce Labs Onesie","item_2_title_link","7.99");

        ArrayList<String> items = productPage.getProductNameList();
        ArrayList<String> idList = productPage.getProductIdList();
        ArrayList<String> priceList = productPage.getProductPriceList();

        CartPage cartPage = productPage.openCart();
        cartPage.checkCart(items,idList,priceList);

        CheckoutInformationPage checkoutInformationPage = cartPage.checkout();
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.fillingInForm(firstName,lastName,postCode);

        checkoutOverviewPage.checkOverview(items,idList,priceList);
        checkoutOverviewPage.checkTotal(priceList);
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finish();

        checkoutCompletePage.checkMessage();
        checkoutCompletePage.goHome();

        Assert.assertEquals(productPage.getItemInCart(),0,"Because all products purchased.");
    }

}
