package com.lucien.dap.framework.core.network;

import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
@Component
public class HttpConnectionManager {

    PoolingHttpClientConnectionManager cm = null;

    private static int connectionPoolTimeout = 10000; //从连接池中去连接的超时时间
    private static int connectTimeOut = 15000;//连接主机超时时间
    private static int socketTimeOut = 15000;//返回数据超时时间

    private static int maxTotal = 200;//连接池最大并发数
    private static int defaultMaxPerRoute = 200; //单路由最大并发数

    @PostConstruct
    public void init() {

        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(defaultMaxPerRoute);

    }

    public CloseableHttpClient getHttpClient() {

        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true)
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut)
                .setConnectionRequestTimeout(connectionPoolTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return httpClient;
    }

    public CloseableHttpClient getHttpClient(int socketTimeOut) {

        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true)
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut)
                .setConnectionRequestTimeout(connectionPoolTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return httpClient;
    }

    public CloseableHttpClient getHttpClient(int socketTimeOut, int connectTimeOut) {

        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true)
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut)
                .setConnectionRequestTimeout(connectionPoolTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return httpClient;
    }

    public CloseableHttpClient getProxyHttpClient(String ipAddress, Integer port) throws Exception {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();

        HttpHost httpHost = new HttpHost(ipAddress, port, "https");
        // 信任所有
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true)
                .setSocketTimeout(socketTimeOut * 4)
                .setConnectTimeout(connectTimeOut * 4)
                .setConnectionRequestTimeout(connectionPoolTimeout * 4)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setProxy(httpHost)
                .setDefaultCredentialsProvider(credsProvider)
                .setSSLSocketFactory(sslsf)
                .build();
        return httpclient;
    }

    public CloseableHttpClient getHttpClient(String username, String password) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        org.apache.http.auth.Credentials credentials = new org.apache.http.auth.UsernamePasswordCredentials(username, password);
        credsProvider.setCredentials(org.apache.http.auth.AuthScope.ANY, credentials);

        RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true)
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut)
                .setConnectionRequestTimeout(connectionPoolTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        return httpClient;
    }

    /**
     * get方式提交,url中请拼接好参数
     *
     * @param url
     * @param heads
     * @return
     */
    public String doGet(String url, Map<String, String> heads) {
        CloseableHttpResponse response = null;
        try {
            url = URLEncoder.encode(url, "utf-8");
            CloseableHttpClient httpClient = getHttpClient();
            HttpGet get = new HttpGet();
            get.setURI(new URI(url));
            if (heads != null && heads.size() > 0) {
                for (Map.Entry<String, String> entry : heads.entrySet()) {
                    get.addHeader(entry.getKey(), entry.getValue());
                }
            }
            response = httpClient.execute(get);
        } catch (Exception e) {
            log.error("<<<<发送http请求出错,url={}", url, e);
            throw new ApplicationException(RetEnum.NetConnectError);
        }
        if (response != null) {
            return converResponse(response.getEntity());
        }
        return null;
    }

    /**
     * 发送post请求,requestBody方式
     * @param url
     * @param requestBody
     * @param heads
     * @return
     */
    public String doPost(String url, String requestBody, Map<String, String> heads) {
        CloseableHttpResponse response = null;
        try {
            url = URLEncoder.encode(url, "utf-8");
            CloseableHttpClient httpClient = getHttpClient();
            HttpPost post = new HttpPost();
            if (heads != null && heads.size() > 0) {
                for (Map.Entry<String, String> entry : heads.entrySet()) {
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            post.setURI(new URI(url));
            HttpEntity entity = new StringEntity(requestBody);
            post.setEntity(entity);
            response = httpClient.execute(post);
        } catch (Exception e) {
            log.error("<<<<发送http请求出错,url={}", url, e);
            throw new ApplicationException(RetEnum.NetConnectError);
        }
        if (response != null) {
            return converResponse(response.getEntity());
        }
        return null;
    }

    private String converResponse(HttpEntity entity) {
        try {
            InputStream content = entity.getContent();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len = -1;
            while ((len = content.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            byte[] output = outputStream.toByteArray();
            return new String(output);
        } catch (IOException e) {
            log.error("<<<<http连接转换返回参数异常");
            return null;
        }
    }
}
