package com.cat.cat.like;

import android.content.Context;


import com.cat.cat.article.Article;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wuzijian on 2017/9/4.
 */

public class ArticleLikeLab {
    private static ArticleLikeLab sArticleLab;
    private List<Article> mArticles;

    public static ArticleLikeLab get(Context context, List<Article> articles) {
        if (sArticleLab == null) {
            sArticleLab = new ArticleLikeLab(context, articles);
        }
        return sArticleLab;
    }

    private ArticleLikeLab(Context context, List<Article> articles) {
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

    public void setArticles(List<Article> articles) {
        mArticles = articles;
    }
}
