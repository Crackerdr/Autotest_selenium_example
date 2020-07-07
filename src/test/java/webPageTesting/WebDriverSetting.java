package webPageTesting;


import org.openqa.selenium.interactions.Actions;
import pageObject.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.logging.Logger;

public class WebDriverSetting {

    public final static Logger LOGGER = Logger.getLogger(WebDriverSetting.class.getName());
    private ChromeDriver driver;
   private WebDriverWait wait;
    private Actions builder;



    public ChromeDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public Actions getBuilder(){
        return builder;
    }




    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/user/chromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        builder = new Actions(driver);
        driver.manage().window().maximize();
        LOGGER.info("Start");
    }
    @AfterMethod
    public void closeDriver() {
        LOGGER.info("Finish");
//        driver.close();
//        driver.quit();
    }


}
