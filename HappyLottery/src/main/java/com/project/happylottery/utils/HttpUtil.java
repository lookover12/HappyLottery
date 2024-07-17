package com.project.happylottery.utils;

import okhttp3.*;

import java.util.Map;

public class HttpUtil {

    private HttpUtil() {}

    public static String get(String url) {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headers != null) {
            for(Map.Entry<String, String> entry : headers.entrySet()) {
                headersBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(urlBuilder.build());
        requestBuilder.headers(headersBuilder.build());

        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();
            Response response = client.newCall(requestBuilder.build()).execute();
            assert response.body() != null;
            return response.body().string();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
