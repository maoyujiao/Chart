package com.zqx.zqxcharts.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zqx on 2015/9/19.
 *
 * @Describe ：listview通用holder封装
 */
public class MyListViewHolder {
    private final SparseArray<View> mViews;
    private final View mConvertView;

    private MyListViewHolder(View convertView,ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        convertView = LayoutInflater.from(parent.getContext()).inflate(
                layoutId, parent, false);
        mConvertView = convertView;
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     */
    public static MyListViewHolder get(View convertView, ViewGroup parent,
                                     int layoutId, int position) {
        if (convertView == null) {
            return new MyListViewHolder(convertView,parent, layoutId, position);
        } else {
            return (MyListViewHolder) convertView.getTag();
        }
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        if (mConvertView != null) {
            return mConvertView;
        }
        return null;
    }


}
