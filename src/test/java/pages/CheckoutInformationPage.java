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

public class CheckoutInformationPage {
    protected WebDriver driver;


    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "postal-code")
    private WebElement zipInput;

    @FindBy(id = "continue")
    private WebElement continueBtn;


    public CheckoutInformationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutOverviewPage fillingInForm(String firstNameUser, String lastNameUser, String postCode){
        firstNameInput.click();
        firstNameInput.sendKeys(firstNameUser);

        lastNameInput.click();
        lastNameInput.sendKeys(lastNameUser);

        zipInput.click();
        zipInput.sendKeys(postCode);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(10));
        fluentWait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        continueBtn.click();
        return new CheckoutOverviewPage(driver);
    }

}
