package webPageTesting;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObject.KommunalniePage;
import pageObject.MainPage;
import pageObject.PaymentsPage;
import pageObject.ZhkuPage;

import java.util.logging.Logger;


@Listeners(AllureReportListener.class)
public class RequestTest extends WebDriverSetting {

    final static Logger LOGGER = Logger.getLogger(RequestTest.class.getName());


    @Test(priority = 1, description = "Тест на переход страницу ЖКХ")
    public void goToPayment() throws InterruptedException {
        LOGGER.info("goToPayment test: Start");
        MainPage main = new MainPage(getDriver());
        getDriver().get("https://www.tinkoff.ru/");
        main.goToPayment();
        LOGGER.info(getDriver().getCurrentUrl());
        PaymentsPage payPage = new PaymentsPage(getDriver(),getWait());
        getDriver().navigate().refresh();
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//header[.=\"Коммунальные услуги\"]")));
        payPage.goToKommunal();
        Thread.sleep(5000);
        WebElement titleOfKommunal = getDriver().findElement(By.xpath("//div[contains(text(),\"Коммунальные услуги\")]"));
        Assert.assertTrue(titleOfKommunal.getAttribute("innerText").contains("Коммунальные услуги"));
        LOGGER.info(getDriver().getCurrentUrl());
        LOGGER.info("goToPayment test: Finnish");
    }

    @Test(priority = 2, dependsOnMethods = "goToPayment", description = "Тест на изменение региона")
    public void changeRegion() {
        LOGGER.info("changeRegion: Start");
        KommunalniePage kom = new KommunalniePage(getDriver(),getWait(),getBuilder());
        kom.changeRegion("г. Москва");
        LOGGER.info(getDriver().findElement(By.xpath("//span[@class='Link__inner_1Sgvq']/parent::span")).getAttribute("innerText"));
        Assert.assertEquals("Москве",kom.getNameOfCurrentRegion());
        LOGGER.info("changeRegion: Finnish");
    }


    @Test(priority = 2, description = "Тест на выбор предоставляемой услуги")
    public void zhku() throws InterruptedException {
        LOGGER.info("zhku: Start");
        KommunalniePage kom = new KommunalniePage(getDriver(),getWait(),getBuilder());
        ZhkuPage zhku = new ZhkuPage(getDriver());
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[.=\"ЖКУ-Москва\"]/div[2]")));
        kom.goToZhku();
        getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//h1[.=\"Узнайте задолженность по ЖКУ в Москве\"]"))));
        zhku.changeToPayZhku();
        Thread.sleep(5000);
        WebElement payZhku = getDriver().findElement(By.xpath("//h1[.=\"Оплатите ЖКУ в Москве без комиссии\"]"));
        Assert.assertTrue(payZhku.getAttribute("innerText").contains("Оплатите ЖКУ в"));
        LOGGER.info(getDriver().findElement(By.xpath("//h1[@data-qa-file='PageFrame']")).getAttribute("innerText"));
        LOGGER.info("zhku: Finnish");
    }
    @Test(priority = 3, dependsOnMethods = "zhku", description = "Тест на проверку невалидных значений")
    public void checkErrors() {
        LOGGER.info("checkErrors: Start");
        ZhkuPage zhku = new ZhkuPage(getDriver());
       zhku.payerCodeFillIn("");
       zhku.periodFillIn("56.7675");
       zhku.saveMoneyFillIn("-1");
       zhku.sumMoneyFillIn("1400000");
       zhku.checkForError("Поле обязательное","Поле заполнено некорректно",
               "Поле заполнено неверно");
        LOGGER.info("checkErrors: Finnish");
    }

    @Test(priority = 4, description = "Тест на проверку валидности страницы")
    public void checkPageSearch() throws InterruptedException {
        LOGGER.info("checkPageSearch: Start");
        getDriver().get("https://www.tinkoff.ru/payments/");
        PaymentsPage payPage = new PaymentsPage(getDriver(),getWait());
        ZhkuPage zhku = new ZhkuPage(getDriver());
        payPage.goToSearchFound("ЖКУ-Москва");
        zhku.changeToPayZhku();
        Thread.sleep(5000);
        zhku.checkValidOfPage("Оплатите ЖКУ в Москве без комиссии","Код плательщика за ЖКУ в Москве",
                "За какой период оплачиваете коммунальные услуги");
        LOGGER.info("checkPageSearch: Finnish");
    }

}
