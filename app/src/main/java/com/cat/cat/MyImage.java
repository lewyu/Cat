package com.cat.cat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class MyImage extends Activity {

    private String image_url;


    private static final String TAG = "HttpClient";

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        mImageView = (ImageView) findViewById(R.id.item_image);

        file2Bitmap();
    }

    /**
     * 使用HttpClient来进行GET请求
     */
    private void httpClientGet() {

        new Thread() {
            @Override
            public void run() {

                // 基础参数对象
                BasicHttpParams params = new BasicHttpParams();
                // 使用静态方法
                // 设置连接时长
                // 第一个参数：BasicHttpParams对象
                // 第二个参数：设置时间
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

                // 获得HttpClient对象
                HttpClient client = new DefaultHttpClient(params);

                // 实例化Get请求对象
                HttpGet get = new HttpGet(
                        "http://v.juhe.cn/toutiao/index?type=tiyu&key=5465c4c5d60f72c3d756a9f1a9b8437d");

                try {
                    // 执行get请求,获得返回的数据封装
                    HttpResponse response = client.execute(get);

                    // 判断状态码
                    if (response.getStatusLine().getStatusCode() == 200) {
                        // 获取字节输入流
                        InputStream is = response.getEntity().getContent();
                        // 转换为字符流输入流
                        InputStreamReader isr = new InputStreamReader(is);
                        // 缓冲字符输入流
                        BufferedReader br = new BufferedReader(isr);

                        // JSON数据拼接用的字符串类
                        StringBuffer sb = new StringBuffer();
                        // “水瓢”
                        String buffer = new String();

                        // 循环读取数据
                        while ((buffer = br.readLine()) != null) {
                            // 追加拼接的字符串
                            sb.append(buffer);
                        }
                        // 转换成字符串，打印检测结果是否正常
                        Log.d(TAG, sb.toString());

                        // 关闭输入流
                        br.close();

                    } else {
                        Log.d(TAG, "Error code:" + response.getStatusLine().getStatusCode());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "" + e);
                }

            }
        }.start();

    }

    /**
     * 从网络获取图片的输入流,并且将输入流转换成Bitmap用于UI显示
     */
    private void getInputStreamFromNet() {
        new Thread() {

            public void run() {

                // 基础参数对象
                BasicHttpParams params = new BasicHttpParams();
                // 使用静态方法
                // 设置连接时长
                // 第一个参数：BasicHttpParams对象
                // 第二个参数：设置时间
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

                // 获得HttpClient对象
                HttpClient client = new DefaultHttpClient(params);

                HttpGet get = new HttpGet(
                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530532248868&di=0fccebc2c4911e0520b9b84db949b0b3&imgtype=0&src=http%3A%2F%2Fwww.sdtajg.com%2FimageRepository%2F401a6292-6cf9-4d0f-850a-29fbb08c5271.jpg");
                try {
                    // 执行get请求,获得返回的数据封装
                    HttpResponse response = client.execute(get);

                    // 判断状态码
                    if (response.getStatusLine().getStatusCode() == 200) {
                        // 获取字节输入流
                        InputStream is = response.getEntity().getContent();
                        // TODO 转Bitmap 或者 文件存储
                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        // 显示在控件（主线程）
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // 主线程要执行的代码
                                mImageView.setImageBitmap(bitmap);
                            }
                        });

                        // 准备一个输出流用于输出Bitmap到文件
                        FileOutputStream fos = new FileOutputStream(getCacheDir() + File.separator + "demo.jpg");
                        // 套缓冲流
                        BufferedOutputStream bos = new BufferedOutputStream(fos);

                        // 将Bitmap缓存成文件
                        // 第一个参数：要压缩的格式
                        // 第二个参数：压缩品质，0是最小文件，100是最高品质
                        //
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

                        is.close();

                    } else {
                        Log.d(TAG, "Error code:" + response.getStatusLine().getStatusCode());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "" + e);
                }

            }

        }.start();
    }

    /**
     * 从缓存中读取图片为Bitmap，UI显示
     */
    private void file2Bitmap() {

        new Thread() {

            public void run() {

                // 从文件读取到Bitmap，参数为文件路径
                final Bitmap bitmap = BitmapFactory.decodeFile(getCacheDir() + File.separator + "demo.jpg");
                // 通知主线程显示
                runOnUiThread(new Runnable() {
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });

            }

        }.start();

    }

}