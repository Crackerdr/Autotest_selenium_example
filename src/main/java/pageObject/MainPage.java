package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    private By payments = By.xpath("//a[contains(text(),'Платежи')]");


    public MainPage (WebDriver driver) {
        this.driver = driver;

    }


    public void goToPayment () {
        driver.findElement(payments).click();
    }



}
