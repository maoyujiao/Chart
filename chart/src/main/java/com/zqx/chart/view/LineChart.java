package com.zqx.chart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.zqx.chart.R;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.ChartData;
import com.zqx.chart.data.LineChartData;

import java.text.DecimalFormat;

/**
 * 折线统计图
 * Created by zqx on 16/6/25.
 */
public class LineChart extends Chart {
    private int point_color;//折点颜色
    private int point_text_size;//折点文字尺寸
    private int point_text_color;//折点文字颜色
    private float point_size;//折点尺寸
    private int line_color;//折线颜色
    private int line_path_style;//折线样式
    private float line_width;//折现宽度

    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStyle(context,attrs);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStyle(context,attrs);
    }

    /*
     * 初始化样式属性
     */
    private void initStyle(Context context,AttributeSet attrs) {
        TypedArray types = context.obtainStyledAttributes(attrs, R.styleable.zqxchart_line);
        coordinates_color = types.getColor(R.styleable.zqxchart_line_lCoordinatesColor,Color.RED);
        point_color = types.getColor(R.styleable.zqxchart_line_pointColor,Color.RED);
        point_text_color = types.getColor(R.styleable.zqxchart_line_pointTextColor,Color.BLACK);
        point_text_size = types.getInteger(R.styleable.zqxchart_line_pointTextSize,24);
        line_color = types.getColor(R.styleable.zqxchart_line_lineColor,Color.RED);
        x_text_color = types.getColor(R.styleable.zqxchart_line_lxTextColor,Color.BLACK);
        y_text_color = types.getColor(R.styleable.zqxchart_line_lyTextColor,Color.BLACK);
        x_text_size = types.getInteger(R.styleable.zqxchart_line_lxTextSize,30);
        y_text_size = types.getInteger(R.styleable.zqxchart_line_lyTextSize,26);
        xpCount = types.getInteger(R.styleable.zqxchart_line_lxPointCount,7);
        ypCount = types.getInteger(R.styleable.zqxchart_line_lyPointCount,5);
        line_width = types.getFloat(R.styleable.zqxchart_line_line_width,5.0f);
        point_size = types.getFloat(R.styleable.zqxchart_line_point_size,10.0f);
        animType = types.getInteger(R.styleable.zqxchart_line_lanimType, Anim.ANIM_NONE);
        line_path_style = types.getInteger(R.styleable.zqxchart_line_lPathStyle,0);
        types.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }

    /*
     * 画折线
     */
    private void drawLine(Canvas canvas){
        //如果没有设置x轴数据
        if (xData == null){
            throw new NullPointerException("x轴数据源不能为空!");
        }
        //如果没有设置y轴数据
        if (yData == null){
            throw new NullPointerException("y轴数据源不能为空!");
        }
        //折线画笔
        Paint linePaint = new Paint();
        linePaint.setColor(line_color);
        linePaint.setAntiAlias(true);
        linePaint.setStyle(line_path_style == 0 ? Paint.Style.STROKE : Paint.Style.FILL);
        linePaint.setStrokeWidth(line_width);
        //折点画笔
        Paint pointPaint = new Paint();
        pointPaint.setColor(point_color);
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setStrokeWidth(point_size);
        //画折点和折线
        Path path = new Path();
        path.moveTo(oX,oY);
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(point_text_size);
        textPaint.setColor(point_text_color);
        DecimalFormat formater = new DecimalFormat("0.000");
        for (int i=0;i<xpCount;i++){
            //float dataX = oX+xCoordinates[i];
            //float dataY = oY-yData[i]/yMax*yCoordinates[yCoordinates.length-1];
            int alpha = anims[i].getAlpha();
            linePaint.setAlpha(alpha);
            pointPaint.setAlpha(alpha);
            textPaint.setAlpha(alpha);
            canvas.drawPoint(anims[i].getFinalX(),anims[i].getCurrentY(),pointPaint);
            //画折点上文字
            String text = formater.format(yData[i]);
            int[] textSize = getTextSize(text,textPaint);
            int textX = textSize[0];
            int textY = textSize[1];
            canvas.drawText(text,anims[i].getFinalX()-textX/2,anims[i].getCurrentY()-textY,textPaint);
            path.lineTo(anims[i].getFinalX(),anims[i].getCurrentY());
        }
        switch (line_path_style){
            case 0:
                canvas.drawPath(path,linePaint);
                break;
            case 1:
                linePaint.setStyle(Paint.Style.FILL);
                path.close();
                canvas.drawPath(path,linePaint);
                break;
            default:
                canvas.drawPath(path,linePaint);
                break;
        }
    }

    @Override
    public void setChartData(ChartData chartData) {
        super.setChartData(chartData);
        LineChartData lineData = (LineChartData) chartData;
        this.line_color = getFinalValue(this.line_color,lineData.getLineColor());
        this.point_color = getFinalValue(this.point_color,lineData.getPointColor());
        this.point_text_size = getFinalValue(this.point_text_size,lineData.getPointTextSize());
        this.point_text_color = getFinalValue(this.point_text_color,lineData.getPointTextColor());
        this.line_width = lineData.getLineWidth() != 0f ? lineData.getLineWidth() : this.line_width;
        this.point_size = lineData.getPointSize() != 0f ? lineData.getPointSize() : this.point_size;
        this.line_path_style = lineData.getLinePathStyle() != -1 ?
                lineData.getLinePathStyle() : this.line_path_style;
    }
}
