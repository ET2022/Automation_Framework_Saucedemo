package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(css = "[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginBtn;

    @FindBy(css = ".error-button")
    private WebElement wrongUserBtn;

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    private WebElement errorMassage;



    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login(String username, String password){
        userNameInput.click();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.sendKeys(password);

        WebDriverWait wait = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("login-button"))));
        loginBtn.click();
        if (username.isEmpty()){
           Assert.assertEquals(errorMassage.getText(),"Epic sadface: Username is required","Because user name is empty");
            Assert.assertTrue(loginBtn.isEnabled());
            return null;
        }
        if (password.isEmpty()){
            Assert.assertEquals(errorMassage.getText(),"Epic sadface: Password is required","Because password name is empty");
            Assert.assertTrue(loginBtn.isEnabled());
            return null;
        }
//        if(){
//            Assert.assertEquals(wrongUserBtn.getText(),"Epic sadface: Username and password do not match any user in this service",
//                    "Because username and password do not match any user in this service");
//            Assert.assertTrue(loginBtn.isEnabled());
//            return null;
//        }
        return new ProductsPage(driver);
    }

    public boolean checkWrongBtn(){
        Assert.assertTrue(wrongUserBtn.isDisplayed());
        return true;
    }
}
