package com.zqx.chart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.zqx.chart.anim.AlphaAnim;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.anim.TranslateAnim;
import com.zqx.chart.data.ChartData;

import java.text.DecimalFormat;

/**
 * 统计图
 * Created by zqx on 16/6/25.
 */
public class Chart extends View{

    protected final int xSurplus = 50;//x轴留余
    protected final int ySurplus = 40;//y轴留余
    protected final int xTextSurplus = 100;//x轴文字留余
    protected final int yTextSurplus = 100;//y轴文字留余
    protected float oX;//原点x
    protected float oY;//原点y
    protected int xWidth;//x轴宽度
    protected int yHeight;//y轴高度
    protected int xSpacing;//x轴坐标间距
    protected int ySpacing;//y轴坐标间距
    protected int xpCount = 7;//x轴坐标点数
    protected int ypCount = 5;//y轴坐标点数
    protected int[] xCoordinates;//x轴坐标点
    protected int[] yCoordinates;//y轴坐标点
    protected float yMax = 0f;//y轴最大刻度值
    protected String[] xData;//x轴数据
    protected float[] yData;//y轴数据
    protected int coordinates_color;//坐标系颜色
    protected int x_text_size;//x轴文字尺寸
    protected int y_text_size;//y轴文字尺寸
    protected int x_text_color;//x轴文字颜色
    protected int y_text_color;//y轴文字颜色
    protected boolean isAnim = true;
    protected Anim[] anims;//动画数组
    protected long interval = 100;//动画执行间隔
    protected int animType = -2;//动画

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initChartProp();
        initCoordinateSystem(canvas);
        //开始动画
        if (isAnim){
            initAnims();
            post(animator);
            isAnim = false;
        }
    }
    /*
     * 初始化统计图属性
     */
    private void initChartProp() {
        //x轴宽度
        xWidth = getWidth() - getPaddingLeft() - getPaddingRight() - yTextSurplus;
        //y轴宽度
        yHeight = getHeight() - getPaddingTop() - getPaddingBottom() - xTextSurplus;
        //x轴每个刻度的间距
        xSpacing = (xWidth-xSurplus-xWidth%xpCount)/xpCount;
        //y轴每个刻度的间距
        ySpacing = (yHeight-ySurplus-yHeight%ypCount)/ypCount;
        //坐标系原点x
        oX = getPaddingLeft()+yTextSurplus;
        //坐标系原点y
        oY = getPaddingTop()+yHeight+xTextSurplus/2;
        //x轴各刻度点位置
        xCoordinates = new int[xpCount];
        //y轴各刻度点位置
        yCoordinates = new int[ypCount];
        //记录x轴刻度点位置
        for (int i=0; i<xpCount; i++){
            xCoordinates[i] = (i+1) * xSpacing;
        }
        //记录y轴刻度点位置
        for (int j=0; j<ypCount; j++){
            yCoordinates[j] = (j+1) * ySpacing;
        }
    }
    /*
     * 初始化坐标系
     */
    private void initCoordinateSystem(Canvas canvas){
        Paint mainPaint = new Paint();
        mainPaint.setColor(coordinates_color);
        mainPaint.setAntiAlias(true);
        //draw x 坐标
        canvas.drawLine(oX,oY, oX,oY-yHeight,mainPaint);
        //draw y 坐标
        canvas.drawLine(oX,oY,oX+xWidth,oY,mainPaint);
        //刻度值画笔
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //draw x 刻度
        for (int i=0; i<xCoordinates.length; i++){
            //更改文字大小
            textPaint.setTextSize(x_text_size);
            //更改文字颜色
            textPaint.setColor(x_text_color);
            //x轴刻度线
            canvas.drawLine(oX+xCoordinates[i],oY,oX+xCoordinates[i],oY-5,mainPaint);
            //获取x轴文字宽高
            int[] textSize = getTextSize(xData[i],textPaint);
            //计算x轴文字x,y坐标偏移量
            int textX = textSize[0]/2;
            int textY = xTextSurplus/2 - textSize[1]/2;
            //画x轴文字
            canvas.drawText(xData[i],oX+xCoordinates[i]-textX,oY+textY,textPaint);
        }
        if(yMax == 0f){
            yMax = getYMax();
        }
        //draw y 刻度
        for (int j=0; j<ypCount; j++){
            //更改文字大小
            textPaint.setTextSize(y_text_size);
            //更改文字颜色
            textPaint.setColor(y_text_color);
            //y轴刻度线
            canvas.drawLine(oX,oY-yCoordinates[j],oX+5,oY-yCoordinates[j],mainPaint);
            //y轴刻度值
            int iYMax = (int)yMax;
            String datay;
            if (yMax - iYMax == 0){
                datay = (int)(yMax/ypCount*(j+1))+"";
            }else {
                datay = new DecimalFormat("0.000").format(yMax/ypCount*(j+1));
            }

            //获取文字宽高
            int[] textSize = getTextSize(datay,textPaint);
            //计算画文字的坐标偏移量
            int textX = yTextSurplus/2 + textSize[0]/2;
            int textY = textSize[1]/2;
            //画y轴文字
            canvas.drawText(datay,oX-textX,oY-yCoordinates[j]+textY,textPaint);
        }
    }

    /*
     * 准备动画
     */
    private void initAnims() {
        anims = new Anim[xpCount];
        switch (animType){
            case Anim.ANIM_TRANSLATE:
                for (int i=0;i<xpCount;i++){
                    float dataX = oX+xCoordinates[i];
                    float dataY = oY-yData[i]/yMax*yCoordinates[yCoordinates.length-1];
                    Anim anim = new Anim(dataX,dataY,dataX,oY);
                    anim.setAnimation(new TranslateAnim());
                    anim.setVelocity(interval*2);
                    anims[i] = anim;
                }
                break;
            case Anim.ANIM_ALPHA:
                for (int i=0;i<xpCount;i++){
                    float dataX = oX+xCoordinates[i];
                    float dataY = oY-yData[i]/yMax*yCoordinates[yCoordinates.length-1];
                    Anim anim = new Anim(dataX,dataY,dataX,dataY);
                    anim.setAnimation(new AlphaAnim());
                    anim.setAlpha(0);
                    anim.setVelocity(interval * 3/2);
                    anims[i] = anim;
                }
                break;
            default:
                for (int i=0;i<xpCount;i++){
                    float dataX = oX+xCoordinates[i];
                    float dataY = oY-yData[i]/yMax*yCoordinates[yCoordinates.length-1];
                    Anim anim = new Anim(dataX,dataY,dataX,dataY);
                    anim.setAnimation(new TranslateAnim());
                    anim.setVelocity(interval);
                    anims[i] = anim;
                }
                break;
        }
    }

    /*
     * 获取y轴最大值
     */
    protected float getYMax(){
        if (yData == null || yData.length == 0){
            yData = new float[ypCount];
        }
        float max = 1f;
        for (int i=0; i<yData.length; i++){
            if (max < yData[i]){
                max = yData[i];
            }
        }
        return max;
    }

    /*
     * 获取文字宽高
     */
    protected int[] getTextSize(String str,Paint paint){
        //计算文字所在矩形，可以得到宽高
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        int w = rect.width();
        int h = rect.height();
        return new int[]{w,h};
    }
    /*
     * 设置统计图属性
     */
    public void setChartData(ChartData chartData) {
        if (chartData == null){
            throw new NullPointerException("折线图数据不能为空!");
        }
        this.xData = chartData.getXdata();
        this.yData = chartData.getYdata();
        this.xpCount = getFinalValue(this.xpCount,chartData.getXpCount());
        this.ypCount = getFinalValue(this.ypCount,chartData.getYpCount());
        this.coordinates_color = getFinalValue(this.coordinates_color,chartData.getCoordinatesColor());
        this.x_text_size = getFinalValue(this.x_text_size,chartData.getxTextSize());
        this.y_text_size = getFinalValue(this.y_text_size,chartData.getyTextSize());
        this.x_text_color = getFinalValue(this.x_text_color,chartData.getxTextColor());
        this.y_text_color = getFinalValue(this.y_text_color,chartData.getyTextColor());
        this.interval = getFinalValue((int) this.interval,chartData.getInterval());
        this.animType = chartData.getAnimType() != -2 ? chartData.getAnimType()
                : this.animType;
        this.yMax = chartData.getyMax() != 0f ? chartData.getyMax() : this.yMax;
    }
    /*
     * 设置正确属性值
     */
    protected int getFinalValue(int old,int news){
        old = news != 0 ? news : old;
        return old;
    }
    /*
     * 执行动画runnable
     */
    private Runnable animator = new Runnable() {
        @Override
        public void run() {
            for(Anim a : anims){
                if(!a.isOver()){
                    a.refresh();
                    postDelayed(this, interval);
                    invalidate();
                    return;
                }
            }
        }
    };
    /*
     * 更新数据并重绘
     */
    public void update(ChartData chartData){
        yMax = 0;
        setChartData(chartData);
        isAnim = true;
        invalidate();
    }
}
