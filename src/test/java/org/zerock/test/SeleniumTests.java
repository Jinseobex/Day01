package org.zerock.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumTests {

    @Test
    public void testOpen() throws Exception {

        WebDriver driver = new ChromeDriver();

        driver.get("http://gs25.gsretail.com/gscvs/ko/products/event-goods#");

        // ajax 방식 사이트에서 데이터를 가져올 충분한 시간을 주기 위한 쓰레드 슬립
        Thread.sleep(3000);

        // 자바스크립트 실행기. 자바스크립트 쓸 수 있음!
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0, 1000)");

        WebElement ele = driver.findElement(By.cssSelector("#TWO_TO_ONE"));

        ele.click();

        Thread.sleep(2000);

        List<WebElement> list = driver.findElements(By.cssSelector(".prod_list li"));

        list.forEach(webElement -> {
            //System.out.println(webElement);
            WebElement imgEle = webElement.findElement(By.cssSelector("img"));
            System.out.println(imgEle.getAttribute("alt"));
            System.out.println(imgEle.getAttribute("src"));
        });

        

    }

}
