package com.zqx.chart.data;

import com.zqx.chart.anim.Anim;

/**
 * 折线图属性
 * Created by zqx on 16/6/27.
 */
public class LineChartData extends ChartData {
    private static final int STROKE = 0;
    private static final int FILL = 1;
    private int pointColor;
    private float pointSize;
    private int pointTextColor;
    private int pointTextSize;
    private int lineColor;
    private float lineWidth;
    private int linePathStyle = -1;

    private LineChartData(Builder builder) {
        this.xdata = builder.xdata;
        this.ydata = builder.ydata;
        this.xpCount = builder.xpCount;
        this.ypCount = builder.ypCount;
        this.coordinatesColor = builder.coordinatesColor;
        this.xTextSize = builder.xTextSize;
        this.yTextSize = builder.yTextSize;
        this.xTextColor = builder.xTextColor;
        this.yTextColor = builder.yTextColor;
        this.yMax = builder.yMax;
        this.interval = builder.interval;
        this.animType = builder.animType;
        this.pointColor = builder.pointColor;
        this.pointSize = builder.pointSize;
        this.pointTextColor = builder.pointTextColor;
        this.pointTextSize = builder.pointTextSize;
        this.lineColor = builder.lineColor;
        this.lineWidth = builder.lineWidth;
        this.linePathStyle = builder.linePathStyle;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }

    public float getPointSize() {
        return pointSize;
    }

    public void setPointSize(float pointSize) {
        this.pointSize = pointSize;
    }

    public int getPointTextColor() {
        return pointTextColor;
    }

    public void setPointTextColor(int pointTextColor) {
        this.pointTextColor = pointTextColor;
    }

    public int getPointTextSize() {
        return pointTextSize;
    }

    public void setPointTextSize(int pointTextSize) {
        this.pointTextSize = pointTextSize;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getLinePathStyle() {
        return linePathStyle;
    }

    public void setLinePathStyle(int linePathStyle) {
        this.linePathStyle = linePathStyle;
    }

    public final static class Builder{
        private String[] xdata;
        private float[] ydata;
        private int xpCount;
        private int ypCount;
        private int coordinatesColor;
        private int xTextSize;
        private int yTextSize;
        private int xTextColor;
        private int yTextColor;
        private int yMax;
        private int interval;
        private int animType = Anim.ANIM_TRANSLATE;
        private int pointColor;
        private float pointSize;
        private int pointTextColor;
        private int pointTextSize;
        private int lineColor;
        private float lineWidth;
        private int linePathStyle = -1;

        private Builder(){}

        public Builder setXdata(String[] xdata) {
            this.xdata = xdata;
            return this;
        }

        public Builder setYdata(float[] ydata) {
            this.ydata = ydata;
            return this;
        }

        public Builder setXpCount(int xpCount) {
            this.xpCount = xpCount;
            return this;
        }

        public Builder setYpCount(int ypCount) {
            this.ypCount = ypCount;
            return this;
        }

        public Builder setCoordinatesColor(int coordinatesColor) {
            this.coordinatesColor = coordinatesColor;
            return this;
        }

        public Builder setxTextSize(int xTextSize) {
            this.xTextSize = xTextSize;
            return this;
        }

        public Builder setyTextSize(int yTextSize) {
            this.yTextSize = yTextSize;
            return this;
        }

        public Builder setxTextColor(int xTextColor) {
            this.xTextColor = xTextColor;
            return this;
        }

        public Builder setyTextColor(int yTextColor) {
            this.yTextColor = yTextColor;
            return this;
        }

        public Builder setyMax(int yMax) {
            this.yMax = yMax;
            return this;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder setAnimType(int animType) {
            this.animType = animType;
            return this;
        }

        public Builder setPointColor(int pointColor) {
            this.pointColor = pointColor;
            return this;
        }

        public Builder setPointSize(float pointSize) {
            this.pointSize = pointSize;
            return this;
        }

        public Builder setPointTextColor(int pointTextColor) {
            this.pointTextColor = pointTextColor;
            return this;
        }

        public Builder setPointTextSize(int pointTextSize) {
            this.pointTextSize = pointTextSize;
            return this;
        }

        public Builder setLineColor(int lineColor) {
            this.lineColor = lineColor;
            return this;
        }

        public Builder setLineWidth(float lineWidth) {
            this.lineWidth = lineWidth;
            return this;
        }

        public Builder setLinePathStyle(int linePathStyle) {
            this.linePathStyle = linePathStyle;
            return this;

        }

        public LineChartData build(){
            return new LineChartData(this);
        }
    }
}
