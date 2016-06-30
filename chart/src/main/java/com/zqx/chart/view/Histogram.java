package com.zqx.chart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.zqx.chart.R;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.ChartData;
import com.zqx.chart.data.HistogramData;

import java.text.DecimalFormat;

/**
 * 柱状图
 * Created by zqx on 16/6/25.
 */
public class Histogram extends Chart {
    private int[] rect_color;//矩形颜色
    private int rect_text_color;//矩形文字颜色
    private int rect_text_size;//矩形文字尺寸

    public Histogram(Context context) {
        super(context);
    }

    public Histogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context,attrs);
    }

    public Histogram(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle(context,attrs);
    }
    /*
     * 初始化样式属性
     */
    private void initStyle(Context context,AttributeSet attrs) {
        TypedArray types = context.obtainStyledAttributes(attrs, R.styleable.zqxchart_histogram);
        coordinates_color = types.getColor(R.styleable.zqxchart_histogram_hCoordinatesColor,Color.RED);
        rect_text_color = types.getColor(R.styleable.zqxchart_histogram_rectTextColor,Color.BLACK);
        rect_text_size = types.getInteger(R.styleable.zqxchart_histogram_rectTextSize,24);
        x_text_color = types.getColor(R.styleable.zqxchart_histogram_hxTextColor,Color.BLACK);
        y_text_color = types.getColor(R.styleable.zqxchart_histogram_hyTextColor,Color.BLACK);
        x_text_size = types.getInteger(R.styleable.zqxchart_histogram_hxTextSize,30);
        y_text_size = types.getInteger(R.styleable.zqxchart_histogram_hyTextSize,26);
        xpCount = types.getInteger(R.styleable.zqxchart_histogram_hxPointCount,7);
        ypCount = types.getInteger(R.styleable.zqxchart_histogram_hyPointCount,5);
        rect_color = getResources().getIntArray(R.array.histogram_color);
        animType = types.getInteger(R.styleable.zqxchart_histogram_hanimType, Anim.ANIM_NONE);
        types.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHistogram(canvas);
    }

    /*
     * 画柱状图
     */
    private void drawHistogram(Canvas canvas) {
        //如果没有设置x轴数据
        if (xData == null){
            throw new NullPointerException("x轴数据源不能为空!");
        }
        //如果没有设置y轴数据
        if (yData == null){
            throw new NullPointerException("y轴数据源不能为空!");
        }
        Paint histogramPaint = new Paint();
        histogramPaint.setAntiAlias(true);
        histogramPaint.setStyle(Paint.Style.FILL);
        histogramPaint.setStrokeWidth((float) 5.0);
        //矩形上具体数据画笔
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(rect_text_size);
        textPaint.setColor(rect_text_color);
        DecimalFormat formater = new DecimalFormat("0.000");
        for (int i=0; i<xpCount; i++){
            try {
                histogramPaint.setColor(rect_color == null
                        ? getResources().getColor(R.color.colorPrimary)
                        : rect_color[i]);
            }catch (ArrayIndexOutOfBoundsException e){
                histogramPaint.setColor(getResources().getColor(R.color.colorPrimary));
            }
            int alpha = anims[i].getAlpha();
            textPaint.setAlpha(alpha);
            histogramPaint.setAlpha(alpha);
            //计算执行动画当前y坐标
            float top = anims[i].getCurrentY();
            float left = oX+xCoordinates[i]-xSpacing/3;
            float right = oX+xCoordinates[i]+xSpacing/3;
            String ydata = formater.format(yData[i]);
            int[] textSize = getTextSize(xData[i],textPaint);
            float textY = top - textSize[1]/2;
            //画矩形上文字
            canvas.drawText(ydata,left,textY,textPaint);
            //画每一个矩形
            canvas.drawRect(left,top,right,oY,histogramPaint);
        }
    }
    /*
     * 设置数据
     */
    @Override
    public void setChartData(ChartData chartData) {
        super.setChartData(chartData);
        HistogramData histogramData = (HistogramData) chartData;
        this.rect_color = histogramData.getRectColor() != null ?
                histogramData.getRectColor() : this.rect_color;
        this.rect_text_size = getFinalValue(this.rect_text_size,histogramData.getRectTextSize());
        this.rect_text_color = getFinalValue(this.rect_text_color,histogramData.getRectTextColor());
    }
}
