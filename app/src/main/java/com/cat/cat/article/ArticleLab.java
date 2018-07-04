package com.cat.cat.article;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuzijian on 2017/9/4.
 */

public class ArticleLab {
    private static ArticleLab sArticleLab;
    private List<Article> mArticles;

    public static ArticleLab get(Context context, List<Article> articles) {
        if (sArticleLab == null) {
            sArticleLab = new ArticleLab(context, articles);
        }
        return sArticleLab;
    }

    private ArticleLab(Context context, List<Article> articles) {
        mArticles = new ArrayList<>();
        //在这里进行数据读取、解析、存放
        mArticles = articles;
    }

    public List<Article> getArticles() {
        return mArticles;
    }

    public Article getArticle(int id) {
        for (Article article: mArticles
             ) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }


}
