package com.cat.cat.article;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//从指定URL对Json数据获取  字节流->字符串
//解析Json数据


public class JsonFetchArticle {
    private static final String TAG = "FetchJson";

    //getUrlBytes()：从指定URL获取原始数据并返回一个字节流数组
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        java.net.URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(5000);
        connection.setRequestMethod("GET");

        try {
            //写入
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //读出
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }

    }

    //将getUrlBytes()方法返回的结果转化为String
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    //解析获得的Json数据
    private void parseJson(List<Article> articles, JSONObject jsonBody) {
        try {
            JSONObject resultObject = jsonBody.getJSONObject("result");
            JSONArray jsonArray = resultObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                Article article = new Article();
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                article.setId(i);
                article.setTitle(jsonObject.getString("title"));
                article.setDate(jsonObject.getString("date"));
                article.setPic(jsonObject.getString("thumbnail_pic_s"));
                article.setWebURL(jsonObject.getString("url"));
                articles.add(article);
            }
            Log.i(TAG, "picUrl:" + articles.get(0).getPic());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //该类主要提供的方法：从指定URL中获取数据，并将解析后的数据存入List中
    public List<Article> fetchArticles(String type) {
        List<Article> articles = new ArrayList<>();
        String url = Uri.parse("http://v.juhe.cn/toutiao/index")
                .buildUpon()
                .appendQueryParameter("type", type)
                .appendQueryParameter("key", "5465c4c5d60f72c3d756a9f1a9b8437d")
                .build().toString();
        try {
            String jsonString = getUrlString(url);

            Log.i(TAG, "Url:" + url);
            Log.i(TAG, "Received json:" + jsonString);

            JSONObject jsonBody = new JSONObject(jsonString);
            parseJson(articles, jsonBody);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articles;
    }

}