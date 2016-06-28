package com.zqx.chart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zqx.chart.R;

/**
 * 饼状图
 * Created by zqx on 16/6/26.
 */
public class PieChart extends View{
    private float left;
    private float top;
    private float right;
    private float bottom;
    private float pieMax;//数据总和
    private float[] degrees;//度数
    private float surplus = 50;//留余
    private float[] datas;//数据
    private int[] colors;//数据对应颜色


    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initColors();
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initColors();
    }
    /*
     * 初始化饼状图颜色
     */
    private void initColors() {
        colors = getResources().getIntArray(R.array.pie_colors);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPieProp();
        drawPieChart(canvas);
    }
    /*
     * 初始化饼状图属性
     */
    private void initPieProp() {
        float width = Math.min(getWidth(),getHeight());
        left = getPaddingLeft() + surplus;
        top = getPaddingTop() + surplus;
        right = width - getPaddingRight() - surplus;
        bottom = width - getPaddingBottom() - surplus;

        float max = 0;
        for (int i=0; i<datas.length; i++){
            max += datas[i];
        }
        degrees = new float[datas.length];
        for (int j=0; j<datas.length; j++){
            degrees[j] = datas[j] / max * 360.0f;
        }
        pieMax = max;
    }

    /*
     * 画饼状图
     */
    private void drawPieChart(Canvas canvas){
        if (datas == null){
            throw new NullPointerException("数据源不能为空!");
        }
        // 创建画笔
        Paint p = new Paint();
        RectF rectF = new RectF(left, top, right, bottom);
        float start = 0f;
        for (int i=0; i<datas.length; i++){
            p.setColor(colors[i]);
            try {
                p.setColor(colors == null
                        ? getResources().getColor(R.color.colorPrimary)
                        : colors[i]);
            }catch (ArrayIndexOutOfBoundsException e){
                p.setColor(getResources().getColor(R.color.colorPrimary));
            }
            canvas.drawArc(rectF, start, degrees[i], true, p);
            start += degrees[i];
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }

    public void setDatas(float[] datas) {
        this.datas = datas;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public int[] getColors() {
        return colors;
    }
}
