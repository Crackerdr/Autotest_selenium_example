package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ZhkuPage {
    private WebDriver driver;
    private Actions builder;

    private By payZhku = By.xpath("//ul[@data-qa-file='Tabs']/child::li[2]/div");
//            ("//div[@data-qa-file='Tabs']/ul/li[2]");

    private By period = By.id("period");

    private By payerCode = By.id("payerCode");

    private By saveMoney = By.xpath("//span[contains(text(),\"страхования\")]/following::div[1]/input");

    private By sumPay = By.xpath("//span[contains(text(),\"Сумма платежа\")]/following::div/input");

    private By payment = By.xpath("//a[.=\"Платежи\"]");

    private By titleOfPage = By.xpath("//h1[.=\"Оплатите ЖКУ в Москве без комиссии\"]");

    private By titleOfPayerCode = By.xpath("//span[.=\"Код плательщика за ЖКУ в Москве\"]/span[1]");

    private By titleOfPeriod = By.xpath("//span[.=\"За какой период оплачиваете коммунальные услуги\"]/span[1]");

    private By titleOfSaveMoney = By.xpath("//span[contains(text(),\"Сумма добровольного страхования\")]");

    private By titleOfSumPay = By.xpath("//span[contains(text(),\"Сумма платежа,  от 1\")]");




    public ZhkuPage(WebDriver driver,Actions builder) {
        this.driver = driver;
        this.builder = builder;
    }

    public void changeToPayZhku() {

        driver.findElement(By.xpath("//div[@data-qa-file='PlatformLayout']"));
        driver.findElement(payZhku).click();

    }

    public void payerCodeFillIn(String number) {
        driver.findElement(payerCode).click();
        driver.findElement(payerCode).sendKeys(number);
        driver.findElement(By.xpath("//h2[@class='ui-button__ui-button__text_pw5ce']")).click();
    }

    public void periodFillIn (String number) {
        driver.findElement(period).click();
        driver.findElement(period).sendKeys(Keys.CONTROL+"a");
        driver.findElement(period).sendKeys(Keys.DELETE);
        driver.findElement(period).sendKeys(number);
    }

    public void saveMoneyFillIn(String number) {
        driver.findElement(saveMoney).click();
        driver.findElement(saveMoney).sendKeys(number);
    }

    public void sumMoneyFillIn(String number) {
        driver.findElement(sumPay).click();
        driver.findElement(sumPay).sendKeys(number);
    }

    public void checkPayerError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[.=\"Поле обязательное\"]"));
        Assert.assertEquals(error.getAttribute("innerText"),textError);
    }

    public void checkPeriodError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[.=\"Поле заполнено некорректно\"]"));
        Assert.assertEquals(error.getAttribute("innerText"),textError);
    }

    public void checkSaveMoneyError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[.=\"Поле заполнено неверно\"]"));
        Assert.assertEquals(error.getAttribute("innerText"),textError);
    }


    public void checksumMoneyError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[contains(text(),\"Максимум\")]"));
//        Assert.assertEquals(error.getAttribute("innerText"),textError);
        Assert.assertTrue(error.getAttribute("innerText").contains("Максимум — 15"));

    }

    public void checkForError(String payerError, String periodError, String saveMoneyError, String sumMoneyError){

        checkPayerError(payerError);
        checkPeriodError(periodError);
        checkSaveMoneyError(saveMoneyError);
        checksumMoneyError(sumMoneyError);


    }

public void goToPayment() {
        driver.findElement(payment).click();
}

public void checkValidOfPage(String title, String valPayer, String valPeriod){
        WebElement titleEl = driver.findElement(titleOfPage);
        WebElement payerEl = driver.findElement(titleOfPayerCode);
        WebElement periodEl = driver.findElement(titleOfPeriod);
        WebElement saveMoneyEl = driver.findElement(titleOfSaveMoney);
        WebElement sumPayEl = driver.findElement(titleOfSumPay);
        Assert.assertEquals(titleEl.getAttribute("innerText"),title);
        Assert.assertEquals(payerEl.getAttribute("innerText"), valPayer);
        Assert.assertEquals(periodEl.getAttribute("innerText"),valPeriod);
    Assert.assertTrue(saveMoneyEl.getAttribute("innerText").contains("Сумма добровольного страховани"));
    Assert.assertTrue(sumPayEl.getAttribute("innerText").contains("Сумма платежа"));


}

}
