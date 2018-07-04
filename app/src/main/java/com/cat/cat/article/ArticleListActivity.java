package com.cat.cat.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.cat.cat.R;


public class ArticleListActivity extends FragmentActivity {

    //    private ImageButton mButton1;
//    private ImageButton mButton2;
//    private ImageButton mButton3;
    protected Fragment createFragment() {
        return new ArticleListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            //启动一个新的Fragment事务，将新建的Fragment添加到队列当中
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
//        mButton1 = findViewById(R.id.button_article_1);
//        mButton1.setImageResource(R.drawable.luyesanpx);
//
//        mButton2 = findViewById(R.id.button_article_2);
//        mButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                resetImgs();//
//                mButton2.setImageResource(R.drawable.sousuo3);
//                intent.setClass(getApplicationContext(), ArticleLikeListActivity.class);
//                startActivity(intent);
//            }
//        });

//        mButton3 = findViewById(R.id.button_article_3);
//        mButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                resetImgs();//
//                mButton3.setImageResource(R.drawable.setting3);//
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                intent.setClass(getApplicationContext(), SettingActivity.class);
//                startActivity(intent);
//            }
//        });
    }
//    private void resetImgs()
//    {
//        mButton1.setImageResource(R.drawable.pluyesanpx);
//        mButton2.setImageResource(R.drawable.sousuono3);
//        mButton3.setImageResource(R.drawable.settingno3);
//    }
}
