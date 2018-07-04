package com.cat.cat.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cat.cat.R;



public class ArticlePageActivity extends AppCompatActivity {
    private static final String ARG_URI = "article_page_url";

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, ArticlePageActivity.class);
        intent.putExtra(ARG_URI, url);
        return intent;
    }

    public Fragment createFragment() {
        return ArticlePageFragment.newInstance(getIntent().getStringExtra(ARG_URI));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container2);
        if (fragment == null) {
            fragment = createFragment();
            //启动一个新的Fragment事务，将新建的Fragment添加到队列当中
            fm.beginTransaction()
                    .add(R.id.fragment_container2, fragment)
                    .commit();
        }
    }
}
