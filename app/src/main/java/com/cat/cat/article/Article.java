package com.cat.cat.article;

/**
 * Created by wuzijian on 2017/9/4.
 */

public class Article {
    private int mId;
    private String mTitle;
    private String mAuthor;
    private String mContent;
    private String mKind;
    private String mDate;
    private String mPic;
    private String mReference;
    private String mWebURL;

    public Article() {}

    public String getWebURL() {
        return mWebURL;
    }

    public void setWebURL(String webURL) {
        mWebURL = webURL;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getPic() {
        return mPic;
    }

    public void setPic(String pic) {
        mPic = pic;
    }

    public String getReference() {
        return mReference;
    }

    public void setReference(String reference) {
        mReference = reference;
    }
}
