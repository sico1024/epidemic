package com.duing.springbootepidemic.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;

public class HttpClientUtil {


    public static String doGet(String urlStr){
        CloseableHttpClient httpClient = null;

        CloseableHttpResponse response = null;

        String result = null;

        try{
            httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(urlStr);

            httpGet.addHeader("Accept","application/json");

            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();


            httpGet.setConfig(config);

            response = httpClient.execute(httpGet);

            HttpEntity e = response.getEntity();

            result = EntityUtils.toString(e);

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
