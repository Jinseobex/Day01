package org.zerock.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;

public class SimpleTest {

    @Test
    public void test1() throws Exception {

        System.out.println("Test1.......");
        Document doc = Jsoup.connect("https://comic.naver.com/webtoon/list.nhn?titleId=748105&weekday=thu").get();

        //System.out.println(doc);

        Elements tds = doc.select(".viewList td img");

        tds.forEach(element -> {
            String imgUrl = element.attr("src");
            System.out.println(imgUrl);
            String title = element.attr("title");
            System.out.println(title);
        });

        }


    @Test
    public void testSave() throws Exception {

    //실제 이미지 경로
        String path = "https://img.ruliweb.com/view/2021/06/22/blackd3509g3a_1150x380_1.jpg";
        URL url = new URL(path);

    //read
    InputStream in = url.openStream();

    //write
        File fos = new File("C:\\crawlingPic\\sample.jpg");

        Files.copy(in, fos.toPath());

    }

}

