package com.zqx.chart.data;

/**
 * 柱状图属性
 * Created by zqx on 16/6/27.
 */
public class HistogramData extends ChartData{
    private int[] rectColor;
    private int rectTextColor;
    private int rectTextSize;

    private HistogramData(Builder builder) {
        setXdata(builder.xdata);
        setYdata(builder.ydata);
        setXpCount(builder.xpCount);
        setYpCount(builder.ypCount);
        setCoordinatesColor(builder.coordinatesColor);
        setxTextSize(builder.xTextSize);
        setyTextSize(builder.yTextSize);
        setxTextColor(builder.xTextColor);
        setyTextColor(builder.yTextColor);
        setyMax(builder.yMax);
        setInterval(builder.interval);
        setAnimType(builder.animType);
        setRectColor(builder.rectColor);
        setRectTextColor(builder.rectTextColor);
        setRectTextSize(builder.rectTextSize);
    }

    public static Builder builder() {
        return new Builder();
    }

    public int[] getRectColor() {
        return rectColor;
    }

    public void setRectColor(int[] rectColor) {
        this.rectColor = rectColor;
    }

    public int getRectTextColor() {
        return rectTextColor;
    }

    public void setRectTextColor(int rectTextColor) {
        this.rectTextColor = rectTextColor;
    }

    public int getRectTextSize() {
        return rectTextSize;
    }

    public void setRectTextSize(int rectTextSize) {
        this.rectTextSize = rectTextSize;
    }

    public static final class Builder{
        private int rectTextSize;
        private int rectTextColor;
        private int[] rectColor;
        private int animType;
        private int interval;
        private int yMax;
        private int yTextColor;
        private int xTextColor;
        private int yTextSize;
        private int xTextSize;
        private int coordinatesColor;
        private int ypCount;
        private int xpCount;
        private float[] ydata;
        private String[] xdata;

        private Builder(){}

        public Builder setRectTextSize(int val) {
            rectTextSize = val;
            return this;
        }

        public Builder setRectTextColor(int val) {
            rectTextColor = val;
            return this;
        }

        public Builder setRectColor(int[] val) {
            rectColor = val;
            return this;
        }

        public Builder setAnimType(int val) {
            animType = val;
            return this;
        }

        public Builder setInterval(int val) {
            interval = val;
            return this;
        }

        public Builder setYMax(int val) {
            yMax = val;
            return this;
        }

        public Builder setYTextColor(int val) {
            yTextColor = val;
            return this;
        }

        public Builder setXTextColor(int val) {
            xTextColor = val;
            return this;
        }

        public Builder setYTextSize(int val) {
            yTextSize = val;
            return this;
        }

        public Builder setXTextSize(int val) {
            xTextSize = val;
            return this;
        }

        public Builder setCoordinatesColor(int val) {
            coordinatesColor = val;
            return this;
        }

        public Builder setYpCount(int val) {
            ypCount = val;
            return this;
        }

        public Builder setXpCount(int val) {
            xpCount = val;
            return this;
        }

        public Builder setYdata(float[] val) {
            ydata = val;
            return this;
        }

        public Builder setXdata(String[] val) {
            xdata = val;
            return this;
        }

        public HistogramData build() {
            return new HistogramData(this);
        }
    }
}
