package org.zerock.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.lang.model.util.Elements;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SelfSeleniumTest {

    @Test
    public void selfSeleniumTest() throws Exception {

        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
        options.addArguments("headless");

        //드라이버 생성, 드라이버 하나에 브라우저 하나씩이라 생각하자
        WebDriver driver = new ChromeDriver(options);
        //웹툰 리스트창 열기
        driver.get("http://webtoon.daum.net/webtoon/view/odyssey");
        Thread.sleep(3000);

        //리스트 url이 포함된 요소 선택
        List<WebElement> Eles = driver.findElements(By.cssSelector(".list_update a"));

        //요소에서 각 화의 url을 뽑고 저장할 경로 및 폴더 생성
        Eles.forEach(webElement -> {
            String newFolder = webElement.getText();
            File savingPath = new File("C:\\crawlingPic\\"+newFolder);
            String url = webElement.getAttribute("href");

            if(!savingPath.exists()) {
                savingPath.mkdirs();
            }

            //각 화 페이지로 진입할 드라이버 생성
            WebDriver webtoonDriver = new ChromeDriver(options);
            //위에서 생성한 각 화의 url 담기
            webtoonDriver.get(url);
            //웹툰 이미지 리스트에 담기
            List<WebElement> picList = webtoonDriver.findElements(By.cssSelector(".cont_view img"));
            //담은 후 이미지의 주소를 담은 src 선택 후 string으로 저장
            picList.forEach(webElement1 -> {
                String imgUrl = webElement1.getAttribute("src");

                //저장한 이미지 url들을 날짜로 이름을 조합해서 파일로 저장.
                try {
                    URL webtoonUrl = new URL(imgUrl);
                    HttpURLConnection urlConn = (HttpURLConnection) webtoonUrl.openConnection();
                    InputStream in = urlConn.getInputStream();
                    String nowDate = new SimpleDateFormat("YYYYMMddHHmmssSSSSSS").format(new Date());
                    String newPath = savingPath.getPath()+"\\sample"+nowDate+".jpg";
                    File fos = new File(newPath);
                    Files.copy(in, fos.toPath());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        });
    }
}
