package com.zqx.zqxcharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zqx.chart.data.PieChartData;
import com.zqx.chart.event.OnPieItemClickListener;
import com.zqx.chart.view.PieChart;

/**
 * Created by zqx on 16/6/30.
 */
public class PieActivity extends AppCompatActivity{

    float[] datas = new float[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart);
        update();
        final PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        final int[] colors = new int[]{Color.RED,Color.BLACK,Color.BLUE,Color.GREEN,Color.GRAY,
                Color.YELLOW,Color.LTGRAY,Color.CYAN,Color.MAGENTA};
        final PieChartData pieChartData = PieChartData.builder()
                .setDatas(datas)
                //.setColors(colors)
                //.setTextColor(Color.RED)
                //.setTextSize(36)
                //.setSeparationDegree(3)
                .setPieItemClickListener(new OnPieItemClickListener() {
                    @Override
                    public void onPieItemClick(int position) {
                        Toast.makeText(PieActivity.this,"click->"+position,Toast.LENGTH_SHORT).show();
                    }
                }).build();
        pieChart.setChartData(pieChartData);
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                pieChartData.setDatas(datas);
                pieChart.update(pieChartData);
            }
        });
    }

    private void update(){
        for (int i=0;i<9;i++){
            datas[i] = (float) (Math.random() * 50.0f);
        }
    }
}
