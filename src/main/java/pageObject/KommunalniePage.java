package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class KommunalniePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions builder;

    private By region = By.xpath("//span[@role='button']");

    private By backToPageBtn = By.xpath("//div[@class='PageFrame__backButton_Wk_-E']/a");
    
    private By currentRegion = By.xpath("//span[@class='Link__inner_1Sgvq']/parent::span");

    private By zhkuMoskva = By.xpath("//div[.=\"ЖКУ-Москва\"]");

    public KommunalniePage(WebDriver driver, WebDriverWait wait, Actions builder) {
        this.driver = driver;
        this.wait = wait;
        this.builder = builder;
    }

    public void changeRegion(String regionToChange) {
        driver.findElement(region).click();
        wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/?popup=REGIONS_CHANGE-646648.1593605681740/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[.=\""+regionToChange+"\"]/parent::a")).click();
    }

    public String getNameOfCurrentRegion() {
    return driver.findElement(currentRegion).getAttribute("innerText");
    }

    public void goToZhku() {

        builder.moveToElement(driver.findElement(zhkuMoskva)).click().build().perform();
    }



}
