package org.zerock.test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class SelfTest {

    @Test
    public void crawlingTest() throws Exception {

        String path = "http://t1.daumcdn.net/webtoon/bulk/b2d9ff78c7c9214425b9d48ea28179e9561d0325";
        URL url = new URL(path);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        urlConn.setUseCaches(false);

        InputStream in = urlConn.getInputStream();

        //write
        File fos = new File("C:\\crawlingPic\\webtoonSample4.jpg");
        Files.copy(in, fos.toPath());

    }

}
