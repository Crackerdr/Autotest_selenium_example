package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    private final By payments = By.xpath("//a[contains(text(),'Платежи')]");

    public MainPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу платежи")
    public void goToPayment () {
        driver.findElement(payments).click();
    }

}
