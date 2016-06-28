package com.zqx.zqxcharts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqx on 2015/9/19.
 *
 * @Describe ：
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected List<T> list = null;
    protected  int layoutId;
    public BaseListViewAdapter(List<T> list,int layoutId) {
        if (list == null) {
            list = new ArrayList<>(0);
        }
        this.list = list;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyListViewHolder holder = MyListViewHolder.get(convertView, parent, layoutId, position);
        convert(holder,list.get(position),position);
        return holder.getConvertView() == null ? LayoutInflater.from(parent.getContext()).inflate(
                layoutId, parent, false) : holder.getConvertView();
    }
    /**
     * 初始化item值
     */
    protected abstract void convert(MyListViewHolder holder,T item,int postion);

    /**
     * 刷新
     * @param list
     */
    public void onRefresh(List<T> list){
        if (list == null) {
            list = new ArrayList<>(0);
        }
        this.list = list;
        this.notifyDataSetChanged();
    }

    /**
     * 删除
     * @param position
     */
    public void delete(int position){
        if (list != null && list.size() > position){
            list.remove(position);
        }
        notifyDataSetChanged();
    }

    /**
     * 删除
     * @param o
     */
    public void delete(T o){
        if (list != null && !list.isEmpty()){
            list.remove(o);
        }
        notifyDataSetChanged();
    }

    public void add(T o){
        if (list != null){
            list.add(o);
        }
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return list;
    }
}
