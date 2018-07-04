package com.cat.cat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsListActivity extends Activity {
    private ArrayList<String> mDataList;
    private ArrayList<String> mUrlList;

    private ListView mListView;
    //自定义适配器
    //    private MyAdapter myAdapter;
    // P:适配器
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        initData();
        initAdapter(); //presenter
        initView();
    }

    private void initData() {
        mDataList = new ArrayList<String>();
        mDataList.add("中国人带领亚洲田径迎来革命！上世纪变态强纪录现在要逐个拿下？");
        mDataList.add("2018年度辽宁省银行业普及金融知识万里行”活动成功启动");
        mDataList.add("台反年改团体举办誓师大会：启动全台22县市革命");
        mDataList.add("点击有惊喜");


        mUrlList = new ArrayList<>();
        mUrlList.add("http:\\/\\/mini.eastday.com\\/mobile\\/180702090023280.html");
        mUrlList.add("http:\\/\\/mini.eastday.com\\/mobile\\/180702085938123.html");
        mUrlList.add("https://www.baidu.com/");
        mUrlList.add("http://lewyu.xin/#blog/");




    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter); //自定义Adapter

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsListActivity.this, WebViewActivity.class);
                intent.putExtra("url", mUrlList.get(position));
                startActivity(intent);
            }
        });

    }

    public void initAdapter() {
//        myAdapter = new MyAdapter(this, mImageList, mTextList);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDataList);
    }

}
