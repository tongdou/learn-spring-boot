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
    public static final String urlString = "https://afsyf.jd.com/sub_afs/waitauditDetail/getCalendar";  //先登录保存cookie

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
        connection.setRequestProperty("Cookie","shshshfpb=033bed412c79ce75c4547332f892542d392153cad1cf3ad4e5b5eeb8d8; shshshfpa=6d6fcda0-6675-fb0d-5709-b0951799faca-1540175881; areaId=1; mt_xid=V2_52007VwMWUFVRVl8ZSRlYDWEDE1FaUFpaHEEebAY0ABdaXVsARhscTV4ZYgEbAEFRAg8aVUxVAWFWEwEJWQBZGnkaXQVuHxJSQVtbSx9IElwEbAYSYl9oUmoXSRFUDWQEFlpUXFpbFk0cVQRjMxJXW18%3D; ipLocation=%u5317%u4eac; ipLoc-djd=1-2810-51081-0.2147483645; cn=0; __jdu=15398547115971026991296; PCSYCityID=1; user-key=dd6ac834-6e03-4055-84f5-6479a6692cd6; jd.erp.lang=zh_CN; _gcl_au=1.1.1293903214.1544163057; cid=NXhENDM4Nm9UMjcwN3RKMjM2OHBaODMyMWlSNjc0MmRIOTUwM2hUMTM2NGVGNTc2; 3AB9D23F7A4B3C9B=F7OE2WQVTD42J3CQKMVRW3MTNY7GSYGUAFSIDQDNQGJTAAZ2J2BXTFHKREVA6QJ2YSH6C2GMOHTGY75MNZTOPYMNQA; unpl=V2_ZzNtbUIERBcnXxJcchpZBWILEgoRV0MXc1xGUHkRDFdmAkYOclRCFXwURldnGVwUZwcZX0BcQhZFCEJkeBhdDGACGltLUnMlfQAoVDYZMgYJAF9tQVdzFXQ4RlB5EVUBYgUSXUZfRRV0C0FccxlfDWAzIl1EZ3NcLVcbDDBABE05R1oZclZzFEUJdh8VGBEFYwEaVEZSRRV1DE5SexhfAm8LEl5KUHMURQs%3d; mba_muid=15398547115971026991296; __jdv=122270672|kong|t_2008609784_|jingfen|1b63cfe98241491fb1137e1539ac00eb|1544513574877; shshshfp=c01276bfd905921aea18766f22e08ccc; lighting=FE842BC4E4714C3B5A28C06720210B5D9B0AAA3E3BAA24568B70EFC9FFBE19BA91FD66399D37AD812220B6A64445AE15E26D5E0F4B861BD52814F055311E9AA4727C007E2782145EB43CF6226AF43603B4F6E1F3B12BF605378BEE6EE18448FABA9E66C8D83A2418BAEA3EE1C964B316AEB550F8F1A8620DDB9B27292EFC1CC3ECD624C9B183B444BB1732602FBF509B5FB8D86251430BF4AA13EE793B415F63; logining=1; erp1.jd.com=620665CB399BFE07F570BB00A681E4863163DBAAB999445EA57007B614F2BA2D4728222170C5AA95A0C1F596F5404A0E996544CCA697739DF9B05B7F0D538219F78DB33A9B41292F95A57B799C1EDC54; sso.jd.com=1bd346ea0cc04f0db5dd89ba398184bc; VC_INTEGRATION_JSESSIONID=6c2d19e1-a9ed-4cea-bf13-d9d167a87f68; __jda=122270672.15398547115971026991296.1539854712.1544589720.1544594627.273; __jdc=122270672; __jdb=122270672.6.15398547115971026991296|273.1544594627; wlfstk_smdl=4a84fk9rpiiwimjdue3alquycmxxogqs; TrackID=1djpQi3R6W13nZSTTU0ulbOxVq6KdkJKHkYoHS8kicQnwwtnpUvBZIttv6TBDV1_4GBBYO5Fqyv0Fmyqlx9i-jrVgYeBZoyKSQS3ckn6Bmi0; thor=B74AD4557CA862E530A4FC2D426BAEEE8E57DCD1985B98FBBEB7F7F6C2EE6ECC4C8F87000F63C41F850BC5739F29668E91BA8DCFDDB5E28CF28BEDB353E207F60C5E0C3C9DF23499EE8DB09435E706768290D889BA5A59890F0E4D5A29AC58E80D39BDEEC59557366B5A14977BA02B636575653A234010D56FE59FCAC5454D72A27A09FAFB61411C617CEDBB80B7AB5B4CC1F483AA4AEBE0EB9948DAAC7B8A33; pinId=hsamRciRdOvfqW2kacqvu7V9-x-f3wj7; pin=jd_436ee1dddfb84; unick=ANKER%E4%B8%AD%E5%9B%BD; ceshi3.com=000; _tp=fcs%2Fsvf5jGyokWKZl%2B%2FQlcMAU41KKvBifKMQOoZW5Ug%3D; _pst=jd_436ee1dddfb84");
//        connection.setRequestProperty("Host","afsyf.jd.com");
//        connection.setRequestProperty("Origin","https://afsyf.jd.com");
//        connection.setRequestProperty("Referer","https://afsyf.jd.com/sub_afs/waitauditDetail/initWaitauditDetail?afsApplyId=364480942&afsServiceId=520772510&afsServiceStatus=10001&token=98BA8E40C5076DBBD6505330006DD41686E1901D67093855B5AE3C03B4B8586E");
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
