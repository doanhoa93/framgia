package com.framgia.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

@Slf4j
@Component
public class RequestChatWorkBuilder {

    @Value("${chatwork.api}")
    private String chatworkApi;

    @Value("${chatwork.apiKey}")
    private String chatworkApiKey;

    public String postForm(String path,  Map<String, String> mapParams) throws IOException {
        String response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(chatworkApi + path);
            httpPost.addHeader("X-ChatWorkToken", chatworkApiKey);
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");

            List<NameValuePair> params = new ArrayList<>();

            for(Map.Entry<String, String> entry : mapParams.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(params));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = client.execute(httpPost, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        return response;
    }
}
