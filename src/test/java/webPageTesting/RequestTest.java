package webPageTesting;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.KommunalniePage;
import pageObject.MainPage;
import pageObject.PaymentsPage;
import pageObject.ZhkuPage;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RequestTest extends WebDriverSetting {

    final static Logger LOGGER = Logger.getLogger(RequestTest.class.getName());



    @Test()
    public void goToPayment() {
        LOGGER.info("goToPayment test: Start");
        MainPage main = new MainPage(getDriver());
        getDriver().get("https://www.tinkoff.ru/");
        main.goToPayment();
        LOGGER.info(getDriver().getCurrentUrl());
        PaymentsPage payPage = new PaymentsPage(getDriver(),getWait());
        getDriver().navigate().refresh();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//header[.=\"Коммунальные услуги\"]")));
        payPage.goToKommunal();
        LOGGER.info(getDriver().getCurrentUrl());
        LOGGER.info("goToPayment test: Finnish");
    }

    @Test
    public void changeRegion() {
        LOGGER.info("changeRegion: Start");
        KommunalniePage kom = new KommunalniePage(getDriver(),getWait(),getBuilder());
        getDriver().get("https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/");

        kom.changeRegion("г. Москва");

//        getDriver().get("https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/?popup=REGIONS_CHANGE-646648.1593605681740/");

        LOGGER.info(getDriver().findElement(By.xpath("//span[@class='Link__inner_1Sgvq']/parent::span")).getAttribute("innerText"));

        Assert.assertEquals("Москве",kom.getNameOfCurrentRegion());

        LOGGER.info("changeRegion: Finnish");
    }

    @Test
    public void goToPayZhku() throws InterruptedException {
        LOGGER.info("goToPayZhku: Start");
        KommunalniePage kom = new KommunalniePage(getDriver(),getWait(),getBuilder());
        ZhkuPage zhku = new ZhkuPage(getDriver(),getBuilder());
        getDriver().get("https://www.tinkoff.ru/payments/categories/kommunalnie-platezhi/");
        kom.changeRegion("г. Москва");
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        kom.goToZhku();
        Thread.sleep(10000);
        LOGGER.info(getDriver().getCurrentUrl());
        LOGGER.info("goToPayZhku: Finnish");
    }

    @Test
    public void zhku() throws InterruptedException {
        LOGGER.info("zhku: Start");
        ZhkuPage zhku = new ZhkuPage(getDriver(),getBuilder());
        getDriver().get("https://www.tinkoff.ru/zhku-moskva/");
        zhku.changeToPayZhku();
        Thread.sleep(10000);
        LOGGER.info(getDriver().findElement(By.xpath("//h1[@data-qa-file='PageFrame']")).getAttribute("innerText"));
        LOGGER.info("zhku: Finnish");
    }
    @Test
    public void checkErrors() throws InterruptedException {
        LOGGER.info("checkErrors: Start");
        ZhkuPage zhku = new ZhkuPage(getDriver(),getBuilder());
       zhku();
       zhku.payerCodeFillIn("");
       zhku.periodFillIn("56.7675");
       zhku.saveMoneyFillIn("-1");
       zhku.sumMoneyFillIn("1400000");
       zhku.checkForError("Поле обязательное","Поле заполнено некорректно","Поле заполнено неверно",
               "Максимум — 15 000 ₽");
        LOGGER.info("checkErrors: Finnish");
    }

    @Test
    public void checkPageSearch() throws InterruptedException {
        LOGGER.info("checkPageSearch: Start");
        getDriver().get("https://www.tinkoff.ru/payments/");
        PaymentsPage payPage = new PaymentsPage(getDriver(),getWait());
        ZhkuPage zhku = new ZhkuPage(getDriver(),getBuilder());
//        payPage.searchProvider("ЖКУ-Москва");
//        getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@data-qa-type=\"uikit/dropdown.list\"]"))));
//        WebElement element = getDriver().findElement(By.xpath("//div[@data-qa-type=\"uikit/dropdown.list\"]"));
//        System.out.println(element);
//        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        String page = (String) getDriver().executeScript("return document.getElementsByTagName('html')[0].innerHTML");
//        System.out.println(page);
        payPage.goToSearchFound("ЖКУ-Москва");

        zhku.changeToPayZhku();
        Thread.sleep(10000);
        zhku.checkValidOfPage("Оплатите ЖКУ в Москве без комиссии","Код плательщика за ЖКУ в Москве",
                "За какой период оплачиваете коммунальные услуги");


        LOGGER.info("checkPageSearch: Finnish");
    }

    @Test
    public void changeToDifferentRegion(){
        LOGGER.info("changeToDifferentRegion: Start");
        goToPayment();
        KommunalniePage kom = new KommunalniePage(getDriver(),getWait(),getBuilder());
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        kom.changeRegion("г. Санкт-Петербург");
        Assert.assertEquals("Санкт-Петербурге",kom.getNameOfCurrentRegion());

        LOGGER.info("changeToDifferentRegion: Finnish");
    }



}
