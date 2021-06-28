package org.zerock.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class SimpleTest {

    // naver webtoon crawling
    @Test
    public void testSave2() throws Exception {

        String path = "http://t1.daumcdn.net/webtoon_episode/84c2b3ec6a99b183b24b790c77b58218e84f373c";
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        System.out.println(urlConnection);

        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        // setting like header
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        InputStream in = urlConnection.getInputStream();

        //write
        File fos = new File("C:\\crawlingPic\\webtoonSample3.jpg");
        Files.copy(in, fos.toPath());

    }

    // naver blog crawling
    @Test
    public void test1() throws Exception {

        System.out.println("Test1.......");
        Document doc = Jsoup.connect("https://m.blog.naver.com/gangbuknamc/221987424287").get();

        //System.out.println(doc);

        Elements tds = doc.select(".se-module se-module-image > img");

        tds.forEach(element -> {
            String imgUrl = element.attr("src");
            System.out.println(imgUrl);
            String title = element.attr("title");
            System.out.println(title);
        });

        }
        
    // inputstream/outputstream 사용
    @Test
    public void testSave() throws Exception {

    //실제 이미지 경로
    String path = "https://mblogthumb-phinf.pstatic.net/20131212_106/purejin108_1386794299785rHO3z_JPEG/%B1%CD%BF%A9%BF%EE%B0%AD%BE%C6%C1%F6%BB%E7%C1%F8%B8%F0%C0%BD.jpg?type=w2";
    URL url = new URL(path);

    //read inputstream!!!
    InputStream in = url.openStream();

    //write
    File fos = new File("C:\\crawlingPic\\retrieverTest3.jpg");
    Files.copy(in, fos.toPath());

    }

}

