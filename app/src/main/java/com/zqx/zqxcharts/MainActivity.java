package com.zqx.zqxcharts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.HistogramData;
import com.zqx.chart.data.LineChartData;
import com.zqx.chart.data.PieChartData;
import com.zqx.chart.event.OnPieItemClickListener;
import com.zqx.chart.view.Histogram;
import com.zqx.chart.view.LineChart;
import com.zqx.chart.view.PieChart;
import com.zqx.zqxcharts.adapter.BaseListViewAdapter;
import com.zqx.zqxcharts.adapter.MyListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.chart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChartActivity.class));
            }
        });
        findViewById(R.id.piechart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PieActivity.class));
            }
        });
    }
}
