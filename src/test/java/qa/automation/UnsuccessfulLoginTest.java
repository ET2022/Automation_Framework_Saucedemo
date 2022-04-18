package qa.automation;

import base.TestUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;



public class UnsuccessfulLoginTest extends TestUtil {

    @DataProvider(name = "users")
    public Object[][] getUsers(){
        return new Object[][]{
                {"standard_user", "wrongPass"},
                {"wrong_User", "secret_sauce"},
                {"", ""},
                {"", "kutddu@78"},
                {"koci35", ""}
        };
    }

    @Test (dataProvider = "users")
    public void UnsuccessfulLoginTest(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
        loginPage.checkWrongBtn();
    }
}
