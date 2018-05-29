
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package com.zx.common.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpClientUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("organizationId", "123456");
//        String url = "http://erp.joysuch.com:19999";
        String url = "http://ningjj.mppstore.com:8081/exam/getDefaultPaper";
        String postWithJson = doPostWithJson(url, jsonObject.toString());
        System.out.println(postWithJson);
    }

    public static String doPostWithJson(String url, Map<String, Object> params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        return doPostWithJson(url, jsonObject.toString());
    }

    public static String doPostWithJson(String url, String json){
        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");

            // 构建消息实体
            StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);

            byte[] content = getContent(response);
            String resp = new String(content, "utf-8");
            logger.info("resp : " + resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static byte[] getContent(HttpResponse response)
            throws IOException {
        InputStream result = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = resEntity.getContent();
                int len = 0;
                while ((len = result.read()) != -1) {
                    out.write(len);
                }
                return out.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("getContent异常", e);
        } finally {
            out.close();
            if (result != null) {
                result.close();
            }
        }
        return null;
    }

    public static String httpGet(String httpUrl, Map<String, Object> params){
        StringBuilder builder = new StringBuilder("?");
        for (Map.Entry<String, Object> e : params.entrySet()) {
            builder.append(e.getKey());
            builder.append("=");
            builder.append(e.getValue());
        }
        httpUrl = httpUrl + builder.toString();
        HttpGet httpGet = new HttpGet(httpUrl);
        CloseableHttpResponse chResponse = null;
        String result = null;
        try {
            chResponse = HttpClients.createDefault().execute(httpGet);
            if(chResponse.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(chResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(chResponse !=null){
                try {
                    chResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(result !=null){
            System.out.println("有结果返回result=="+result);
            return result;
        }else{
            System.out.println("请求没有结果返回");
            return "";
        }
    }
}