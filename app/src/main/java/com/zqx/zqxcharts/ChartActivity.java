package com.zqx.zqxcharts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.HistogramData;
import com.zqx.chart.data.LineChartData;
import com.zqx.chart.view.Histogram;
import com.zqx.chart.view.LineChart;

/**
 * Created by zqx on 16/6/30.
 */
public class ChartActivity extends AppCompatActivity {
    float[] ydata = new float[7];
    float[] ydata2 = new float[7];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        update();
        final String[] xdata = new String[]{"06-11","06-12","06-13","06-14","06-15","06-16","06-17"};
        final LineChart lineChart = (LineChart) findViewById(R.id.linechart);
        final LineChartData lineChartData = LineChartData.builder()
                .setXdata(xdata)
                .setYdata(ydata)
                .setCoordinatesColor(getResources().getColor(android.R.color.holo_orange_dark))
                .setYpCount(7)
                .setPointSize(20)
                .setAnimType(Anim.ANIM_ALPHA)
                .build();
        lineChart.setChartData(lineChartData);
        final Histogram histogramChart = (Histogram) findViewById(R.id.histogramchart);
        final HistogramData histogramData = HistogramData.builder()
                .setXdata(xdata)
                .setYdata(ydata)
                .setYpCount(7)
                .setAnimType(Anim.ANIM_NONE)
                .build();
        histogramChart.setChartData(histogramData);
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                lineChartData.setYdata(ydata);
                histogramData.setYdata(ydata2);
                lineChart.update(lineChartData);
                histogramChart.update(histogramData);
            }
        });
    }
    private void update(){
        for (int i=0;i<7;i++){
            ydata[i] = (float) (Math.random() * 50.0f);
            ydata2[i] = (float) (Math.random() * 100.0f);
        }
    }

}
