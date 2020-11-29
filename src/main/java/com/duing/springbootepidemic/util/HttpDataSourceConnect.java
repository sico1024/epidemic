package com.duing.springbootepidemic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 原生类 实现http请求拿到请求数据
 */
public class HttpDataSourceConnect {

    public static String HttpConnectDataSource(String urlStr){
        HttpURLConnection urlConnection = null;
        URL url = null;
        InputStream is = null;
        BufferedReader br = null;
        //存储结果
        StringBuilder result = new StringBuilder();

        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");

            //设置连接时间限制
            urlConnection.setConnectTimeout(15000);
            //设置最大读取时间
            urlConnection.setReadTimeout(60000);

            //设置请求参数
            urlConnection.setRequestProperty("Accept","application/json");
            //发送请求
            urlConnection.connect();

            //判断 相应结果是否正确
            if(urlConnection.getResponseCode()==200){
                //获得输入流
                is = urlConnection.getInputStream();

                br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

                String line;

                while((line=br.readLine())!=null){
                    result.append(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }




}
