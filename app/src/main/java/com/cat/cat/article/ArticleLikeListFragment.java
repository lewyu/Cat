package com.cat.cat.article;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import java.util.List;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import com.cat.cat.R;
import com.cat.cat.like.ArticleLikeLab;
import com.squareup.picasso.Picasso;


public class ArticleLikeListFragment extends Fragment{
    private static final String TAG = "ArticleLikeListFragment";
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private static String type;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRecyclerView = view.findViewById(R.id.article_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.i(TAG, "onCreateView() called");
        String newType = getActivity().getIntent().getStringExtra("type");
        if (newType != null) {
            type = newType;
        }
        Log.i(TAG, "type: " + type);
        new FetchArticlesTask().execute(type);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() called");
        if (savedInstanceState!= null) {
            type = savedInstanceState.getString("type");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("type", type);
    }

    private void updateUI(List<Article> articles) {

        ArticleLikeLab articleLab = ArticleLikeLab.get(getActivity(), articles);
        articleLab.setArticles(articles);
        List<Article> articleList = articleLab.getArticles();

        if (mAdapter == null) {
            mAdapter = new ArticleAdapter(articleList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ArticleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Article mArticle;
        private TextView mTitle;
        private TextView mDate;
        private ImageView mImageView;

        public ArticleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = itemView.findViewById(R.id.list_item_article_title);
            mDate = itemView.findViewById(R.id.list_item_article_date);
            mImageView = itemView.findViewById(R.id.list_item_article_image);

        }
        //在Adapter中调用
        public void bindArticle(Article article) {
            mArticle = article;
            Picasso.with(getActivity())
                    .load(mArticle.getPic())
                    .into(mImageView);
            mTitle.setText(mArticle.getTitle());
            mDate.setText(mArticle.getDate());
        }

        //实现点击跳转
        @Override
        public void onClick(View v) {
            Intent intent = ArticlePageActivity
                    .newIntent(getActivity(), mArticle.getWebURL());
            Log.i(TAG, "----ListOnClick()----");
            startActivity(intent);
        }

    }

    private class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
        private List<Article> mArticles;

        public ArticleAdapter(List<Article> articles) {
            mArticles = articles;
        }

        @Override
        public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_article, parent, false);

            return new ArticleHolder(view);
        }

        @Override
        public void onBindViewHolder(ArticleHolder holder, int position) {
            Article article = mArticles.get(position);
            holder.bindArticle(article);
        }

        @Override
        public int getItemCount() {
            return mArticles.size();
        }
    }

    private class FetchArticlesTask extends AsyncTask<String, Void, List<Article>> {


        @Override
        protected List<Article> doInBackground(String... strings) {
            JsonFetchArticle jsonFetchArticle = new JsonFetchArticle();
            Log.i(TAG, "type in background : " + strings[0]);
            List<Article> articles = jsonFetchArticle.fetchArticles(strings[0]);
            return articles;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            updateUI(articles);
        }
    }


}
