package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    public static WebDriver getChromeDriver() {

        WebDriverManager.chromedriver().setup(); //this download the needed web driver
        WebDriver driver = new ChromeDriver(); //creates the session and open the
        return driver;
    }

    public static WebDriver getFireFoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }
}
