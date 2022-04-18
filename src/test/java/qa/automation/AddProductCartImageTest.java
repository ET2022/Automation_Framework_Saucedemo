package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;
import java.io.IOException;


public class AddProductCartImageTest extends TestUtil {

    @DataProvider(name = "csvProductsList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/products.csv");
    }

    @Test(dataProvider = "csvProductsList")
    public void addItemToCart(String productName, String productId, String productSrc, String productPrice) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user","secret_sauce");
        productPage.addItemToCart(productName,productId,productPrice);
        Assert.assertEquals(productPage.getItemInCart(), 1, "Because we have added one item in the cart.");
    }
}
