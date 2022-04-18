package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class ProblemUserTest extends TestUtil {

    @DataProvider(name = "csvProductsList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/products.csv");
    }

    @Test(dataProvider = "csvProductsList")
    public void shopping(String productName, String productId, String productSrc, String productPrice) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("problem_user", "secret_sauce");
        productPage.checkImage(productName, productId, productSrc);
    }
}
