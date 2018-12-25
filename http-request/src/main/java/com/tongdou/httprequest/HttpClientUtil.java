package com.tongdou.httprequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpClientUtil {
    public static final String urlString = "https://afsyfjdcom/sub_afs/waitauditDetail/getCalendar";  //先登录保存cookie

    public void doPost(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true); //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
        connection.setDoInput(true); // 设置连接输入流为true
        connection.setRequestMethod("POST"); // 设置请求方式为post
        connection.setUseCaches(false); // post请求缓存设为false
        connection.setInstanceFollowRedirects(true); //// 设置该HttpURLConnection实例是否自动执行重定向
        // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
        // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
//        connection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
//        connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
//        connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9,ja;q=0.8,en;q=0.7,zh-TW;q=0.6");
//        connection.setRequestProperty("Connection","keep-alive");
//        connection.setRequestProperty("Content-Length","121");
//        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("Cookie","");
//        connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
//        connection.setRequestProperty("X-Requested-With","XMLHttpRequest");

        connection.connect();

        // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
        DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
        String parm = URLEncoder.encode("orderId=49440944706&provinceId=2&cityId=2830&countyId=51808&townId=0&skuId=1900210&token=A7226A6DA45A58697FA767DF733AF6F9", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码
        dataout.writeBytes(parm);
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder(); // 用来存储响应数据
        // 循环读取流,若不到结尾处
        while ((line = bf.readLine()) != null) {
            sb.append(bf.readLine());
        }
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
        connection.disconnect(); // 销毁连接
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        HttpClientUtil hcu = new HttpClientUtil();
        hcu.doPost(urlString);
    }
}
