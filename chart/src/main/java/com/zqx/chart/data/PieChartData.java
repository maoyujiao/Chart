package com.zqx.chart.data;

import com.zqx.chart.anim.Anim;
import com.zqx.chart.event.OnPieItemClickListener;

/**
 * 饼状图属性
 * Created by zqx on 16/6/30.
 */
public class PieChartData {
    private float[] datas;//数据源
    private int[] colors;//数据对应颜色
    private float separationDegree = 2;//item间隔度数
    private int textColor = 0;//文字颜色
    private int textSize = 0 ;//文字大小
    private int animType = -2;
    private OnPieItemClickListener pieItemClickListener;//图表item点击监听

    private PieChartData(Builder builder) {
        setDatas(builder.datas);
        setColors(builder.colors);
        setTextColor(builder.textColor);
        setTextSize(builder.textSize);
        setSeparationDegree(builder.separationDegree);
        setAnimType(builder.animType);
        setPieItemClickListener(builder.pieItemClickListener);
    }

    public static Builder builder() {
        return new Builder();
    }

    public float[] getDatas() {
        return datas;
    }

    public int[] getColors() {
        return colors;
    }

    public float getSeparationDegree() {
        return separationDegree;
    }

    public OnPieItemClickListener getPieItemClickListener() {
        return pieItemClickListener;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setDatas(float[] datas) {
        this.datas = datas;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setSeparationDegree(float separationDegree) {
        this.separationDegree = separationDegree;
    }

    public int getAnimType() {
        return animType;
    }

    public void setAnimType(int animType) {
        this.animType = animType;
    }

    public void setPieItemClickListener(OnPieItemClickListener pieItemClickListener) {
        this.pieItemClickListener = pieItemClickListener;
    }

    public static final class Builder{
        private float[] datas;//数据源
        private int[] colors;//数据对应颜色
        private int textColor = 0;//文字颜色
        private int textSize = 0 ;//文字大小
        private float separationDegree = 2;//item间隔度数
        private int animType = -2;//动画
        private OnPieItemClickListener pieItemClickListener;//图表item点击监听

        public Builder setDatas(float[] datas) {
            this.datas = datas;
            return this;
        }

        public Builder setColors(int[] colors) {
            this.colors = colors;
            return this;
        }

        public Builder setSeparationDegree(float separationDegree) {
            this.separationDegree = separationDegree;
            return this;
        }

        public Builder setPieItemClickListener(OnPieItemClickListener pieItemClickListener) {
            this.pieItemClickListener = pieItemClickListener;
            return this;
        }

        public Builder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setAnimType(int animType) {
            this.animType = animType;
            return this;
        }

        public PieChartData build(){
            return new PieChartData(this);
        }
    }
}
