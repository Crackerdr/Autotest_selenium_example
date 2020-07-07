package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PaymentsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By kommunal = By.xpath("//header[.=\"Коммунальные услуги\"]");

private By search = By.xpath("//div[contains(text(),\"Имя, название\")]/following:: input");

    public PaymentsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void goToKommunal() {
        driver.findElement(kommunal).click();
        Actions actionObject = new Actions(driver);
        actionObject = actionObject.sendKeys(Keys.RETURN); //ASSIGN the return or you lose this event.
        actionObject.perform();

    }

    public void searchProvider(String providerName) {
        driver.findElement(search).click();
        driver.findElement(search).sendKeys(providerName);
    }
    public void goToSearchFound(String providerName) throws InterruptedException {
        searchProvider(providerName);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(search).click();
        Thread.sleep(5000);
//        driver.findElement(search).sendKeys(Keys.DOWN,Keys.RETURN);

        Actions actionObject = new Actions(driver);
        actionObject = actionObject.sendKeys(Keys.DOWN,Keys.UP,Keys.RETURN); //ASSIGN the return or you lose this event.
        actionObject.perform();
    }
}
