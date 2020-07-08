package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ZhkuPage {
    private final WebDriver driver;


    private final By payZhku = By.xpath("//ul[@data-qa-file='Tabs']/child::li[2]/div");

    private final By period = By.id("period");

    private final By payerCode = By.id("payerCode");

    private final By saveMoney = By.xpath("//span[contains(text(),\"страхования\")]/following::div[1]/input");

    private final By sumPay = By.xpath("//span[contains(text(),\"Сумма платежа\")]/following::div/input");

    private final By payment = By.xpath("//a[.=\"Платежи\"]");

    private final By titleOfPage = By.xpath("//h1[.=\"Оплатите ЖКУ в Москве без комиссии\"]");

    private final By titleOfPayerCode = By.xpath("//span[.=\"Код плательщика за ЖКУ в Москве\"]/span[1]");

    private final By titleOfPeriod = By.xpath("//span[.=\"За какой период оплачиваете коммунальные услуги\"]/span[1]");

    private final By titleOfSaveMoney = By.xpath("//span[contains(text(),\"Сумма добровольного страхования\")]");

    private final By titleOfSumPay = By.xpath("//span[contains(text(),\"Сумма платежа,  от 1\")]");




    public ZhkuPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Фокусировка на layout")
    public void changeToPayZhku() {
        driver.findElement(By.xpath("//div[@data-qa-file='PlatformLayout']"));
        driver.findElement(payZhku).click();

    }

    @Step("Заполнение кода плательщика")
    public void payerCodeFillIn(String number) {
        driver.findElement(payerCode).click();
        driver.findElement(payerCode).sendKeys(number);
        driver.findElement(By.xpath("//h2[@class='ui-button__ui-button__text_pw5ce']")).click();
    }

    @Step("Заполнение периода оплачивания")
    public void periodFillIn (String number) {
        driver.findElement(period).click();
        driver.findElement(period).sendKeys(Keys.CONTROL+"a");
        driver.findElement(period).sendKeys(Keys.DELETE);
        driver.findElement(period).sendKeys(number);
    }

    @Step("Заполнение суммы добровольного страхования")
    public void saveMoneyFillIn(String number) {
        driver.findElement(saveMoney).click();
        driver.findElement(saveMoney).sendKeys(number);
    }

    @Step("Заполнение суммы платежа")
    public void sumMoneyFillIn(String number) {
        driver.findElement(sumPay).click();
        driver.findElement(sumPay).sendKeys(number);
    }

    @Step("Проверка наличие ошибки в коде плательщика")
    public void checkPayerError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[.=\"Поле обязательное\"]"));
        Assert.assertEquals(error.getAttribute("innerText"),textError);
    }

    @Step("Проверка наличие ошибки в периоде оплачивания")
    public void checkPeriodError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[.=\"Поле заполнено некорректно\"]"));
        Assert.assertEquals(error.getAttribute("innerText"),textError);
    }

    @Step("Проверка наличие ошибки в сумме добровольного страхования")
    public void checkSaveMoneyError(String textError){
        WebElement error = driver.findElement(By.xpath("//div[.=\"Поле заполнено неверно\"]"));
        Assert.assertEquals(error.getAttribute("innerText"),textError);
    }

    @Step("Проверка наличие ошибки в сумме платежа")
    public void checksumMoneyError(){
        WebElement error = driver.findElement(By.xpath("//div[contains(text(),\"Максимум\")]"));
        Assert.assertTrue(error.getAttribute("innerText").contains("Максимум — 15"));

    }

    @Step("Проверка на наличие ошибок на странице оплаты жку")
    public void checkForError(String payerError, String periodError, String saveMoneyError){

        checkPayerError(payerError);
        checkPeriodError(periodError);
        checkSaveMoneyError(saveMoneyError);
        checksumMoneyError();


    }

    @Step("Переход на Оплатить ЖКУ в Москве tab")
    public void goToPayment() {
        driver.findElement(payment).click();
}

    @Step("Проверка на валидность страницы")
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
