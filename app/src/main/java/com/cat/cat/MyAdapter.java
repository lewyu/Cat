package com.cat.cat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {


    //全局变量
    private Context mContext;
    private List<Integer> mImageList;
    private List<String> mTextList;

    public MyAdapter(Context context, List<Integer> imageList, List<String> textList) {
        mContext = context;
        mImageList = imageList;
        mTextList = textList;

    }

    @Override
    public int getCount() {
        //返回数据有多少
        return mTextList.size();
    }

    @Override
    public Object getItem(int position) {
        //可以用于返回Item的数据
        return mTextList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //getView用于负责每个Item的数据设置
    //position：当前绘制的Item的徐序号
    //convertView：
    //parent：Item所在的容器控件
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position会自增

        ViewHolder viewHolder;
        ImageView image;
        TextView text;
        View linearLayout = convertView;//重用，进行优化一
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            linearLayout = inflater.inflate(R.layout.item_layout, null);
            //找出Item
            image = (ImageView) linearLayout.findViewById(R.id.item_image);
            text = (TextView) linearLayout.findViewById(R.id.item_text);

            viewHolder = new ViewHolder(image, text);

            linearLayout.setTag(viewHolder);
        } else {
            //布局重用时
            //先从布局对象中取出viewHolder
            viewHolder = (ViewHolder) linearLayout.getTag();
            //取出之前存进去的对象
            image = viewHolder.mImage;
            text = viewHolder.mText;
        }


        //
        image.setImageResource(mImageList.get(position));
        text.setText(mTextList.get(position));

        return linearLayout;
    }


    //内部类，多个控件封装
    class ViewHolder {
        public ImageView mImage;
        public TextView mText;

        public ViewHolder(ImageView img, TextView txt) {
            mImage = img;
            mText = txt;

        }

    }


}
